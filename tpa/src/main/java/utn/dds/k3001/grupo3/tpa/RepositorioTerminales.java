package utn.dds.k3001.grupo3.tpa;

import java.util.LinkedList;
import java.util.List;

public class RepositorioTerminales
{
	private List<Terminal> terminales;
	
	public RepositorioTerminales()
	{
		this.terminales =  new LinkedList<Terminal>();
	}
	public void  agregar(Terminal terminal)
	{
		terminales.add(terminal);
	}
	public int cantidadResultadosPorTerminal(String terminal)
	{
		return terminales .stream()
				          .filter(Terminal -> Terminal.getNombre() == terminal)
						  .findFirst()
						  .get()
						  .getBusquedas()
						  .size();//TODO ver que hacemos si no encuentra la terminal
	}
	public int cantidadResultadosTotal()//TODO no me dejaba hacerlo con el forEach, prueben si los deja
	{
		int resultado = 0;
		int cantTerminales = terminales.size();
		while(cantTerminales > 0)
		{
			resultado = resultado + terminales.get(cantTerminales).getBusquedas().size();
		}
		return resultado;
	}
}
