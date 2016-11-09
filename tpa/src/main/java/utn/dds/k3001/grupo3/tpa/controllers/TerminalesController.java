package utn.dds.k3001.grupo3.tpa.controllers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.utils.AccionesBusquedaHelper;
import utn.dds.k3001.grupo3.tpa.busquedas.AccionesBusqueda;
import utn.dds.k3001.grupo3.tpa.busquedas.RepositorioComunas;
import utn.dds.k3001.grupo3.tpa.busquedas.Terminal;
import utn.dds.k3001.grupo3.tpa.busquedas.RepositorioTerminales;
import utn.dds.k3001.grupo3.tpa.pois.Comuna;
import utn.dds.k3001.grupo3.tpa.usuarios.Usuario;

//TODO Abstraer recopilación de comunas y ABM de terminales al repositorio, emprolijar
public class TerminalesController {

	public ModelAndView listar(Request req, Response res){
		if(req.session().attribute("user")!=null)
		{	
			if(!((Usuario) req.session().attribute("user")).esAdmin()) {
				//res.status(403);
				res.redirect("/login",403);
			} 
			else {
				Map<String, List> model = new HashMap<>();
				List<Terminal> terminales = new LinkedList<Terminal>();
				List<Comuna> comunas = new LinkedList<Comuna>();
				comunas.add(new Comuna("Cualquiera"));
				comunas.addAll(RepositorioComunas.getInstance().getComunas());
				model.put("comunas", comunas);
				
				if(req.queryParams("nombre")!=null && !req.queryParams("nombre").isEmpty())
					terminales.addAll(RepositorioTerminales.getInstance().buscarTerminalesPorNombre(req.queryParams("nombre")));
				else
					terminales.addAll(RepositorioTerminales.getInstance().obtenerTerminales());
				 
				if (req.queryParams("comuna") !=null && !req.queryParams("comuna").equals("Cualquiera"))
					terminales = terminales.stream().filter(t -> t.estaEnComuna(req.queryParams("comuna"))).collect(Collectors.toList());

				model.put("Terminales", terminales);
				return new ModelAndView(model, "admin/listarTerminales.hbs");
			}
		}
		else {
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
	
	public ModelAndView getModificar(Request req, Response res){
		if(req.session().attribute("user")!=null)
		{	
			if(!((Usuario) req.session().attribute("user")).esAdmin()) {
				res.redirect("/login", 403);
				return null;
			}
			else {
				Map<String, Object> model = new HashMap<>();
				model.put("comunas", RepositorioComunas.getInstance().getComunas());
				model.put("terminal", RepositorioTerminales.getInstance().buscarTerminalPorId(Integer.parseInt(req.params("id"))));
				return new ModelAndView(model, "admin/modificarTerminal.hbs");
			}
		}
		else {
			res.redirect("/login",403);
		}
		return null;
	}
	
	public ModelAndView modificar(Request req, Response res){
		if(req.session().attribute("user")!=null)
		{	
			if(!((Usuario) req.session().attribute("user")).esAdmin()) {
				res.redirect("/login", 403);
				return null;
			}
			else {
				if (req.params("id") == null || req.queryParams("nombre")==null || req.queryParams("nombre").isEmpty()){
					res.redirect("/terminales");
					return null;
				}
				Terminal terminalAModificar = RepositorioTerminales.getInstance().buscarTerminalPorId(Integer.parseInt(req.params("id")));
				List<Terminal> terminalesEncontradas = RepositorioTerminales.getInstance().buscarTerminalesPorNombre(req.queryParams("nombre"));
				if (!terminalesEncontradas.isEmpty() && !terminalesEncontradas.get(0).getNombre().equals(req.queryParams("nombre"))){
					res.redirect("/login",403);
					return null;
				}
				List<Comuna> comunasEncontradas = RepositorioComunas.getInstance().buscarComunasPorNombre(req.queryParams("comuna"));
				if (!comunasEncontradas.isEmpty()){
					terminalAModificar.setNombre(req.queryParams("nombre"));
					terminalAModificar.setComuna(comunasEncontradas.get(0));
				}
				res.redirect("/terminales");
				return null;
			}
		}
		else {
			res.redirect("/login",403);
		}
		return null;
	}
	
	public ModelAndView getAgregar(Request req, Response res){
		if(req.session().attribute("user")!=null)
		{	
			if(!((Usuario) req.session().attribute("user")).esAdmin()) {
				res.redirect("/login", 403);
				return null;
			}
			else {
				Map<String, List<Comuna>> model = new HashMap<>();
				model.put("comunas", RepositorioComunas.getInstance().getComunas());
				return new ModelAndView(model, "admin/agregarTerminal.hbs");
			}
		}
		else {
			res.redirect("/login",403);
		}
		return null;
	}
	
	public ModelAndView agregar(Request req, Response res){
		if(req.session().attribute("user")!=null)
		{	
			if(!((Usuario) req.session().attribute("user")).esAdmin()) {
				res.redirect("/login", 403);
				return null;
			}
			else {
				if (req.queryParams("nombre")==null || req.queryParams("nombre").isEmpty()){
					res.redirect("/terminales");
					return null;
				}
				//Verifico que no haya terminales con igual nombre y lo agrego en memoria
				List<Terminal> terminalesEncontradas = new LinkedList<Terminal>();
				terminalesEncontradas.addAll(RepositorioTerminales.getInstance().buscarTerminalesPorNombre(req.queryParams("nombre")));
				if (!(terminalesEncontradas.isEmpty())){
					res.redirect("/login",403);
					return null;
				}
				Terminal terminal = new Terminal();
				terminal.setNombre(req.queryParams("nombre"));
				List<Comuna> comunasEncontradas = RepositorioComunas.getInstance().buscarComunasPorNombre(req.queryParams("comuna"));
				if (!comunasEncontradas.isEmpty()){
					terminal.setComuna(comunasEncontradas.get(0));
					RepositorioTerminales.getInstance().agregarTerminal(terminal);
				}
				res.redirect("/terminales");
				return null;
			}
		}
		else {
			res.redirect("/login",403);
		}
		return null;
	}
	
	public ModelAndView getAcciones(Request req, Response res){
		if(req.session().attribute("user")!=null)
		{	
			if(!((Usuario) req.session().attribute("user")).esAdmin()) {
				res.redirect("/login", 403);
				return null;
			}
			else {
				Map<String, Terminal> model = new HashMap<>();
				model.put("terminal", RepositorioTerminales.getInstance().buscarTerminalPorId(Integer.parseInt(req.params("id"))));
				return new ModelAndView(model, "admin/accionesTerminal.hbs");
			}
		}
		else {
			res.redirect("/login",403);
		}
		return null;
	}
	
	public ModelAndView actualizarAcciones(Request req, Response res){
		if(req.session().attribute("user")!=null)
		{	
			if(!((Usuario) req.session().attribute("user")).esAdmin()) {
				res.redirect("/login", 403);
				return null;
			}
			else {
				Terminal terminal = RepositorioTerminales.getInstance().buscarTerminalPorId(Integer.parseInt(req.params("id")));
				if (terminal!=null){
					if (req.queryParams(String.valueOf(AccionesBusqueda.GUARDARBUSQUEDA))!=null && req.queryParams(String.valueOf(AccionesBusqueda.GUARDARBUSQUEDA)).equals("on")){
						//System.out.println("Guardar busqueda OK");
					}

				}
				//res.redirect("/terminales");
				return null;
			}
		}
		else
			res.redirect("/login",403);
		return null;
	}
}
