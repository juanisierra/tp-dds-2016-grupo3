package utn.dds.k3001.grupo3.tpa.origenesDePOIS;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import redis.clients.jedis.Jedis;
import utn.dds.k3001.grupo3.tpa.pois.POI;

public class OrigenSistemaExternoConCache<T> implements OrigenDeDatos {
	
	private OrigenDeDatos origen;
	private LocalDateTime ultimaActualizacion = LocalDateTime.of(1,1,1,0,0,0);
	private int horasRefresh;
	private Jedis jedis;
	private String nombreClase;
	
	public OrigenSistemaExternoConCache(OrigenDeDatos origen,String nombreClase,int horasActualizacion) {
		this.origen = origen;
		this.jedis = new Jedis("localhost");
		this.nombreClase = nombreClase;
		this.horasRefresh = horasActualizacion;
	}
	@Override
	public List<POI> buscar(String criterio) throws IOException, ClassNotFoundException {
		if(ultimaActualizacion.plusHours(horasRefresh).isBefore(LocalDateTime.now()) || ultimaActualizacion.equals(LocalDateTime.of(1,1,1,0,0,0))) {
			ultimaActualizacion = LocalDateTime.now();
			ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(byteArray);
			List<POI> pois = origen.buscar("");
			List<T> listaT= new LinkedList<T>();
			pois.stream().forEach(t -> listaT.add((T) t) );;
	        out.writeObject(listaT);
	        out.close();
	        String serializado = byteArray.toString("ISO-8859-1");
	        jedis.set(nombreClase, serializado);
	        return  (List<POI>) listaT;
		} else {
	        String value = jedis.get(nombreClase);
	        ByteArrayInputStream entrada = new ByteArrayInputStream(value.getBytes("ISO-8859-1"));
	        ObjectInputStream in = new ObjectInputStream(entrada);
	        List<POI> elementosTraidos = (List<POI>) in.readObject();
	        in.close();
	        return elementosTraidos;
		}
		}
	}