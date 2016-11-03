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
			List<POI> pois = Mapa.getInstance().buscar(req.queryParams("criterio")!= null? req.queryParams("criterio") : "");
			model.put("pois", pois);
			return new ModelAndView(model, "admin/buscarPOI.hbs");
		}
		
		} else {
			//res.status(403);
			res.redirect("/login",403);
		}
		return null;
	}
}
