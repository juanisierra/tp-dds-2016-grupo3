package utn.dds.k3001.grupo3.tpa.busquedas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepositorioBusquedas implements WithGlobalEntityManager{
private static final RepositorioBusquedas INSTANCE = new RepositorioBusquedas();
private List<Busqueda> listaBusquedas = new LinkedList<Busqueda>();
public static RepositorioBusquedas getInstance(){
	return INSTANCE;
}
private RepositorioBusquedas(){}
@SuppressWarnings("unchecked")
public Map<Terminal,List<Integer>> busquedasParcialesPorTerminal(){	
	return  listaBusquedas.stream()
			.collect(Collectors.groupingBy(busqueda -> busqueda.getTerminal(),Collectors.mapping(busqueda-> busqueda.getCantidadResultados(), Collectors.toList())));
}
@SuppressWarnings("unchecked")
public Map<Terminal,Integer> cantResultadosTotalesPorTerminal(){
	return (listaBusquedas.stream()
			.collect(Collectors.groupingBy(busqueda -> busqueda.getTerminal(),Collectors.summingInt(busqueda-> busqueda.getCantidadResultados()))));
}
@SuppressWarnings("unchecked")
public List<Busqueda> getBusquedas() {
	return listaBusquedas;
}
@SuppressWarnings("unchecked")
public Map<LocalDate,Long> busquedasPorFecha(){
	return  listaBusquedas.stream()
			.collect(Collectors.groupingBy(busqueda -> busqueda.getFecha(), Collectors.counting()));
}
public void buscar(Busqueda busqueda) {
	listaBusquedas.add(busqueda);
}
public  void reset() {
	listaBusquedas = new LinkedList<Busqueda>();
	
}
}
