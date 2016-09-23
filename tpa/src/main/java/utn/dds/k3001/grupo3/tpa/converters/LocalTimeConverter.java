package utn.dds.k3001.grupo3.tpa.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Time;
import java.time.LocalTime;

@Converter
public class LocalTimeConverter implements AttributeConverter<LocalTime, Time> {

	@Override
	public Time convertToDatabaseColumn(LocalTime time) {
		if(Time.valueOf(time)!=null) return Time.valueOf(time);
		else return null;
	}
	@Override
	public LocalTime convertToEntityAttribute(Time dbData) {
		if(dbData.toLocalTime() != null) return dbData.toLocalTime();
		else return null;
	}
}