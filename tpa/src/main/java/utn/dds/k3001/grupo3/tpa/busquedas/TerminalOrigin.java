package utn.dds.k3001.grupo3.tpa.busquedas;

import java.util.List;

public interface TerminalOrigin {
	public void agregarTerminal(Terminal terminal);
	public List<Terminal> obtenerTerminales();
	public void reset();
}
