package spark.utils;

import java.io.IOException;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

import utn.dds.k3001.grupo3.tpa.busquedas.AccionesBusqueda;

public enum AccionesBusquedaHelper implements Helper<AccionesBusqueda>{
	
	mostrarAccion{
		@Override
		public CharSequence apply(AccionesBusqueda arg0, Options arg1) throws IOException {
			CharSequence resultado = new String();
			switch(arg0){
			case GUARDARBUSQUEDA: resultado = "Guardar Búsqueda";
				break;
			case NOTIFICARBUSQUEDALARGA: resultado = "Notificar búsqueda larga";
				break;
			default: resultado = "";
				break;
			}
			return resultado;
		}
	}
}
