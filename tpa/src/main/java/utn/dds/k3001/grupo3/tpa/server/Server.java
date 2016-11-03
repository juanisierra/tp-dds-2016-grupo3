package utn.dds.k3001.grupo3.tpa.server;

import java.time.LocalTime;
import java.util.Arrays;

import spark.Spark;
import spark.debug.DebugScreen;
import utn.dds.k3001.grupo3.tpa.busquedas.Mapa;
import utn.dds.k3001.grupo3.tpa.busquedas.RepositorioTerminales;
import utn.dds.k3001.grupo3.tpa.busquedas.Terminal;
import utn.dds.k3001.grupo3.tpa.geo.PersistablePoint;
import utn.dds.k3001.grupo3.tpa.pois.CGP;
import utn.dds.k3001.grupo3.tpa.pois.Comuna;
import utn.dds.k3001.grupo3.tpa.pois.Disponibilidad;
import utn.dds.k3001.grupo3.tpa.pois.LocalComercial;
import utn.dds.k3001.grupo3.tpa.pois.ParadaColectivo;
import utn.dds.k3001.grupo3.tpa.pois.Rubro;
import utn.dds.k3001.grupo3.tpa.pois.Servicio;
import utn.dds.k3001.grupo3.tpa.usuarios.RepositorioUsuarios;
import utn.dds.k3001.grupo3.tpa.usuarios.Usuario;
import utn.dds.k3001.grupo3.tpa.usuarios.UsuarioTerminal;

public class Server {
	public static void main(String[] args) {
		Spark.port(9000);
		
		Comuna comuna1 = new Comuna("comuna 1",Arrays.asList(new PersistablePoint(0,0), new PersistablePoint(0,11), new PersistablePoint(11,11), new PersistablePoint (11,0)));
		
		Terminal terminal1 = new Terminal("Terminal 1",Mapa.getInstance(),comuna1);
		RepositorioTerminales.INSTANCE.agregarTerminal(terminal1);
		Usuario u = new Usuario();
		u.setNombre("admin");
		u.setContrasenia("123");
		UsuarioTerminal t = new UsuarioTerminal();
		t.setNombre("terminal");
		t.setContrasenia("123");
		t.setTerminal(terminal1);
		//CREAMOS POIS
		
		Rubro libreria = new Rubro("libreria",50);
		Disponibilidad disponibilidadLibrerias = Disponibilidad.lunesAViernes(LocalTime.of(10,0), LocalTime.of(18,0));
		LocalComercial libreriaYenny = new LocalComercial("libreria yenny","Beiro","devoto",100,new PersistablePoint(10,10),libreria,disponibilidadLibrerias);
		libreriaYenny.setId(0);
		ParadaColectivo parada114 = new ParadaColectivo("parada 114","Chivilcoy","devoto",1000,new PersistablePoint(10,10),114);
		parada114.setId(2);
		CGP cgp1 = new CGP("cgp2","beiro","caballito",100,new PersistablePoint(10.1,10.1),comuna1);
		cgp1.setId(1);
		Servicio altaDomicilio = new Servicio("alta domicilio",disponibilidadLibrerias);
		cgp1.agregarServicio(altaDomicilio);
		Mapa.getInstance().agregarPoi(parada114);
		Mapa.getInstance().agregarPoi(libreriaYenny);
		Mapa.getInstance().agregarPoi(cgp1);
		
		RepositorioUsuarios.instance().agregarUsuario(t);
		RepositorioUsuarios.instance().agregarUsuario(u);
		DebugScreen.enableDebugScreen();
		Router.configure();
	}
}