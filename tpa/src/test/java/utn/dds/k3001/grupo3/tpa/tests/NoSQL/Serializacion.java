package utn.dds.k3001.grupo3.tpa.tests.NoSQL;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import utn.dds.k3001.grupo3.tpa.geo.PersistablePoint;
import utn.dds.k3001.grupo3.tpa.pois.Banco;
import utn.dds.k3001.grupo3.tpa.pois.CGP;
import utn.dds.k3001.grupo3.tpa.pois.Comuna;
import utn.dds.k3001.grupo3.tpa.pois.Direccion;
import utn.dds.k3001.grupo3.tpa.pois.Disponibilidad;
import utn.dds.k3001.grupo3.tpa.pois.LocalComercial;
import utn.dds.k3001.grupo3.tpa.pois.POI;
import utn.dds.k3001.grupo3.tpa.pois.Rubro;
import utn.dds.k3001.grupo3.tpa.pois.Servicio;

public class Serializacion {
	@Test
public void testSerializar() throws IOException, ClassNotFoundException{
		
		Comuna comuna1 = new Comuna("comuna 1",null);
		Rubro libreria = new Rubro("libreria",50);
		Disponibilidad disponibilidadLibrerias = Disponibilidad.lunesAViernes(LocalTime.of(10,0), LocalTime.of(18,0));
		LocalComercial libreriaYenny = new LocalComercial("libreria yenny","Beiro","devoto",100,null,libreria,disponibilidadLibrerias);

	ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(byteArray);
        out.writeObject(libreriaYenny);
        out.close();
        String serializado = byteArray.toString("ISO-8859-1");
        ByteArrayInputStream entrada = new ByteArrayInputStream(serializado.getBytes("ISO-8859-1"));
        ObjectInputStream in = new ObjectInputStream(entrada);
        LocalComercial libreriaYenny2 = (LocalComercial) in.readObject();
        in.close();
	}
	@Test
public void testSerializarCGP() throws IOException, ClassNotFoundException{
		ArrayList<Servicio> listaServicios = new ArrayList<Servicio>();
		
		Servicio miCambioDeDomicilio = new Servicio("Cambio Domicilio",new Disponibilidad(LocalTime.of(8,0),LocalTime.of(16,0),Arrays.asList(DayOfWeek.of(1))));
		listaServicios.add(miCambioDeDomicilio);
		CGP miCGPCaballito = new CGP("CGP 1","Rivadavia 123","Caballito",0,null,new Comuna("1",null),listaServicios);
	ByteArrayOutputStream byteArray = new ByteArrayOutputStream();

		ObjectOutputStream out = new ObjectOutputStream(byteArray);
        out.writeObject(miCGPCaballito);
        out.close();
        String serializado = byteArray.toString("ISO-8859-1");
        ByteArrayInputStream entrada = new ByteArrayInputStream(serializado.getBytes("ISO-8859-1"));
        ObjectInputStream in = new ObjectInputStream(entrada);
        CGP cgp2 = (CGP) in.readObject();
        in.close();
	}
	@Test
public void testSerializarBancos() throws IOException, ClassNotFoundException{
		ArrayList<Servicio> listaServicios = new ArrayList<Servicio>();
		
		Servicio miCambioDeDomicilio = new Servicio("Cambio Domicilio",new Disponibilidad(LocalTime.of(8,0),LocalTime.of(16,0),Arrays.asList(DayOfWeek.of(1))));
		listaServicios.add(miCambioDeDomicilio);
		Banco banco1 = new Banco("asdasd","Rivadavia 123","Caballito",0,null);
		banco1.agregarServicio(miCambioDeDomicilio);
		banco1.agregarEtiqueta("asdasd");
		List<Banco> bancos = new LinkedList<Banco>();
		bancos.add(banco1);
	ByteArrayOutputStream byteArray = new ByteArrayOutputStream();

		ObjectOutputStream out = new ObjectOutputStream(byteArray);
        out.writeObject(bancos);
        out.close();
        String serializado = byteArray.toString("ISO-8859-1");
        ByteArrayInputStream entrada = new ByteArrayInputStream(serializado.getBytes("ISO-8859-1"));
        ObjectInputStream in = new ObjectInputStream(entrada);
        List<Banco> bancostraidos = (List<Banco>) in.readObject();
        in.close();
	}
	//@Test Solo funciona con redis levantado
public void testSerializarBancosConRedis() throws IOException, ClassNotFoundException{
		ArrayList<Servicio> listaServicios = new ArrayList<Servicio>();
		
		Servicio miCambioDeDomicilio = new Servicio("Cambio Domicilio",new Disponibilidad(LocalTime.of(8,0),LocalTime.of(16,0),Arrays.asList(DayOfWeek.of(1))));
		listaServicios.add(miCambioDeDomicilio);
		Banco banco1 = new Banco("asdasd","Rivadavia 123","Caballito",0,null);
		banco1.agregarServicio(miCambioDeDomicilio);
		banco1.agregarEtiqueta("asdasd");
		List<Banco> bancos = new LinkedList<Banco>();
		bancos.add(banco1);
	ByteArrayOutputStream byteArray = new ByteArrayOutputStream();

		ObjectOutputStream out = new ObjectOutputStream(byteArray);
        out.writeObject(bancos);
        out.close();
        String serializado = byteArray.toString("ISO-8859-1");
        Jedis jedis = new Jedis("localhost");
        jedis.set("bancos", serializado);
        String value = jedis.get("bancos");
        ByteArrayInputStream entrada = new ByteArrayInputStream(value.getBytes("ISO-8859-1"));
        ObjectInputStream in = new ObjectInputStream(entrada);
        List<Banco> bancostraidos = (List<Banco>) in.readObject();
        in.close();
	}
}
