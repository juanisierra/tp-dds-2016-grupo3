package utn.dds.k3001.grupo3.tpa.procesosProgramados;

import javax.ws.rs.core.MediaType;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

public class OldPOISRequestService 
{
	private Client cliente;
	private String sistemaExterno; 
	private String recurso;
	
	public OldPOISRequestService(String sistemaExterno,String recurso){
		this.cliente = Client.create();
		this.sistemaExterno = sistemaExterno;
		this.recurso = recurso;
	}
	public ClientResponse getPOIS(){ 
        return this.cliente.resource(sistemaExterno).path(recurso)
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
	}
	public String getJsonPOIS () throws FallaProcesoException {
		ClientResponse respuesta = this.getPOIS();
		if(respuesta.getClientResponseStatus()==ClientResponse.Status.OK){
			return respuesta.getEntity(String.class);
		} else {
			throw new FallaProcesoException("No se pudieron obtener los POIS a dar de baja");
		}
	}
}
