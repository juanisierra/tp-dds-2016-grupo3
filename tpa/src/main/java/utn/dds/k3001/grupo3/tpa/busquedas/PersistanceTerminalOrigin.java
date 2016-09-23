package utn.dds.k3001.grupo3.tpa.busquedas;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class PersistanceTerminalOrigin implements TerminalOrigin, WithGlobalEntityManager{

	@Override
	public void agregarTerminal(Terminal terminal) {
		entityManager().persist(terminal);	
	}

	@Override
	public List<Terminal> obtenerTerminales() {
		return (List<Terminal>) entityManager().createQuery("FROM Terminal").getResultList();
	}

	@Override
	public void reset() {
				
	}

}
