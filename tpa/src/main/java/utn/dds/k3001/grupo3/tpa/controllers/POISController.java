package utn.dds.k3001.grupo3.tpa.controllers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import utn.dds.k3001.grupo3.tpa.busquedas.Mapa;
import utn.dds.k3001.grupo3.tpa.pois.POI;
import utn.dds.k3001.grupo3.tpa.usuarios.Usuario;
import utn.dds.k3001.grupo3.tpa.usuarios.UsuarioTerminal;

public class POISController {
	public ModelAndView buscar(Request req, Response res){
		if(req.session().attribute("user")!=null)
		{	if(!((Usuario) req.session().attribute("user")).esAdmin()) {
			Map<String, List<POI>> model = new HashMap<>();
			List<POI> pois = ((UsuarioTerminal) req.session().attribute("user")).getTerminal().buscar(req.queryParams("criterio")!= null? req.queryParams("criterio") : "");
			model.put("pois", pois);
			return new ModelAndView(model, "terminal/buscarPOI.hbs");
		} else {
			Map<String, List<POI>> model = new HashMap<>();
			List<POI> pois = new LinkedList<POI>();
			if(req.queryParams("tipos")==null)
					pois.addAll(Mapa.getInstance().buscar(req.queryParams("criterio")!= null? req.queryParams("criterio") : ""));
					else {
						pois.addAll(Mapa.getInstance().buscar(req.queryParams("criterio")!= null? req.queryParams("criterio") : "",req.queryParams("tipos")));
						
					}
			model.put("pois", pois);
			return new ModelAndView(model, "admin/buscarPOI.hbs");
		}
		
		} else {
			//res.status(403);
			res.redirect("/login",403);
		}
		return null;
	}
	public ModelAndView eliminar(Request req, Response res){
		if(req.session().attribute("user")!=null)
		{	if(!((Usuario) req.session().attribute("user")).esAdmin()) {
			res.redirect("/login", 403);
			return null;
		} else {
			Mapa.getInstance().eliminarPorId(req.params("id"));
			res.redirect("/pois");
			return null;
		}
		
		} else {
			res.redirect("/login",403);
		}
		return null;
	}
	public ModelAndView getEliminar(Request req, Response res){
		if(req.session().attribute("user")!=null)
		{	if(!((Usuario) req.session().attribute("user")).esAdmin()) {
			res.redirect("/login", 403);
			return null;
		} else {
			Map<String, POI> model = new HashMap<>();
			model.put("poi", Mapa.getInstance().getById(req.params("id")));
			return new ModelAndView(model, "admin/EliminarPOI.hbs");
		}
		
		} else {
			//res.status(403);
			res.redirect("/login",403);
		}
		return null;
	}
	public ModelAndView getModificar(Request req, Response res){
		if(req.session().attribute("user")!=null)
		{	if(!((Usuario) req.session().attribute("user")).esAdmin()) {
			res.redirect("/login", 403);
			return null;
		} else {
			Map<String, POI> model = new HashMap<>();
			model.put("poi", Mapa.getInstance().getById(req.params("id")));
			return new ModelAndView(model, "admin/modificarPOI.hbs");
		}
		
		} else {
			//res.status(403);
			res.redirect("/login",403);
		}
		return null;
	}
	public ModelAndView modificar(Request req, Response res){
		if(req.session().attribute("user")!=null)
		{	if(!((Usuario) req.session().attribute("user")).esAdmin()) {
			res.redirect("/login", 403);
			return null;
		} else {
			POI poi = Mapa.getInstance().getById(req.params("id"));
			poi.setNombre(req.queryParams("nombre"));
			poi.getDireccion().setCalle(req.queryParams("calle"));
			poi.getDireccion().setAltura(Integer.parseInt(req.queryParams("numero")));
			poi.getDireccion().setBarrio((req.queryParams("barrio")));
			res.redirect("/pois");
			return null;
		}
		
		} else {
			res.redirect("/login",403);
		}
		return null;
	}
}
