package utn.dds.k3001.grupo3.tpa.procesosProgramados;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

public class OldPOISRequestService {
	private Client cliente;
	private static final String SISTEMAEXTERNO = "http://demo3537367.mockable.io/trash"; //TODO setear variables
	private static final String RECURSO = "pois";
	
	public OldPOISRequestService(){
		this.cliente = Client.create();
	}
	public ClientResponse getPOIS(){ //TODO modificar para el servidor real
        return this.cliente.resource(SISTEMAEXTERNO).path(RECURSO)
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
	}
	public String getJsonPOIS () {
		return this.getPOIS().getEntity(String.class);
	}
}
