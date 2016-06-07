package utn.dds.k3001.grupo3.tpa;

public class GuardarBusqueda implements ObserverBusqueda {
	private RepositorioBusquedas repositorio;
	public GuardarBusqueda(RepositorioBusquedas repositorio)
	{
		this.repositorio = repositorio;
	}
	@Override
	public void seBusco(Busqueda busqueda) {
		repositorio.agregarBusqueda(busqueda);
	}

}
