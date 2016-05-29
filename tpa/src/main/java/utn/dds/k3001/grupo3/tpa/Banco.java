package utn.dds.k3001.grupo3.tpa;

import java.time.LocalTime;
import java.util.List;
import org.uqbar.geodds.Point;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Banco extends PrestadorDeServicios 
{	
	public Banco(String nombre, String calle,  String barrio, int altura, Point posicion){
		super(nombre,calle,barrio,altura,posicion);
	}
	@JsonCreator
	public Banco(@JsonProperty("banco")String nombre, @JsonProperty("sucursal") String barrio,  @JsonProperty("x")int PosX,  @JsonProperty("y")int PosY,@JsonProperty("servicios") List<String> nombresServicios){
		super(nombre,"",barrio,0,new Point(PosX,PosY));
		nombresServicios.forEach(servicio -> serviciosOfrecidos.add(new Servicio(servicio,Disponibilidad.lunesAViernes(LocalTime.of(10,0),LocalTime.of(15,0)))));
	}
	public void agregarServicio(Servicio servicio){	
		servicio.limpiarDisponibilidad();
		servicio.agregarDisponibilidad(Disponibilidad.lunesAViernes(LocalTime.of(10,0),LocalTime.of(15,0)));
		serviciosOfrecidos.add(servicio);
	}
}