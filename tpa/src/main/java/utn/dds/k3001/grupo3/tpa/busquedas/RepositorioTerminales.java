package utn.dds.k3001.grupo3.tpa.busquedas;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;



public class RepositorioTerminales implements OrigenDeTerminales, WithGlobalEntityManager{
	List<Terminal> listaTerminales;
	public static RepositorioTerminales INSTANCE = new RepositorioTerminales();
	public static RepositorioTerminales getInstance(){
		return INSTANCE;
	}
	private RepositorioTerminales ()
	{
		listaTerminales = new ArrayList<Terminal>();
	} 
	public void agregarTerminal(Terminal terminal){
		//listaTerminales.add(terminal);
		entityManager().persist(terminal);
	}
	@SuppressWarnings("unchecked")
	public List<Terminal> obtenerTerminales()
	{	
		return (List<Terminal>) entityManager().createQuery("FROM Terminal").getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Terminal> obtenerTerminalesPersistidas(){
		return (List<Terminal>) entityManager().createQuery("FROM Terminal").getResultList();
		
	}
	public void persistirTerminales(){
		listaTerminales.forEach(terminal -> entityManager().persist(terminal));
	}
}
