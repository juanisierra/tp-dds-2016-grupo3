package utn.dds.k3001.grupo3.tpa.procesosProgramados;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class SchedulerProcesos {
	private ScheduledExecutorService executor;
	private List<ResultadoProceso> historialProcesos;
public SchedulerProcesos()
{
	executor = Executors.newSingleThreadScheduledExecutor();
}
public void agregarResultado(ResultadoProceso resultado)
{
	historialProcesos.add(resultado);
}
}
