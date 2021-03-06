package utn.dds.k3001.grupo3.tpa.tests.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import utn.dds.k3001.grupo3.tpa.pois.CGP;

public class CGPIgualesDatos extends TypeSafeMatcher<CGP>{
	private CGP cgpPropio;
	public CGPIgualesDatos(CGP cgp1)
	{
		this.cgpPropio = cgp1;
	}
	@Override
	public void describeTo(Description arg0) {
	}

	@Override
	protected boolean matchesSafely(CGP cgp2) {

		return 	cgpPropio.getNombre().equals(cgp2.getNombre()) &&
				cgpPropio.getDireccion().getBarrio().equals(cgp2.getDireccion().getBarrio()) &&
				cgpPropio.getDireccion().getCalle().equals(cgp2.getDireccion().getCalle());
	}

}
