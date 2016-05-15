package utn.dds.k3001.grupo3.tpa;

import java.io.IOException;
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
}
