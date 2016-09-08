package utn.dds.k3001.grupo3.tpa.busquedas;


public enum AccionesBusqueda implements ObserverBusqueda {
	
	GUARDARBUSQUEDA {

		@Override
		public void agregar(Busqueda busqueda) {
			RepositorioBusquedas.getInstance().buscar(busqueda);
			
		}
	},
	NOTIFICARBUQUEDALARGA {
		
			@Override
			public void agregar(Busqueda busqueda) {
				if(busqueda.getTiempo()>1){
					ServicioMail.notificarAdministrador("admin@sistema.com","Busqueda Larga","La busqueda llevó demasiado tiempo.");
				}
		}
		
	}
}
