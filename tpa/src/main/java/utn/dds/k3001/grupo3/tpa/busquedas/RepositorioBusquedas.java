package utn.dds.k3001.grupo3.tpa.busquedas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepositorioBusquedas implements WithGlobalEntityManager{
private static final RepositorioBusquedas INSTANCE = new RepositorioBusquedas();
public static RepositorioBusquedas getInstance(){
	return INSTANCE;
}
private RepositorioBusquedas(){}
@SuppressWarnings("unchecked")
public Map<Terminal,List<Integer>> busquedasParcialesPorTerminal(){	
	return  ((List<Busqueda>) entityManager().createQuery("FROM Busqueda").getResultList()).stream()
			.collect(Collectors.groupingBy(busqueda -> busqueda.getTerminal(),Collectors.mapping(busqueda-> busqueda.getCantidadResultados(), Collectors.toList())));
}
@SuppressWarnings("unchecked")
public Map<Terminal,Integer> cantResultadosTotalesPorTerminal(){
	return ((List<Busqueda>) entityManager().createQuery("FROM Busqueda").getResultList()).stream()
			.collect(Collectors.groupingBy(busqueda -> busqueda.getTerminal(),Collectors.summingInt(busqueda-> busqueda.getCantidadResultados())));
}
@SuppressWarnings("unchecked")
public List<Busqueda> getBusquedas() {
	return ((List<Busqueda>) entityManager().createQuery("FROM Busqueda").getResultList());
}
@SuppressWarnings("unchecked")
public Map<LocalDate,Long> busquedasPorFecha(){
	return ((List<Busqueda>) entityManager().createQuery("FROM Busqueda").getResultList()).stream()
			.collect(Collectors.groupingBy(busqueda -> busqueda.getFecha(), Collectors.counting()));
}
public void buscar(Busqueda busqueda) {
	entityManager().persist(busqueda);
}
}
