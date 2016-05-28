package utn.dds.k3001.grupo3.tpa;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RepositorioTerminales
{
	private List<Terminal> terminales;
	
	public RepositorioTerminales()
	{
		this.terminales =  new LinkedList<Terminal>();
	}
	public void  agregar(Terminal terminal)
	{
		terminales.add(terminal);
	}
	public Map<Terminal,List<Integer>> busquedasParcialesPorTerminal(){
		return  terminales.stream().collect(Collectors.toMap( terminal -> terminal, terminal -> terminal.busquedasParciales()));
	}
	public Map<Terminal,Integer> busquedasTotalesPorTerminal(){
		return  terminales.stream().collect(Collectors.toMap( terminal -> terminal, terminal -> terminal.busquedasTotales()));
	}
}
