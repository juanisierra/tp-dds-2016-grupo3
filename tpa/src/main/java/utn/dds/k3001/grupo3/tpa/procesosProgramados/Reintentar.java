package utn.dds.k3001.grupo3.tpa.procesosProgramados;

import java.util.concurrent.Callable;

public class Reintentar implements Callable<ResultadoProceso> 
{
	private Callable<ResultadoProceso> proceso;
	private int numRepeticiones;
	
	public Reintentar(int numeroDeVeces,Callable<ResultadoProceso> proceso)	{
		this.numRepeticiones = numeroDeVeces;
		this.proceso = proceso;
	}
	@Override
	public ResultadoProceso call() throws Exception {
		ResultadoProceso resultado;
		do {
			numRepeticiones--;
			resultado = proceso.call();	
		} while (numRepeticiones>=0 && !resultado.terminoCorrectamente());
	return resultado;
	}
}
