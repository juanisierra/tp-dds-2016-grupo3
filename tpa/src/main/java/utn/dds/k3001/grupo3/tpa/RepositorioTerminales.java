package utn.dds.k3001.grupo3.tpa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioTerminales implements OrigenDeTerminales{
	List<Terminal> listaTerminales;
	public RepositorioTerminales ()
	{
		listaTerminales = new ArrayList<Terminal>();
	} 
	public List<Terminal>filtrarPorComuna(String nombreComuna)
	{
		return listaTerminales.stream().filter(Terminal -> Terminal.estaEnComuna(nombreComuna)).collect(Collectors.toList());
	}
	public List<Terminal> obtenerTerminales()
	{
		return listaTerminales;
	}
}
