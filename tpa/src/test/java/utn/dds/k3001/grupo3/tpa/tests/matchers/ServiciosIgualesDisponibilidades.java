package utn.dds.k3001.grupo3.tpa.tests.matchers;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Assert;
import utn.dds.k3001.grupo3.tpa.pois.Servicio;
	
public class ServiciosIgualesDisponibilidades extends TypeSafeMatcher<Servicio>{
		private Servicio servicioPropio;
		public ServiciosIgualesDisponibilidades(Servicio servicio)
		{
			this.servicioPropio = servicio;
		}
		@Override
		public void describeTo(Description arg0) {
		}
		
		@Override
		protected boolean matchesSafely(Servicio servicio) {
			 Assert.assertThat(servicio.getListaDisponibilidad().get(0), 
						new DisponibilidadesIguales(servicioPropio.getListaDisponibilidad().get(0)));
			return 	servicio.getNombre().equals(servicioPropio.getNombre());		
		}

	}


