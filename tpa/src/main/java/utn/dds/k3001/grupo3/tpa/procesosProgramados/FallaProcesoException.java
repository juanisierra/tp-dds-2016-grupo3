package utn.dds.k3001.grupo3.tpa.procesosProgramados;

public class FallaProcesoException extends Exception
{
	private static final long serialVersionUID = 1L;
	public FallaProcesoException(String descripcion)
	{
		super(descripcion);
	}
}
