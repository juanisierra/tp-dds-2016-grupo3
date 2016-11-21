package utn.dds.k3001.grupo3.tpa.usuarios;

import utn.dds.k3001.grupo3.tpa.busquedas.Terminal;

public class UsuarioTerminal extends Usuario{
	private Terminal terminal;

	public Terminal getTerminal() {
		return terminal;
	}

	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
	}
	@Override
	public Boolean esAdmin()
	{
		return false;
	}
}
