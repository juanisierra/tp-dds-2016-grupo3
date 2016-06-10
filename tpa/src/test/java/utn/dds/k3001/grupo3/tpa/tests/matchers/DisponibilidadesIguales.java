package utn.dds.k3001.grupo3.tpa.tests.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import utn.dds.k3001.grupo3.tpa.Disponibilidad;

public class DisponibilidadesIguales extends TypeSafeMatcher<Disponibilidad>{
	private Disponibilidad disponibilidadPropia;

	public DisponibilidadesIguales(Disponibilidad disponibilidad)
	{
		this.disponibilidadPropia = disponibilidad;
	}
	
	@Override
	public void describeTo(Description arg0) {
		
	}

	@Override
	protected boolean matchesSafely(Disponibilidad disponibilidad) {
		return 	disponibilidad.getHoraApertura().equals(disponibilidadPropia.getHoraApertura()) &&
				disponibilidad.getHoraCierre().equals(disponibilidadPropia.getHoraCierre());
	}

}
