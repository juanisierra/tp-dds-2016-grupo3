package spark.utils;

import java.io.IOException;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

import utn.dds.k3001.grupo3.tpa.busquedas.AccionesBusqueda;

public enum AccionesQueryParamHelper implements Helper<AccionesBusqueda>{
	
	parametrizarAccion{
		@Override
		public CharSequence apply(AccionesBusqueda arg0, Options arg1) throws IOException {
			return String.valueOf(arg0);
		}
	}
}
