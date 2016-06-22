package utn.dds.k3001.grupo3.tpa;

import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFactory {
	
	private ObjectMapper objectMapper;
	
	public JsonFactory (){
		this.objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	public <T> T JsonAObjeto(String json, TypeReference<T> typeReference) {
		try {
			return this.objectMapper.readValue(json, typeReference);
		} catch (IOException exception) {
			throw new RuntimeException("Error al leer el JSON", exception);
		}
		
	}
	public Map<Long, LocalDate> JsonAListaPOISBaja(String json)
	{	
	try {
		return this.objectMapper.readValue(json, new TypeReference<Map<Long,LocalDate>>(){});
	} catch (IOException exception) {
		throw new RuntimeException("Error al leer el JSON", exception);  //TODO chequear excepcon lanzada y catchearla
	}
	
	}
}
