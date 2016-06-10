package utn.dds.k3001.grupo3.tpa;

public class NotificarBusquedaLarga implements ObserverBusqueda {
	
	double cantidadSegundos;
	ServicioMail servicio;
	
	public NotificarBusquedaLarga(ServicioMail servicio,double segundos){
		this.cantidadSegundos = segundos;
		this.servicio = servicio;
	}
	@Override
	public void agregar(Busqueda busqueda) {
		if(busqueda.getTiempo()>cantidadSegundos){
			servicio.notificarAdministrador();
		}
	}

}
