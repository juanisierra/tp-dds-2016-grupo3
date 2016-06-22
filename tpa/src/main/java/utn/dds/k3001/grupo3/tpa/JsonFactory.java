package utn.dds.k3001.grupo3.tpa;

import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonFactory {
	
	private ObjectMapper objectMapper;
	
	public JsonFactory (){
		this.objectMapper = new ObjectMapper();
		this.objectMapper.findAndRegisterModules();
		this.objectMapper.registerModule(new JavaTimeModule());
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
