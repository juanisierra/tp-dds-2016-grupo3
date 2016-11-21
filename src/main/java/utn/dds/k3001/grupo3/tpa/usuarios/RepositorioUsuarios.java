package utn.dds.k3001.grupo3.tpa.usuarios;

import java.util.LinkedList;
import java.util.List;

public class RepositorioUsuarios {
	private List<Usuario> usuarios;
	private static RepositorioUsuarios INSTANCE = new RepositorioUsuarios();
	private RepositorioUsuarios()
	{
	this.usuarios = new LinkedList<Usuario>();
	}
	public static RepositorioUsuarios instance()
	{
		return INSTANCE;
	}
	public void agregarUsuario(Usuario u)
	{
		usuarios.add(u);
	}
	public Boolean validarUsuario(String nombre,String contra )
	{
		return usuarios.stream().anyMatch(u -> u.getNombre().equals(nombre) && u.getContrasenia().equals(contra));
	}
	public Usuario getUsuario(String id)
	{
	return usuarios.stream().filter(usuario -> usuario.getNombre().equals(id)).findFirst().get();
	}
}
