package utn.dds.k3001.grupo3.tpa.procesosProgramados;

import java.time.LocalDateTime;

public class ResultadoProceso
{
	private LocalDateTime momentoFinalizacion;
	private int cantidadAfectados;
	private boolean terminoCorrectamente;
	private String comentarios;
	
	public ResultadoProceso(LocalDateTime momentoFinalizacion, int cantidadAfectados, boolean terminoCorrectamente,String comentario) {
		this.momentoFinalizacion = momentoFinalizacion;
		this.cantidadAfectados = cantidadAfectados;
		this.terminoCorrectamente = terminoCorrectamente;
		this.comentarios = comentario;
	}
	public boolean terminoCorrectamente()
	{
		return terminoCorrectamente;
	}
	public String getDescripcion()
	{
		return comentarios;
	}
}
