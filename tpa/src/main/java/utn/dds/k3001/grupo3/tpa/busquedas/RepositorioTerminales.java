package utn.dds.k3001.grupo3.tpa.busquedas;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;



public class RepositorioTerminales implements OrigenDeTerminales, WithGlobalEntityManager{
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
		return (List<Terminal>) entityManager().createQuery("FROM Terminal").getResultList();
		
	}
	public void persistirTerminales(){
		listaTerminales.forEach(terminal -> entityManager().persist(terminal));
	}
}
