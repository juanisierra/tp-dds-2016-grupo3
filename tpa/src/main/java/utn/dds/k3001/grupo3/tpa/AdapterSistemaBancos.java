package utn.dds.k3001.grupo3.tpa;

import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.core.type.TypeReference;

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
}
