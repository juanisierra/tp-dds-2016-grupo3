package utn.dds.k3001.grupo3.tpa.server;

import java.time.LocalTime;
import java.util.Arrays;

import spark.Spark;
import spark.debug.DebugScreen;
import utn.dds.k3001.grupo3.tpa.busquedas.AccionesBusqueda;
import utn.dds.k3001.grupo3.tpa.busquedas.Mapa;
import utn.dds.k3001.grupo3.tpa.busquedas.RepositorioTerminales;
import utn.dds.k3001.grupo3.tpa.busquedas.Terminal;
import utn.dds.k3001.grupo3.tpa.geo.PersistablePoint;
import utn.dds.k3001.grupo3.tpa.pois.Banco;
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
		
		Comuna comuna1 = new Comuna("Montecastro",Arrays.asList(new PersistablePoint(0,0), new PersistablePoint(0,11), new PersistablePoint(11,11), new PersistablePoint (11,0)));
		
		Terminal terminal1 = new Terminal("Terminal 1",Mapa.getInstance(),comuna1);
		RepositorioTerminales.getInstance().agregarTerminal(terminal1);
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
		libreriaYenny.setRubro(new Rubro("asd", 2));
		ParadaColectivo parada114 = new ParadaColectivo("parada 114","Chivilcoy","devoto",1000,new PersistablePoint(10,10),114);
		parada114.setId(2);
		CGP cgp1 = new CGP("cgp2","beiro","caballito",100,new PersistablePoint(10.1,10.1),comuna1);
		cgp1.setId(1);
		Servicio altaDomicilio = new Servicio("alta domicilio",disponibilidadLibrerias);
		Servicio servicio2 = new Servicio("otroServicio",disponibilidadLibrerias);
		cgp1.agregarServicio(altaDomicilio);
		Banco banco1 = new Banco("banco","call1","devoto",23,new PersistablePoint(20,20));
		banco1.agregarServicio(altaDomicilio);
		banco1.agregarServicio(servicio2);
		banco1.setId(5);
		Mapa.getInstance().agregarPoi(banco1);
		Mapa.getInstance().agregarPoi(parada114);
		Mapa.getInstance().agregarPoi(libreriaYenny);
		Mapa.getInstance().agregarPoi(cgp1);
		
		Terminal terminal = new Terminal("miTerminal", Mapa.getInstance());   //para que haya datos que mostrar, dsp se borra
		terminal.agregarObserverBusqueda(AccionesBusqueda.GUARDARBUSQUEDA);
		terminal.buscar("parada");
		terminal.buscar("a");
		
		RepositorioUsuarios.instance().agregarUsuario(t);
		RepositorioUsuarios.instance().agregarUsuario(u);
		DebugScreen.enableDebugScreen();
		Router.configure();
	}
}
