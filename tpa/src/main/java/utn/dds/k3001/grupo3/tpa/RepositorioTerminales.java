package utn.dds.k3001.grupo3.tpa;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RepositorioTerminales{
	
	private List<Terminal> terminales;
	public RepositorioTerminales(){
		this.terminales =  new LinkedList<Terminal>();
	}
	
	public void  agregarTerminal(Terminal terminal){
		terminales.add(terminal);
	}
	
	public void eliminarTerminal(Terminal terminal){
		terminales.remove(terminal);
	}
	
	public Map<Terminal,List<Integer>> busquedasParcialesPorTerminal(){//TODO ver si este metodo es necesario o basta con resultadosParcialesDeBusquedas()
		return  terminales.stream().collect(Collectors.toMap( terminal -> terminal, terminal -> terminal.cantResultadosParcialesDeBusquedas()));
	}
	
	public Map<Terminal,Integer> cantResultadosTotalesPorTerminal(){
		return  terminales.stream().collect(Collectors.toMap( terminal -> terminal, terminal -> terminal.cantResultadosTotalesDeBusquedas()));
	}
	
	public Map<LocalDate,Long> busquedasPorFecha(){
		return terminales.stream()
				         .flatMap(terminal -> terminal.getBusquedas().stream())
				         .collect(Collectors.groupingBy(busqueda -> busqueda.getFecha(), Collectors.counting()));
	}
}
