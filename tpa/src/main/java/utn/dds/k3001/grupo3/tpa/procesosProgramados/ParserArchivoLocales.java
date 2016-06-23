package utn.dds.k3001.grupo3.tpa.procesosProgramados;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class ParserArchivoLocales
{
	private File archivo;
	private Scanner scanner;
	
	public ParserArchivoLocales(String filepath) throws FallaProcesoException{
		archivo = new File(filepath);
		try {
			scanner = new Scanner(archivo);
		} catch (FileNotFoundException e) {
			throw new FallaProcesoException("El archivo indicado no es correcto");
		}
	}
	public Map<String,List<String>> obtenerLocalYPalabrasClaves(){	
		String[] linea;
		List<String> palabrasClave;
		Map<String,List<String>> mapa = new HashMap<String,List<String>>();
		while(scanner.hasNextLine())
		{ 
			linea = scanner.nextLine().split(";",2);
			palabrasClave =Arrays.asList(linea[1].trim().split("\\s+"));
			palabrasClave.removeAll(Arrays.asList("",null)); //removemos las claves nulas
			mapa.putIfAbsent(linea[0],palabrasClave);
		}
		return mapa;
	}
}

