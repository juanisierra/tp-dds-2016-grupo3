package utn.dds.k3001.grupo3.tpa.busquedas;

import java.util.LinkedList;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

public class PersistanceTerminalOrigin extends AbstractPersistenceTest implements TerminalOrigin, WithGlobalEntityManager{

	@Override
	public void agregarTerminal(Terminal terminal) {
		withTransaction(() -> {
		entityManager().persist(terminal);	
		});
	}

	@Override
	public List<Terminal> obtenerTerminales() {
		List<Terminal> terminales = new LinkedList<Terminal>();
		withTransaction(() -> {
		terminales.addAll((List<Terminal>) entityManager().createQuery("FROM Terminal").getResultList());
		});
		return terminales;
	}

	@Override
	public void reset() {
				
	}

	@Override
	public void remove(Terminal termABorrar) {
		withTransaction(() -> {
			entityManager().remove(termABorrar);
			});
		
	}

}
