package utn.dds.k3001.grupo3.tpa.busquedas;

import java.util.List;
import java.util.stream.Collectors;

import utn.dds.k3001.grupo3.tpa.pois.Comuna;

public class RepositorioComunas {

	private final static RepositorioComunas INSTANCE = new RepositorioComunas();
	private static ComunasOrigin origen = new InMemoryComunasOrigin();
	
	public static RepositorioComunas getInstance(){
		return INSTANCE;
	}
	
	public List<Comuna> getComunas(){
		return origen.obtenerComunas();
	}
	
	public void agregarComuna(Comuna comuna){
		origen.agregarComuna(comuna);
	}
	
	public void eliminarComuna(Comuna comuna){
		origen.obtenerComunas().remove(comuna);
	}
	
	public Comuna buscarComunaPorId(int id){
		List<Comuna> listaFiltrada = origen.obtenerComunas().stream().filter(comuna -> comuna.getId()==id).collect(Collectors.toList());
		if(listaFiltrada.isEmpty())
			return null;
		else
			return listaFiltrada.get(0);
	}
	
	public List<Comuna> buscarComunasPorNombre(String nombre){
		return origen.obtenerComunas().stream().filter(comuna -> comuna.getNombre().equals(nombre)).collect(Collectors.toList());
	}
	
	public static void persistirEnMemoria(){
		origen = new InMemoryComunasOrigin();
	}
	
	public static void persistirEnBD(){
		origen = new PersistenceComunasOrigin();
	}
	
	public static void reset() {
		origen.reset();
	}
}
