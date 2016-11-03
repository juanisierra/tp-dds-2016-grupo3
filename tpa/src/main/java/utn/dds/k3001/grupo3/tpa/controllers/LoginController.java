package utn.dds.k3001.grupo3.tpa.controllers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import utn.dds.k3001.grupo3.tpa.usuarios.RepositorioUsuarios;

public class LoginController {
	public ModelAndView mostrarLogin(Request req, Response res){
		Map<String,String> model = new HashMap<>();
		
		return new ModelAndView(null, "login/login.hbs");
	}
	public ModelAndView iniciarSesion(Request req, Response res){
		String id_usuario = req.queryParams("usuario");
		String contrasenia = req.queryParams("contrasenia");
		req.session().removeAttribute("user");
		if(!RepositorioUsuarios.instance().validarUsuario(id_usuario, contrasenia))
		{
			Map<String,String> model = new HashMap<>();
			model.put("error","Error: El usuario o la contraseña no son validos");
			return new ModelAndView(model, "login/login.hbs");
		} else {
			req.session().attribute("user",RepositorioUsuarios.instance().getUsuario(id_usuario));
		if(RepositorioUsuarios.instance().getUsuario(id_usuario).esAdmin()){
			
			res.redirect("/pois");
			
			return null;
		}
		else {
			res.redirect("/pois");
			return null;
		}
		} 
	}
		public Void logout(Request req, Response res){
			req.session().removeAttribute("user");
			res.redirect("/login");
			return null;
		}
	
}
