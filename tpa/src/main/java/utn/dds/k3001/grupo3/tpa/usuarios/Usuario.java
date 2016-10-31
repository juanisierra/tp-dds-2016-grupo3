package utn.dds.k3001.grupo3.tpa.usuarios;

import utn.dds.k3001.grupo3.tpa.busquedas.Terminal;

public class Usuario {
	private String nombre;
	private String contrasenia;
	private TipoUsuario tipo;
	private Terminal terminal;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public TipoUsuario getTipo() {
		return tipo;
	}
	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}
	public Terminal getTerminal() {
		return terminal;
	}
	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
	}
	
}
