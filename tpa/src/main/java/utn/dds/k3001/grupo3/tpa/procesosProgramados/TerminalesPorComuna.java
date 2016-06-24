package utn.dds.k3001.grupo3.tpa.procesosProgramados;

import java.util.List;
import java.util.stream.Collectors;
import utn.dds.k3001.grupo3.tpa.OrigenDeTerminales;
import utn.dds.k3001.grupo3.tpa.Terminal;

public class TerminalesPorComuna implements OrigenDeTerminales
{
	private OrigenDeTerminales origen;
	private String comunaBuscada;
	
	public TerminalesPorComuna(OrigenDeTerminales origen, String comunaBuscada){
		this.origen = origen;
		this.comunaBuscada = comunaBuscada;
	}
	@Override
	public List<Terminal> obtenerTerminales() {
		return origen.obtenerTerminales().stream().filter(Terminal -> Terminal.estaEnComuna(comunaBuscada)).collect(Collectors.toList());
	}
}
