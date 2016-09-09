package utn.dds.k3001.grupo3.tpa.pois;


import utn.dds.k3001.grupo3.tpa.geo.*;
import java.time.*;

import javax.persistence.Entity;
@Entity
public class ParadaColectivo extends POI 
{	
	private Integer linea;
	
	public ParadaColectivo(String nombre, String calle, String barrio, int altura, PersistablePoint posicion, Integer linea){	
		super(nombre,calle,barrio,altura,posicion);
		this.linea = linea;
	}
	public ParadaColectivo(){}
	@Override
	public boolean estaCerca(PersistablePoint otraPosicion){
		return (posicion.distance(otraPosicion) <=0.1);
	}
	
	public boolean estaDisponible(LocalDateTime fechaBuscada,String servicio){
		return true;
	}
	
	public boolean esBuscado(String criterio) {
		return super.esBuscado(criterio) || linea.toString().contains(criterio);
	}
	public Integer getLinea() {
		return linea;
	}
	public void setLinea(Integer linea) {
		this.linea = linea;
	}
}
