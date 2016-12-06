package utn.dds.k3001.grupo3.tpa.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import utn.dds.k3001.grupo3.tpa.busquedas.Mapa;
import utn.dds.k3001.grupo3.tpa.busquedas.filtros.FiltroClase;
import utn.dds.k3001.grupo3.tpa.busquedas.filtros.FiltroPOI;
import utn.dds.k3001.grupo3.tpa.busquedas.filtros.FiltroStringCriterio;
import utn.dds.k3001.grupo3.tpa.origenesDePOIS.RepositorioInterno;
import utn.dds.k3001.grupo3.tpa.pois.POI;
import utn.dds.k3001.grupo3.tpa.usuarios.Usuario;
import utn.dds.k3001.grupo3.tpa.usuarios.UsuarioTerminal;

public class POISController implements WithGlobalEntityManager,TransactionalOps {
	
	public ModelAndView buscar(Request req, Response res){
			if(((Usuario) req.session().attribute("user")).esAdmin()) {
			return busquedaAdmin(req, res);
		}
		else return busquedaTerminal(req,res);
	}
	
	private ModelAndView busquedaAdmin(Request req,Response res)
	{
		Map<String, List<POI>> model = new HashMap<>();
		List<POI> pois = new LinkedList<POI>();
		List<FiltroPOI> filtros = new LinkedList<FiltroPOI>();
		if(req.queryParams("tipos")!=null || req.queryParams("criterio")!= null){
			
			if(req.queryParams("criterio")!=null){
				filtros.add(new FiltroStringCriterio(req.queryParams("criterio")));
			}
			if(req.queryParams("tipos")!=null) {
				filtros.add(new FiltroClase(req.queryParams("tipos")));
			}
			pois.addAll(RepositorioInterno.getInstance().buscar(filtros));
		}
			
		model.put("pois", pois);
		return new ModelAndView(model, "admin/buscarPOI.hbs");
	}
	
	private ModelAndView busquedaTerminal(Request req,Response res)
	{
		Map<String, List<POI>> model = new HashMap<>();
		List<POI> pois = new LinkedList<POI>();
		List<FiltroPOI> filtros = new LinkedList<FiltroPOI>();
		if(req.queryParams("criterio")!= null){
	
			pois.addAll(((UsuarioTerminal) req.session().attribute("user")).getTerminal().buscar(req.queryParams("criterio")));
		}
		model.put("pois", pois);
		return new ModelAndView(model, "terminal/buscarPOI.hbs");
	}
	
	public ModelAndView eliminar(Request req, Response res){
		withTransaction(() -> {
			RepositorioInterno.getInstance().eliminarPoiPorNumero(Long.parseLong( req.params("id")));
		});
			res.redirect("/pois");
			return null;
		
	}
	public ModelAndView getEliminar(Request req, Response res){
			Map<String, POI> model = new HashMap<>();
			model.put("poi", Mapa.getInstance().getById(Long.parseLong( req.params("id")) ));
			return new ModelAndView(model, "admin/EliminarPOI.hbs");
	}
	public ModelAndView getModificar(Request req, Response res){
			Map<String, POI> model = new HashMap<>();
			model.put("poi", Mapa.getInstance().getById(Long.parseLong( req.params("id"))));
			return new ModelAndView(model, "admin/modificarPOI.hbs");
		}
		
	public ModelAndView verPOI(Request req, Response res){
	Map<String, POI> model = new HashMap<>();
		POI poi =Mapa.getInstance().getById(Long.parseLong( req.params("id")));
			model.put("poi",poi);
			return new ModelAndView(model, "admin/verPois/ver"+poi.getClass().getSimpleName()+".hbs");
	}
	public ModelAndView modificar(Request req, Response res){
		withTransaction(() -> {
			POI poi = Mapa.getInstance().getById(Long.parseLong( req.params("id")));
			poi.setNombre(req.queryParams("nombre"));
			poi.getDireccion().setCalle(req.queryParams("calle"));
			poi.getDireccion().setAltura(Integer.parseInt(req.queryParams("numero")));
			poi.getDireccion().setBarrio((req.queryParams("barrio")));
			RepositorioInterno.getInstance().agregarPoi(poi);
		});
			res.redirect("/pois");
			return null;
	}
}
