package utn.dds.k3001.grupo3.tpa.busquedas;

import java.util.LinkedList;
import java.util.List;

public class InMemoryTerminalOrigin implements TerminalOrigin{
	private List<Terminal> listaTerminales;
	
	public InMemoryTerminalOrigin() {
		listaTerminales = new LinkedList<Terminal>();
	}
	@Override
	public void agregarTerminal(Terminal terminal) {
		if(!listaTerminales.contains(terminal)) listaTerminales.add(terminal);
	}

	@Override
	public List<Terminal> obtenerTerminales() {
		return listaTerminales;
	}
	@Override
	public void reset() {
		listaTerminales = new LinkedList<Terminal>();
		
	}
	@Override
	public void remove(Terminal termABorrar) {
		listaTerminales.remove(termABorrar);
		
	}

}
