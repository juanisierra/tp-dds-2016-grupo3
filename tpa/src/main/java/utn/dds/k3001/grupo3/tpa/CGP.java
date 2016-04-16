package utn.dds.k3001.grupo3.tpa;

import org.uqbar.geodds.*;

public class CGP extends PrestadorDeServicios 
{
	public CGP(String nombre, String calle, String barrio, int altura, Point posicion)
	{
		super(nombre,calle,barrio,altura,posicion);
	}
	public boolean estaCerca(Point otraPosicion) 
	{
		Comuna comunaPropia = this.estaEnComuna(posicion);
		Comuna comunaBuscada = this.estaEnComuna(otraPosicion);
		return comunaPropia.equals(comunaBuscada);
	}
	private Comuna estaEnComuna(Point posicion) 
	{
		Comuna comunaBuscada = (Comuna) listaComunas.stream().filter(comuna -> comuna.isInside(posicion)).findFirst().get(); // saca el primero y unico de la lista
		return comunaBuscada;
	}
}
