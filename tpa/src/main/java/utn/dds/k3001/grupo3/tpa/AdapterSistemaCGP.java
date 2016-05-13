package utn.dds.k3001.grupo3.tpa;


import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.uqbar.geodds.Point;

import utn.dds.k3001.grupo3.tpa.DTO.*;

public class AdapterSistemaCGP implements OrigenDeDatos{
	SistemaCGP sistema;
	public AdapterSistemaCGP(SistemaCGP sistema){
		this.sistema = sistema;
	}
	public List<Servicio> adaptarServicios(List<ServiciosDTO> servicios){
		LinkedList<Servicio> serviciosLocales = new LinkedList<Servicio>();
		servicios.forEach(servicio -> serviciosLocales.add(new Servicio(servicio.getNombre(),adaptarDisponibilidades(servicio.getHorarios()))));
		return serviciosLocales;
	}
	public List<Disponibilidad> adaptarDisponibilidades(List<RangosServicioDTO> rangoDTO) {
		LinkedList<Disponibilidad>listaDisponibilidades = new LinkedList<Disponibilidad>();
		rangoDTO.forEach(rango -> listaDisponibilidades.add(
				new Disponibilidad(LocalTime.of(rango.getHorarioDesde(),rango.getMinutosDesde()),
						LocalTime.of(rango.getHorarioHasta(),rango.getMinutosHasta()),Arrays.asList(DayOfWeek.of(rango.getNumeroDia()))))
						);
		return listaDisponibilidades;
	}
	public CGP adaptarCGP(CentroDTO cgpDTO){//TODO Adaptar demas datos, chequear comunas
		return new CGP("",cgpDTO.getDomicilio(),cgpDTO.getZonas(),0,new Point(0,0),new Comuna(new Integer(cgpDTO.getNumComuna()).toString()));
	}
	public List<POI> buscar(String criterio){
		List<CentroDTO> listaOriginal = sistema.buscarPOIS(criterio);
		List<POI> listaCGP = new LinkedList<POI>();
		listaOriginal.forEach(cgpDTO -> listaCGP.add(adaptarCGP(cgpDTO)));
		return listaCGP;		
	}
}
