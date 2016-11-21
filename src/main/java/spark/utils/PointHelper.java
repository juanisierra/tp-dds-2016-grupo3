package spark.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

import utn.dds.k3001.grupo3.tpa.pois.Direccion;
public enum PointHelper implements Helper<BigDecimal>{
	mostrarCoord{
		@Override
		public CharSequence apply(BigDecimal arg0, Options arg1) throws IOException {
		return arg0.setScale(2, RoundingMode.CEILING).toString();
		}
	}
}
