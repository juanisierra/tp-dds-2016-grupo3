package utn.dds.k3001.grupo3.tpa;

import javax.ws.rs.core.MediaType;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

public class RequestService {
	
	private Client cliente;
	private static final String SISTEMAEXTERNO = "127.0.0.1"; //TODO setear variables
	private static final String RECURSO = "bancos";
	
	public RequestService(){
		this.cliente = Client.create();
	}
	public ClientResponse getBancos(String banco, String servicio){ //TODO modificar para el servidor real
        return this.cliente.resource(SISTEMAEXTERNO).path(RECURSO)
                .queryParam("banco",banco).queryParam("servicio",servicio)
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
	}
	public String getJsonBancos (String banco, String servicio) {
		return this.getBancos(banco, servicio).getEntity(String.class);
	}
}
