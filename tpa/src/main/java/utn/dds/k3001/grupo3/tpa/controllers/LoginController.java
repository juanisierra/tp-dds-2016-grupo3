package utn.dds.k3001.grupo3.tpa.controllers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController {
	public ModelAndView mostrarLogin(Request req, Response res){
		//Map<String, List<Proyecto>> model = new HashMap<>();
		//List<Proyecto> proyectos = RepositorioProyectos.instancia.listar();
		
		//model.put("proyectos", proyectos);
		return new ModelAndView(null, "login/login.hbs");
	}
	public String iniciarSesion(Request req, Response res){
		//Map<String, List<Proyecto>> model = new HashMap<>();
		//List<Proyecto> proyectos = RepositorioProyectos.instancia.listar();
		String id_usuario = req.queryParams("usuario");
		String contrasenia = req.queryParams("contrasenia");
		//model.put("proyectos", proyectos);
		return id_usuario+contrasenia;
	}
}
