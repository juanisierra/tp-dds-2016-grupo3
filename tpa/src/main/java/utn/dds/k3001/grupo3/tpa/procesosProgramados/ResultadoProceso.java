package utn.dds.k3001.grupo3.tpa.procesosProgramados;

import java.time.LocalDateTime;

public class ResultadoProceso {
public ResultadoProceso(LocalDateTime momentoFinalizacion, int cantidadAfectados, String resultadoProceso) {
		this.momentoFinalizacion = momentoFinalizacion;
		this.cantidadAfectados = cantidadAfectados;
		this.resultadoProceso = resultadoProceso;
	}
private LocalDateTime momentoFinalizacion;
private int cantidadAfectados;
private String resultadoProceso;
}
