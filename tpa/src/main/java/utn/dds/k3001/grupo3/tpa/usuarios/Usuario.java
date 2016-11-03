package utn.dds.k3001.grupo3.tpa.usuarios;

import utn.dds.k3001.grupo3.tpa.busquedas.Terminal;

public class Usuario {
	private String nombre;
	private String contrasenia;	
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
	public Boolean esAdmin()
	{
		return true;
	}
}
