package spark.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

import utn.dds.k3001.grupo3.tpa.pois.Servicio;

public enum ServiciosHelper implements Helper<List<Servicio>>{
	mostrarServicio{
		@Override
		public CharSequence apply(List<Servicio> arg0, Options arg1) throws IOException {
			if(arg0!=null) {
			StringBuilder builder = new StringBuilder();
			 arg0.forEach(elem -> builder.append(elem.getNombre()+" , "));
			 return builder.substring(0, builder.length()-3);
		} else {
			return "";
		}
		}
	}
}