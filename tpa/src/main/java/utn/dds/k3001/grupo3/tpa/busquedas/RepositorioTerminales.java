package utn.dds.k3001.grupo3.tpa.busquedas;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;



public class RepositorioTerminales implements OrigenDeTerminales{
	List<Terminal> listaTerminales;
	public RepositorioTerminales ()
	{
		listaTerminales = new ArrayList<Terminal>();
	} 
	public RepositorioTerminales (List<Terminal> listaTerminales)
	{
		this.listaTerminales = listaTerminales;
	} 
	public void agregarTerminal(Terminal terminal){
		listaTerminales.add(terminal);
	}
	public List<Terminal> obtenerTerminales()
	{
		return listaTerminales;
	}
	@SuppressWarnings("unchecked")
	public List<Terminal> obtenerTerminalesPersistidas(){
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		return (List<Terminal>) em.createQuery("FROM Terminal").getResultList();
		
	}
	public void persistirTerminales(){
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		listaTerminales.forEach(terminal -> em.persist(terminal));
	}
}
