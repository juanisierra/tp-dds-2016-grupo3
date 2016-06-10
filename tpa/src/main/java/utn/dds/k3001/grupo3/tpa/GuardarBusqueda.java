package utn.dds.k3001.grupo3.tpa;

public class GuardarBusqueda implements ObserverBusqueda {
	RepositorioBusquedas repositorio;
	public GuardarBusqueda(RepositorioBusquedas repositorio)
	{
		this.repositorio = repositorio;
	}
	@Override
	public void agregar(Busqueda busqueda) {
	repositorio.buscar(busqueda);
		
	}

}
