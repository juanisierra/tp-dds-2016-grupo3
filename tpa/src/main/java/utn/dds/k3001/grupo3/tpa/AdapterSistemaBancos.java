package utn.dds.k3001.grupo3.tpa;

import java.util.LinkedList;
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
    	List<POI> listaPOI = new LinkedList<POI>();
    	String json = requestService.getJsonBancos("","");           
        listaPOI.addAll(factory.JsonAObjeto(json, new TypeReference<List<Banco>>(){})); //Solo asi me deja retornar lista de Bancos pero que admite otros POI
        return listaPOI.stream().filter(Banco -> Banco.esBuscado(criterio)).collect(Collectors.toList());
    }
}
