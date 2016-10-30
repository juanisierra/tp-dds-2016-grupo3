package utn.dds.k3001.grupo3.tpa.busquedas;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;



public class RepositorioTerminales implements OrigenDeTerminales{
	
	public static RepositorioTerminales INSTANCE = new RepositorioTerminales();
	public static TerminalOrigin origen = new InMemoryTerminalOrigin();
	
	public static RepositorioTerminales getInstance(){
		return INSTANCE;
	}
	private RepositorioTerminales (){}
	
	public void agregarTerminal(Terminal terminal){
		origen.agregarTerminal(terminal);
	}
	
	public List<Terminal> obtenerTerminales()
	{	
		return origen.obtenerTerminales();
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
