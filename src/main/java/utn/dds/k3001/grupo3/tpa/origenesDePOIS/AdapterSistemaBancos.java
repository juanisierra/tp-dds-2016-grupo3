package utn.dds.k3001.grupo3.tpa.origenesDePOIS;

import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.core.type.TypeReference;

import utn.dds.k3001.grupo3.tpa.busquedas.filtros.FiltroPOI;
import utn.dds.k3001.grupo3.tpa.pois.Banco;
import utn.dds.k3001.grupo3.tpa.pois.POI;

public class AdapterSistemaBancos implements OrigenDeDatos{
	
	private RequestService requestService;
	private JsonFactory factory;
	
	public AdapterSistemaBancos (RequestService requestService){
		this.requestService = requestService;
		this.factory = new JsonFactory();
	}
    public List<POI> buscar(String criterio){
    	String json = requestService.getJsonBancos("","");           
    	return factory
    			.JsonAObjeto(json, new TypeReference<List<Banco>>(){})
    			.stream()
    			.filter(Banco -> Banco.esBuscado(criterio))
    			.collect(Collectors.toList());
    }
    public List<POI> buscar(List<FiltroPOI> filtros){
    	String json = requestService.getJsonBancos("","");           
    	return factory
    			.JsonAObjeto(json, new TypeReference<List<Banco>>(){})
    			.stream()
    			.filter(Banco -> filtros.stream().allMatch(f -> f.filtrar(Banco)))
    			.collect(Collectors.toList());
    }
}
