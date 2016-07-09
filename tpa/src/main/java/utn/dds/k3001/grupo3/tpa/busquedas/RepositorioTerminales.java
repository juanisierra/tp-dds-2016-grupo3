package utn.dds.k3001.grupo3.tpa.busquedas;

import java.util.ArrayList;
import java.util.List;

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
	public List<Terminal> obtenerTerminales()
	{
		return listaTerminales;
	}
}
