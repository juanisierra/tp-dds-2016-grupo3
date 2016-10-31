package utn.dds.k3001.grupo3.tpa.controllers;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import utn.dds.k3001.grupo3.tpa.usuarios.RepositorioUsuarios;
import utn.dds.k3001.grupo3.tpa.usuarios.TipoUsuario;

public class TerminalController {
	public ModelAndView mostrarIndex(Request req, Response res){
		if(req.cookie("user")!=null && RepositorioUsuarios.instance().tipoUsuario(req.cookie("user"))==TipoUsuario.TERMINAL)
		{	
			return new ModelAndView(null, "/terminal/index.hbs");
		} else {
			//res.status(403);
			res.redirect("/login",403);
		}
		return null;
	}
}
