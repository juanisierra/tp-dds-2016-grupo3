package utn.dds.k3001.grupo3.tpa.busquedas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RepositorioBusquedas{
private List<Busqueda> listaBusquedas;
private static final RepositorioBusquedas INSTANCE = new RepositorioBusquedas();
public static RepositorioBusquedas getInstance(){
	return INSTANCE;
}
public void resetRepositorio(){
	listaBusquedas.clear();
}
private RepositorioBusquedas()
{
	listaBusquedas = new ArrayList<Busqueda>();
}
public Map<Terminal,List<Integer>> busquedasParcialesPorTerminal(){	
	return  listaBusquedas.stream()
			.collect(Collectors.groupingBy(busqueda -> busqueda.getTerminal(),Collectors.mapping(busqueda-> busqueda.getCantidadResultados(), Collectors.toList())));
}
public Map<Terminal,Integer> cantResultadosTotalesPorTerminal(){
	return listaBusquedas.stream()
			.collect(Collectors.groupingBy(busqueda -> busqueda.getTerminal(),Collectors.summingInt(busqueda-> busqueda.getCantidadResultados())));
}
public Map<LocalDate,Long> busquedasPorFecha(){
	return listaBusquedas.stream()
			.collect(Collectors.groupingBy(busqueda -> busqueda.getFecha(), Collectors.counting()));
}
public void buscar(Busqueda busqueda) {
	listaBusquedas.add(busqueda);
}
}
