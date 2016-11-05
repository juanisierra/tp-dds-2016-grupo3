package utn.dds.k3001.grupo3.tpa.controllers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.*;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import utn.dds.k3001.grupo3.tpa.busquedas.Mapa;
import utn.dds.k3001.grupo3.tpa.busquedas.Terminal;
import utn.dds.k3001.grupo3.tpa.busquedas.RepositorioTerminales;
import utn.dds.k3001.grupo3.tpa.pois.Comuna;
import utn.dds.k3001.grupo3.tpa.pois.POI;
import utn.dds.k3001.grupo3.tpa.usuarios.Usuario;
import utn.dds.k3001.grupo3.tpa.usuarios.UsuarioTerminal;

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
				terminales.addAll(RepositorioTerminales.getInstance().obtenerTerminales());
				for (Terminal terminal : terminales){ //Obtener todas las comunas de las terminales
					if(!comunas.contains(terminal.getComuna()))
						comunas.add(terminal.getComuna());
				}
				terminales.clear();
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
	
	public ModelAndView getModificar(Request req, Response res){
		if(req.session().attribute("user")!=null)
		{	
			if(!((Usuario) req.session().attribute("user")).esAdmin()) {
				res.redirect("/login", 403);
				return null;
			}
			else {
				List<Terminal> terminales = new LinkedList<Terminal>();
				List<Comuna> comunas = new LinkedList<Comuna>();
				comunas.add(new Comuna("Cualquiera"));
				terminales.addAll(RepositorioTerminales.getInstance().obtenerTerminales());
				for (Terminal term : terminales){ //Obtener todas las comunas de las terminales
					if(!comunas.contains(term.getComuna()))
						comunas.add(term.getComuna());
				}
				terminales.clear();
				Map<String, Object> model = new HashMap<>();
				model.put("comunas", comunas);
				model.put("terminal", RepositorioTerminales.getInstance().buscarTerminalPorId(Integer.parseInt(req.params("id"))));
				return new ModelAndView(model, "admin/modificarTerminal.hbs");
			}
		}
		else {
			//res.status(403);
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
				Terminal terminalAModificar = RepositorioTerminales.getInstance().buscarTerminalPorId(Integer.parseInt(req.params("id")));
				List<Terminal> terminalesEncontradas = RepositorioTerminales.getInstance().buscarTerminalesPorNombre(req.queryParams("nombre"));
				if (!terminalesEncontradas.isEmpty()){
					res.redirect("/login",403);
					return null;
				}
				terminalAModificar.setNombre(req.queryParams("nombre"));
				
				List<Terminal> terminales = new LinkedList<Terminal>();
				List<Comuna> comunas = new LinkedList<Comuna>();
				terminales.addAll(RepositorioTerminales.getInstance().obtenerTerminales());
				for (Terminal term : terminales){ //Obtener todas las comunas de las terminales
					if(!comunas.contains(term.getComuna()))
						comunas.add(term.getComuna());
				}
				terminales.clear();
				
				Comuna comunaSeleccionada = comunas.stream().filter(com -> com.getNombre().equals(req.queryParams("comuna"))).collect(Collectors.toList()).get(0);
				terminalAModificar.setComuna(comunaSeleccionada);
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
				List<Terminal> terminales = new LinkedList<Terminal>();
				List<Comuna> comunas = new LinkedList<Comuna>();
				comunas.add(new Comuna("Cualquiera"));
				terminales.addAll(RepositorioTerminales.getInstance().obtenerTerminales());
				for (Terminal term : terminales){ //Obtener todas las comunas de las terminales
					if(!comunas.contains(term.getComuna()))
						comunas.add(term.getComuna());
				}
				terminales.clear();
				Map<String, List<Comuna>> model = new HashMap<>();
				model.put("comunas", comunas);
				return new ModelAndView(model, "admin/agregarTerminal.hbs");
			}
		}
		else {
			//res.status(403);
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
			else { //Verifico que no haya terminales con igual nombre y lo agrego en memoria
				List<Terminal> terminalesEncontradas = RepositorioTerminales.getInstance().buscarTerminalesPorNombre(req.params("nombre"));
				if (!terminalesEncontradas.isEmpty()){
					res.redirect("/login",403);
					return null;
				}
				Terminal terminal = new Terminal();
				terminal.setNombre(req.queryParams("nombre"));
				List<Terminal> terminales = new LinkedList<Terminal>();
				List<Comuna> comunas = new LinkedList<Comuna>();
				terminales.addAll(RepositorioTerminales.getInstance().obtenerTerminales());
				for (Terminal term : terminales){ //Obtener todas las comunas de las terminales
					if(!comunas.contains(term.getComuna()))
						comunas.add(term.getComuna());
				}
				terminales.clear();
				Comuna comunaSeleccionada = comunas.stream().filter(com -> com.getNombre().equals(req.queryParams("comuna"))).collect(Collectors.toList()).get(0);
				terminal.setComuna(comunaSeleccionada);
				RepositorioTerminales.getInstance().agregarTerminal(terminal);

				res.redirect("/terminales");
				return null;
			}
		}
		else {
			res.redirect("/login",403);
		}
		return null;
	}
}
