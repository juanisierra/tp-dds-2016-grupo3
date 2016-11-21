package utn.dds.k3001.grupo3.tpa.busquedas;

import java.util.List;
import java.util.stream.Collectors;

public class RepositorioTerminales implements OrigenDeTerminales{
	
	private static RepositorioTerminales INSTANCE = new RepositorioTerminales();
	private static TerminalOrigin origen = new InMemoryTerminalOrigin();
	
	public static RepositorioTerminales getInstance(){
		return INSTANCE;
	}
	private RepositorioTerminales (){}
	
	public void agregarTerminal(Terminal terminal){
		origen.agregarTerminal(terminal);
	}
	public void eliminarTerminal(Terminal terminal){
		origen.obtenerTerminales().remove(terminal);
	}
	
	public void eliminarTerminalPorId(int id){
		Terminal termABorrar = buscarTerminalPorId(id);
		if (termABorrar!=null)
			origen.remove(termABorrar);
	}
	
	public List<Terminal> obtenerTerminales()
	{	
		return origen.obtenerTerminales();
	}
	
	public List<Terminal> buscarTerminalesPorNombre(String nombre)
	{	
		return origen.obtenerTerminales().stream().filter(terminal -> terminal.getNombre().equals(nombre)).collect(Collectors.toList());
	}
	
	public List<Terminal> buscarTerminalesPorComuna(String nombre)
	{	
		return origen.obtenerTerminales().stream().filter(terminal -> terminal.getComuna().getNombre().equals(nombre)).collect(Collectors.toList());
	}
	
	public Terminal buscarTerminalPorId(int id)
	{	
		return origen.obtenerTerminales().stream().filter(terminal -> terminal.getId()==id).findAny().orElse(null);
	}
	
	public List<String>  getNombresTerminales(){
		return origen.obtenerTerminales().stream().map(terminal -> terminal.getNombre()).collect(Collectors.toList());
	}
	
	public static void persistirEnMemoria(){
		origen = new InMemoryTerminalOrigin();
	}
	
	public static void persistirEnBD(){
		origen = new PersistanceTerminalOrigin();
	}
	
	public static void reset() {
		origen.reset();
	}
}
