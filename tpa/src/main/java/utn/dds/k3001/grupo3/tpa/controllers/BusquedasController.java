package utn.dds.k3001.grupo3.tpa.controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import utn.dds.k3001.grupo3.tpa.busquedas.Busqueda;
import utn.dds.k3001.grupo3.tpa.busquedas.RepositorioBusquedas;
import utn.dds.k3001.grupo3.tpa.pois.POI;
import utn.dds.k3001.grupo3.tpa.usuarios.Usuario;

public class BusquedasController
{
	public ModelAndView buscar(Request req, Response res)
	{
		if(req.session().attribute("user")!=null )
		{
			if(( (Usuario) req.session().attribute("user")).esAdmin() )
			{
				String terminal = req.queryParams("terminal") == null? "" : req.queryParams("terminal");
				int cantResultados = (req.queryParams("cantResultados") == null || req.queryParams("cantResultados").equals(""))? -1 : Integer.parseInt(req.queryParams("cantResultados"));
				LocalDate desde =( req.queryParams("desde") == null ||  req.queryParams("desde").equals("") ) ? LocalDate.of(2000,1,1) : parsearFecha( req.queryParams("desde"));
				LocalDate hasta = (req.queryParams("hasta") == null || req.queryParams("hasta").equals("")  ) ? LocalDate.now() : parsearFecha( req.queryParams("hasta"));
				
				List<Busqueda> busquedas = RepositorioBusquedas.getInstance().busquedaWeb(terminal, cantResultados, desde, hasta);
				Map<String, List<Busqueda>> model = new HashMap<>();
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
				List<POI> pois = RepositorioBusquedas.getInstance().buscarPoisPorId(Integer.parseInt (req.params("id")));
				model.put("pois", pois);
				return new ModelAndView(model, "admin/listarPoisBuscados.hbs");
			}	
		} 
		res.redirect("/login",403);
		return null;
	}
	
	private LocalDate parsearFecha(String fechaString)
	{
		int dia =Integer.parseInt (fechaString.substring(0 ,2)) ;
		int mes =Integer.parseInt (fechaString.substring(3, 5)) ;
		int anio =Integer.parseInt (fechaString.substring(6,10)) ;
		return LocalDate.of(anio, mes, dia);
	}
}
