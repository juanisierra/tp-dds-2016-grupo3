package utn.dds.k3001.grupo3.tpa.server;

import spark.Spark;
import spark.debug.DebugScreen;
import utn.dds.k3001.grupo3.tpa.usuarios.RepositorioUsuarios;
import utn.dds.k3001.grupo3.tpa.usuarios.TipoUsuario;
import utn.dds.k3001.grupo3.tpa.usuarios.Usuario;

public class Server {
	public static void main(String[] args) {
		Spark.port(9000);
		Usuario u = new Usuario();
		u.setNombre("admin");
		u.setContrasenia("123");
		u.setTipo(TipoUsuario.ADMINISTRADOR);
		Usuario t = new Usuario();
		t.setNombre("terminal");
		t.setContrasenia("123");
		t.setTipo(TipoUsuario.TERMINAL);
		RepositorioUsuarios.instance().agregarUsuario(t);
		RepositorioUsuarios.instance().agregarUsuario(u);
		DebugScreen.enableDebugScreen();
		Router.configure();
	}
}
