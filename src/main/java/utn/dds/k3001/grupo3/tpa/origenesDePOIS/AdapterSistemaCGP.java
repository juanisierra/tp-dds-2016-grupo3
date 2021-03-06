package utn.dds.k3001.grupo3.tpa.origenesDePOIS;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import utn.dds.k3001.grupo3.tpa.geo.*;
import utn.dds.k3001.grupo3.tpa.DTO.*;
import utn.dds.k3001.grupo3.tpa.busquedas.filtros.FiltroPOI;
import utn.dds.k3001.grupo3.tpa.pois.CGP;
import utn.dds.k3001.grupo3.tpa.pois.Comuna;
import utn.dds.k3001.grupo3.tpa.pois.Disponibilidad;
import utn.dds.k3001.grupo3.tpa.pois.POI;
import utn.dds.k3001.grupo3.tpa.pois.Servicio;

public class AdapterSistemaCGP implements OrigenDeDatos{
	
	SistemaCGP sistema;
	
	public AdapterSistemaCGP(SistemaCGP sistema){
		this.sistema = sistema;
	}
	
	public List<Servicio> adaptarServicios(List<ServiciosDTO> servicios){
		return servicios.stream().map(servicio -> new Servicio(servicio.getNombre(),adaptarDisponibilidades(servicio.getHorarios()))).collect(Collectors.toList());
	}
	
	public List<Disponibilidad> adaptarDisponibilidades(List<RangosServicioDTO> rangoDTO) {
		return rangoDTO.stream().map(rango -> new Disponibilidad(LocalTime.of(rango.getHorarioDesde(),rango.getMinutosDesde()),
		LocalTime.of(rango.getHorarioHasta(),rango.getMinutosHasta()),Arrays.asList(DayOfWeek.of(rango.getNumeroDia())))).collect(Collectors.toList());
	}
	
	public CGP adaptarCGP(CentroDTO cgpDTO){//TODO Adaptar demas datos, chequear comunas
		return new CGP(String.join(" ","CGP",Integer.valueOf(cgpDTO.getNumComuna()).toString()),cgpDTO.getDomicilio(),cgpDTO.getZonas(),0,new PersistablePoint(0,0),new Comuna(new Integer(cgpDTO.getNumComuna()).toString()),(ArrayList<Servicio>) this.adaptarServicios(cgpDTO.getListaServicios()));
	}
	
	public List<POI> buscar(String criterio){
		return 	sistema.buscarPOIS("").stream().map(cgpDTO -> adaptarCGP(cgpDTO)).filter(CGP -> CGP.esBuscado(criterio)).collect(Collectors.toList());
	}
	public List<POI> buscar(List<FiltroPOI> filtros) 
	{
		return 	sistema.buscarPOIS("").stream().map(cgpDTO -> adaptarCGP(cgpDTO)).filter(CGP -> filtros.stream().allMatch(f -> f.filtrar(CGP))).collect(Collectors.toList());
		
	}
}
