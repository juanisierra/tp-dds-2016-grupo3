package utn.dds.k3001.grupo3.tpa.DTO;

import java.util.ArrayList;

public class CentroDTO {
public CentroDTO(int numComuna, String zonas, String nombreDirector, String domicilio, String telefono,
			ArrayList<ServiciosDTO> listaServicios) {
		super();
		this.numComuna = numComuna;
		this.zonas = zonas;
		this.nombreDirector = nombreDirector;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.listaServicios = listaServicios;
	}
int numComuna;
String zonas,nombreDirector,domicilio,telefono;
ArrayList<ServiciosDTO> listaServicios;
public int getNumComuna() {
	return numComuna;
}
public String getZonas() {
	return zonas;
}
public String getNombreDirector() {
	return nombreDirector;
}
public String getDomicilio() {
	return domicilio;
}
public String getTelefono() {
	return telefono;
}
public ArrayList<ServiciosDTO> getListaServicios() {
	return listaServicios;
}


}
