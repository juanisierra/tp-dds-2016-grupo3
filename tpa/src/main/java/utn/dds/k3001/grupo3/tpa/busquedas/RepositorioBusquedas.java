package utn.dds.k3001.grupo3.tpa.busquedas;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepositorioBusquedas implements WithGlobalEntityManager{
	
	private static final RepositorioBusquedas INSTANCE = new RepositorioBusquedas();
	private static int id = 0;
	private BusquedasOrigin origen = new InMemoryBusquedasOrigin();
	//private BusquedasOrigin origen =  new PersistenceBusquedasOrigin();
	
	public static RepositorioBusquedas getInstance(){
		return INSTANCE;
	}
	private RepositorioBusquedas(){}
	
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
		busqueda.setId(++id);
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
	
	public List<Busqueda> busquedasDesdeHasta(LocalDate desde, LocalDate hasta){
		return origen.getBusquedas().stream().filter(busqueda -> busqueda.estaEntre(desde, hasta)).collect(Collectors.toList());
	}
	
	public List<Busqueda> busquedasCantResultados(int cantidad){
		return origen.getBusquedas().stream().filter(busqueda -> busqueda.getCantidadResultados() == cantidad).collect(Collectors.toList());
	}
	
	public List<Busqueda> busquedasTerminal(Terminal terminal){
		return origen.getBusquedas().stream().filter(busqueda -> busqueda.getTerminal().getId() == terminal.getId()).collect(Collectors.toList());
	}
	
	public List<Busqueda> busquedasTerminal(String nombreTerminal){
		return origen.getBusquedas().stream().filter(busqueda -> busqueda.getTerminal().getNombre().equals(nombreTerminal)).collect(Collectors.toList());
	}
	
	public Busqueda busquedaConId(int id){
		return origen.getBusquedas().stream().filter(b -> b.getId() == id).findAny().orElseGet(null);
	}
}













