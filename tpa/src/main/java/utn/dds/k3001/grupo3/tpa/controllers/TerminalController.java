package utn.dds.k3001.grupo3.tpa.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import utn.dds.k3001.grupo3.tpa.busquedas.Mapa;
import utn.dds.k3001.grupo3.tpa.pois.POI;
import utn.dds.k3001.grupo3.tpa.usuarios.RepositorioUsuarios;
import utn.dds.k3001.grupo3.tpa.usuarios.TipoUsuario;

public class TerminalController {
	public ModelAndView buscar(Request req, Response res){
		if(req.session().attribute("user")!=null && RepositorioUsuarios.instance().tipoUsuario(req.session().attribute("user"))==TipoUsuario.TERMINAL)
		{	//TODO LINKEAR A TERMINAL
			Map<String, List<POI>> model = new HashMap<>();
			List<POI> pois = Mapa.getInstance().buscar(req.queryParams("criterio") != null? req.queryParams("criterio") : "");
			model.put("pois", pois);
			return new ModelAndView(model, "terminal/buscar.hbs");
		} else {
			//res.status(403);
			res.redirect("/login",403);
		}
		return null;
	}
}
