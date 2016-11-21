package spark.utils;

import java.io.IOException;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

import utn.dds.k3001.grupo3.tpa.pois.Direccion;

public enum DireccionHelper implements Helper<Direccion>{
	
	mostrarDireccion{
		@Override
		public CharSequence apply(Direccion arg0, Options arg1) throws IOException {
		return arg0.getCalle()+" "+arg0.getAltura()+" , "+arg0.getBarrio();
		}
	},
	getCalle{
		@Override
		public CharSequence apply(Direccion arg0, Options arg1) throws IOException {
		return arg0.getCalle();
		}
	},
	getNumero{
		@Override
		public CharSequence apply(Direccion arg0, Options arg1) throws IOException {
		return String.valueOf(arg0.getAltura());
		}
	},
	getBarrio{
		@Override
		public CharSequence apply(Direccion arg0, Options arg1) throws IOException {
		return arg0.getBarrio();
		}
	}
}
