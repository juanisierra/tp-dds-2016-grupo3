package utn.dds.k3001.grupo3.tpa.procesosProgramados;

import java.util.List;
import utn.dds.k3001.grupo3.tpa.OrigenDeTerminales;
import utn.dds.k3001.grupo3.tpa.Terminal;

public class ListaTerminales implements OrigenDeTerminales
{
	private List<Terminal> terminales;
	
	public ListaTerminales(List<Terminal> listaTerminales){
		this.terminales = listaTerminales;
	}
	@Override
	public List<Terminal> obtenerTerminales() {
		return terminales;
	}
}
