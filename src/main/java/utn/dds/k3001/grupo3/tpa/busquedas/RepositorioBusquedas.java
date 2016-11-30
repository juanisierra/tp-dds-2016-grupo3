package utn.dds.k3001.grupo3.tpa.busquedas;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import utn.dds.k3001.grupo3.tpa.pois.POI;

public class RepositorioBusquedas {
	
	private static final RepositorioBusquedas INSTANCE = new RepositorioBusquedas();
	private BusquedasOrigin origen = new InMemoryBusquedasOrigin();
	public static RepositorioBusquedas getInstance(){
		return INSTANCE;
	}
	private RepositorioBusquedas(){
	}
	
	public Map<String,List<Integer>> busquedasParcialesPorTerminal(){	
		return  origen.getBusquedas().stream()
				.collect(Collectors.groupingBy(busqueda -> busqueda.getTerminal().getNombre(),Collectors.mapping(busqueda-> busqueda.getCantidadResultados(), Collectors.toList())));
	}
	
	public Map<String,Integer> cantResultadosTotalesPorTerminal(){
		return (origen.getBusquedas().stream()
				.collect(Collectors.groupingBy(busqueda -> busqueda.getTerminal().getNombre(),Collectors.summingInt(busqueda-> busqueda.getCantidadResultados()))));
	}
	
	public List<Busqueda> getBusquedas() {
		return origen.getBusquedas();
	}
	
	public Map<LocalDate,Long> busquedasPorFecha(){
		return  origen.getBusquedas().stream()
				.collect(Collectors.groupingBy(busqueda -> busqueda.getFecha(), Collectors.counting()));
	}
	public void buscar(Busqueda busqueda) {
		origen.addBusqueda(busqueda);
	}
	
	public  void reset() {
		origen.reset();
	}
	public void setInMemory() {
		origen = new InMemoryBusquedasOrigin();
	}
	public void setPersistence() {
		origen = new PersistenceBusquedasOrigin();
	}
	
	public List<POI> buscarPoisPorId(String id){
		Busqueda b =  origen.getBusquedas()
									    .stream()
									    .filter(busqueda -> busqueda.getId().equals(id)).findFirst().get();
		
		return b.getResultados();
	}
	
	public List<Busqueda> busquedaWeb(Optional<String> terminal, Optional<Integer> cantResultados, Optional<LocalDate>  desde, Optional<LocalDate>  hasta){
		return this.getBusquedas()
								 .stream()
								 .filter(busq -> desde.map( dsd -> busq.esDespues(dsd)).orElse(true))
								 .filter(busq -> hasta.map(ht  -> busq.esAntes(ht)).orElse(true))
								 .filter(busq -> terminal.map(ter  -> busq.esDeTerminal(ter)).orElse(true))
								 .filter(busq -> cantResultados.map(cant -> busq.getCantidadResultados() == cant ).orElse(true))
								 .collect(Collectors.toList());
	}
}
