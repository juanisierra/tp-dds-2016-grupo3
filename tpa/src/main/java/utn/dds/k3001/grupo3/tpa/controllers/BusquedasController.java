package utn.dds.k3001.grupo3.tpa.controllers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import utn.dds.k3001.grupo3.tpa.busquedas.Busqueda;
import utn.dds.k3001.grupo3.tpa.busquedas.Mapa;
import utn.dds.k3001.grupo3.tpa.busquedas.RepositorioBusquedas;
import utn.dds.k3001.grupo3.tpa.pois.POI;
import utn.dds.k3001.grupo3.tpa.usuarios.Usuario;

public class BusquedasController
{
	public ModelAndView buscar(Request req, Response res)
	{
		
		if(true ||req.session().attribute("user")!=null )   /* no valido para testear mas facil */
		{
			Map<String, List<Busqueda>> model = new HashMap<>();
			if(true ||( (Usuario) req.session().attribute("user")).esAdmin() )
			{
				List<Busqueda> busquedas = new LinkedList<Busqueda>();
				String term = req.queryParams("terminal") == null? "" : req.queryParams("terminal");
				int cantResult = (req.queryParams("cantResultados") == null || req.queryParams("cantResultados").equals(""))? -1 : Integer.parseInt(req.queryParams("cantResultados"));
				if(term.equals("") && cantResult == -1)
				{
					busquedas.addAll(RepositorioBusquedas.getInstance().getBusquedas());
				}
				else 	if( !term.equals("") && cantResult == -1)
				{
					busquedas.addAll(RepositorioBusquedas.getInstance().busquedasTerminal(req.queryParams("terminal")));
				}
				else if( term.equals("") && cantResult!= -1)
				{
					busquedas.addAll(RepositorioBusquedas.getInstance().busquedasCantResultados(cantResult));
				}
				else if(!term.equals("") && cantResult != -1)
				{
					busquedas.addAll(RepositorioBusquedas.getInstance()
																												 .getBusquedas()
																												 .stream()
																												 .filter(b -> b.getCantidadResultados() == cantResult 
																												                        && b.getTerminal().getNombre() .equals(term)).collect(Collectors.toList()));
				}
				model.put("busquedas", busquedas);
				return new ModelAndView(model, "admin/ListarBusquedas.hbs");
			} 
		} 
		res.redirect("/login",403);
		return null;
	}
	
	public ModelAndView mostrarPois(Request req, Response res)
	{		
		if(req.session().attribute("user")!=null)
		{	
			if(((Usuario) req.session().attribute("user")).esAdmin())
			{
				Map<String, List<POI>> model = new HashMap<>();
				List<POI> pois;
				Busqueda b = RepositorioBusquedas.getInstance().busquedaConId(Integer.parseInt (req.params("id")));
				pois = b != null? b.getResultados() : new LinkedList<POI>();
				model.put("pois", pois);
				return new ModelAndView(model, "admin/ListarPoisBuscados.hbs");
			}	
		} 
		res.redirect("/login",403);
		return null;
	}
}







