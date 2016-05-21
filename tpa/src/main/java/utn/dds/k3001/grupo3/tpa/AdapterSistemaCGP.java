package utn.dds.k3001.grupo3.tpa;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.uqbar.geodds.Point;
import utn.dds.k3001.grupo3.tpa.DTO.*;

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
		return new CGP(String.join(" ","CGP",Integer.valueOf(cgpDTO.getNumComuna()).toString()),cgpDTO.getDomicilio(),cgpDTO.getZonas(),0,new Point(0,0),new Comuna(new Integer(cgpDTO.getNumComuna()).toString()),(ArrayList<Servicio>) this.adaptarServicios(cgpDTO.getListaServicios()));
	}
	public List<POI> buscar(String criterio){
		return 	sistema.buscarPOIS("").stream().map(cgpDTO -> adaptarCGP(cgpDTO)).filter(CGP -> CGP.esBuscado(criterio)).collect(Collectors.toList());
	}
}
