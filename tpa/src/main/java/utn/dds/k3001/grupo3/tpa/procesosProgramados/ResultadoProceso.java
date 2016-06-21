package utn.dds.k3001.grupo3.tpa.procesosProgramados;

import java.time.LocalDateTime;

public class ResultadoProceso {
public ResultadoProceso(LocalDateTime momentoFinalizacion, int cantidadAfectados, boolean terminoCorrectamente) {
		this.momentoFinalizacion = momentoFinalizacion;
		this.cantidadAfectados = cantidadAfectados;
		this.terminoCorrectamente = terminoCorrectamente;
	}
private LocalDateTime momentoFinalizacion;
private int cantidadAfectados;
private boolean terminoCorrectamente;
}
