package utn.dds.k3001.grupo3.tpa.controllers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import utn.dds.k3001.grupo3.tpa.busquedas.Mapa;
import utn.dds.k3001.grupo3.tpa.busquedas.Terminal;
import utn.dds.k3001.grupo3.tpa.busquedas.RepositorioTerminales;
import utn.dds.k3001.grupo3.tpa.pois.POI;
import utn.dds.k3001.grupo3.tpa.usuarios.Usuario;
import utn.dds.k3001.grupo3.tpa.usuarios.UsuarioTerminal;

public class TerminalesController {

	public ModelAndView listar(Request req, Response res){
		if(req.session().attribute("user")!=null)
		{	
			if(!((Usuario) req.session().attribute("user")).esAdmin()) {
				//res.status(403);
				res.redirect("/login",403);
			} 
			else {
				Map<String, List<Terminal>> model = new HashMap<>();
				List<Terminal> terminales = new LinkedList<Terminal>();
				if (req.queryParams("comuna")!=null)
					terminales.addAll(RepositorioTerminales.getInstance().buscarTerminalesPorComuna(req.queryParams("comuna")));
				else
					terminales.addAll(RepositorioTerminales.getInstance().obtenerTerminales());
				model.put("Terminales", terminales);
				return new ModelAndView(model, "admin/listarTerminales.hbs");
			}
		}
		else {
			//res.status(403);
			res.redirect("/login",403);
		}
		return null;
	}
	
	public ModelAndView getEliminar(Request req, Response res){
		if(req.session().attribute("user")!=null)
		{	
			if(!((Usuario) req.session().attribute("user")).esAdmin()) {
				res.redirect("/login", 403);
				return null;
			}
			else {
				Map<String, Terminal> model = new HashMap<>();
				model.put("terminal", RepositorioTerminales.getInstance().buscarTerminalPorId(Integer.parseInt(req.params("id"))));
				return new ModelAndView(model, "admin/eliminarTerminal.hbs");
			}
		}
		else {
			//res.status(403);
			res.redirect("/login",403);
		}
		return null;
	}
	
	public ModelAndView eliminar(Request req, Response res){
		if(req.session().attribute("user")!=null)
		{	
			if(!((Usuario) req.session().attribute("user")).esAdmin()) {
				res.redirect("/login", 403);
				return null;
			}
			else {
				RepositorioTerminales.getInstance().eliminarTerminalPorId(Integer.parseInt(req.params("id")));
				res.redirect("/terminales");
				return null;
			}
		}
		else
			res.redirect("/login",403);
		return null;
	}
}
