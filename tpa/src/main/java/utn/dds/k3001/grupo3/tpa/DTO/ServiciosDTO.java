package utn.dds.k3001.grupo3.tpa.DTO;

import java.util.ArrayList;

public class ServiciosDTO {
public ServiciosDTO(String nombre, ArrayList<RangosServicioDTO> horarios) {
		super();
		this.nombre = nombre;
		this.horarios = horarios;
	}
String nombre;
ArrayList<RangosServicioDTO> horarios;
public String getNombre() {
	return nombre;
}
public ArrayList<RangosServicioDTO> getHorarios() {
	return horarios;
}


}
