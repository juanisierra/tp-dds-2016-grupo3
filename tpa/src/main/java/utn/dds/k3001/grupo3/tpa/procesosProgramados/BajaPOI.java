package utn.dds.k3001.grupo3.tpa.procesosProgramados;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BajaPOI 
{
	private long poiID;
	private LocalDateTime fechaBaja;
	
	@JsonCreator
	public BajaPOI(@JsonProperty("id") Long poiID)  {//TODO agregar datos de fecha
		this.poiID = poiID;
		this.fechaBaja = fechaBaja;
	}
	public long getId(){
		return poiID;
	}
}
