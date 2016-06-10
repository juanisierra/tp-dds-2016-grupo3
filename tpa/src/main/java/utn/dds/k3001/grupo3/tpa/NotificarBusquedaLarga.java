package utn.dds.k3001.grupo3.tpa;

public class NotificarBusquedaLarga implements ObserverBusqueda {
	
	double cantidadSegundos;
	ServicioMail servicio;
	String mailAdministrador;
	public NotificarBusquedaLarga(ServicioMail servicio,String mail,double segundos){
		this.cantidadSegundos = segundos;
		this.servicio = servicio;
		this.mailAdministrador = mail;
	}
	@Override
	public void agregar(Busqueda busqueda) {
		if(busqueda.getTiempo()>cantidadSegundos){
			servicio.notificarAdministrador(mailAdministrador,"Busqueda Larga","La busqueda llevó demasiado tiempo.");
		}
	}

}
