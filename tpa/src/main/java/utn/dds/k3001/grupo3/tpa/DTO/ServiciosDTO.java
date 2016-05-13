package utn.dds.k3001.grupo3.tpa.DTO;

import java.util.ArrayList;

public class ServiciosDTO {
	
	String nombre;
	ArrayList<RangosServicioDTO> horarios;	
	
	public ServiciosDTO(String nombre, ArrayList<RangosServicioDTO> horarios) {
			this.nombre = nombre;
			this.horarios = horarios;
	}
	public String getNombre() {
		return nombre;
	}
	public ArrayList<RangosServicioDTO> getHorarios() {
		return horarios;
	}
}
