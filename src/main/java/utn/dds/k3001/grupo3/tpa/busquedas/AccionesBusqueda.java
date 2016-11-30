package utn.dds.k3001.grupo3.tpa.busquedas;


public enum AccionesBusqueda implements ObserverBusqueda {
	
	GUARDARBUSQUEDA {

		@Override
		public void notificar(Busqueda busqueda) {
			RepositorioBusquedas.getInstance().buscar(busqueda);
			
		}
	},
	NOTIFICARBUSQUEDALARGA {
		
			@Override
			public void notificar(Busqueda busqueda) {
				if(busqueda.getTiempo()>1){
					ServicioMail.notificarAdministrador("admin@sistema.com","Busqueda Larga","La busqueda llevó demasiado tiempo.");
				}
		}
		
	}
}
