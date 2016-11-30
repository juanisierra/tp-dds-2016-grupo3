package utn.dds.k3001.grupo3.tpa.controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import utn.dds.k3001.grupo3.tpa.busquedas.Busqueda;
import utn.dds.k3001.grupo3.tpa.busquedas.RepositorioBusquedas;
import utn.dds.k3001.grupo3.tpa.busquedas.RepositorioTerminales;
import utn.dds.k3001.grupo3.tpa.pois.POI;

public class BusquedasController
{
	public ModelAndView buscar(Request req, Response res)
	{
		List<Busqueda> busquedas = new LinkedList<Busqueda>();
		if(req.queryParams("terminal") != null)
		{
			Optional<String> terminal = req.queryParams("terminal").equals("Cualquiera") ? Optional.empty() : Optional.of(req.queryParams("terminal"));
			Optional<Integer> cantResultados = req.queryParams("cantResultados").equals("") ? Optional.empty() :  Optional.of(Integer.parseInt(req.queryParams("cantResultados")));
			Optional<LocalDate>  desde = req.queryParams("desde").equals("")  ? Optional.empty() : Optional.of(parsearFecha( req.queryParams("desde")));
			Optional<LocalDate>  hasta = req.queryParams("hasta").equals("")  ? Optional.empty() : Optional.of(parsearFecha( req.queryParams("hasta")));
			busquedas = RepositorioBusquedas.getInstance().busquedaWeb(terminal, cantResultados, desde, hasta);
		}
		List<String> terminales = new LinkedList<String>();
		terminales.add("Cualquiera");
		terminales.addAll(RepositorioTerminales.getInstance().getNombresTerminales());
		@SuppressWarnings("rawtypes")
		Map<String, List> model = new HashMap<>();
		model.put("busquedas", busquedas);
		model.put("terminales", terminales);
		return new ModelAndView(model, "admin/ListarBusquedas.hbs");
	}
	
	public ModelAndView mostrarPois(Request req, Response res)
	{
		Map<String, List<POI>> model = new HashMap<>();
		List<POI> pois = RepositorioBusquedas.getInstance().buscarPoisPorId((req.params("id")));
		model.put("pois", pois);
		return new ModelAndView(model, "admin/listarPoisBuscados.hbs");	
	}
	
	private LocalDate parsearFecha(String fechaString)
	{
		int dia =Integer.parseInt (fechaString.substring(8 ,10)) ;
		int mes =Integer.parseInt (fechaString.substring(5, 7)) ;
		int anio =Integer.parseInt (fechaString.substring(0,4)) ;
		return LocalDate.of(anio, mes, dia);
	}
}
