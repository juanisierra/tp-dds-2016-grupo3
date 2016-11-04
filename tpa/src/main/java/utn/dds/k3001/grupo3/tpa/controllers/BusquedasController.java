package utn.dds.k3001.grupo3.tpa.controllers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import utn.dds.k3001.grupo3.tpa.busquedas.Busqueda;
import utn.dds.k3001.grupo3.tpa.busquedas.RepositorioBusquedas;
import utn.dds.k3001.grupo3.tpa.usuarios.Usuario;

public class BusquedasController
{
	public ModelAndView buscar(Request req, Response res)
	{
		if(req.session().attribute("user")!=null)
		{
			Map<String, List<Busqueda>> model = new HashMap<>();
			if(((Usuario) req.session().attribute("user")).esAdmin())
			{
				List<Busqueda> busquedas = new LinkedList<Busqueda>();
				if(req.queryParams("tipos")==null)
				{
					busquedas.addAll(RepositorioBusquedas.getInstance().getBusquedas());
				}
				else
				{
					busquedas.addAll(RepositorioBusquedas.getInstance().getBusquedas());
				}
				model.put("busquedas", busquedas);
				return new ModelAndView(model, "admin/ListarBusquedas.hbs");
			} 
		} 
		res.redirect("/login",403);
		return null;
	}
}
