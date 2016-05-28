package utn.dds.k3001.grupo3.tpa;

public class NotificarBusquedaLarga implements ObserverBusqueda {
	int cantidadSegundos;
	ServicioMail servicio;
	public NotificarBusquedaLarga(ServicioMail servicio,int segundos){
		this.cantidadSegundos = segundos;
		this.servicio = servicio;
	}
	@Override
	public void seBusco(Busqueda busqueda) {
		if(busqueda.getTiempo()>cantidadSegundos){
			servicio.notificarAdministrador();
		}
	}

}
