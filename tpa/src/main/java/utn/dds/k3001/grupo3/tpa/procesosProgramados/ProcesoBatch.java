package utn.dds.k3001.grupo3.tpa.procesosProgramados;

public interface ProcesoBatch {
	public ResultadoProceso ejecutar() throws FallaProcesoException;
}
