package utn.dds.k3001.grupo3.tpa.controllers;

import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import utn.dds.k3001.grupo3.tpa.usuarios.RepositorioUsuarios;

public class LoginController {
	
	public ModelAndView mostrarLogin(Request req, Response res){
		return new ModelAndView(null, "login/login.hbs");
	}
	
	public ModelAndView iniciarSesion(Request req, Response res){
		String id_usuario = req.queryParams("usuario");
		String contrasenia = req.queryParams("contrasenia");
		req.session().removeAttribute("user");
		if(RepositorioUsuarios.instance().validarUsuario(id_usuario, contrasenia)){
			req.session().attribute("user",RepositorioUsuarios.instance().getUsuario(id_usuario));
			res.redirect("/pois");
			return null;
		}
		else{
			Map<String,String> model = new HashMap<>();
			model.put("error","Error: El usuario o la contraseña no son validos");
			return new ModelAndView(model, "login/login.hbs");
		}
	}
	
		public Void logout(Request req, Response res){
			req.session().removeAttribute("user");
			res.redirect("/login");
			return null;
		}
}
