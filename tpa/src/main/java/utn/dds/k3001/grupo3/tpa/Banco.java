package utn.dds.k3001.grupo3.tpa;

import org.uqbar.geodds.Point;

public class Banco extends PrestadorDeServicios 
{
	//TODO agregar constructor de servicios especial, con horario bancario
	
	public Banco(String nombreP, String calleP, String barrioP, int alturaP, Point posicionP)
	{
		this.constructorComun(nombreP, calleP, barrioP, alturaP, posicionP);
	}
}
