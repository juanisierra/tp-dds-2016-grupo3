package utn.dds.k3001.grupo3.tpa.procesosProgramados;

import java.time.LocalDateTime;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.uqbarproject.jpa.java8.extras.convert.LocalDateTimeConverter;

public class ResultadoProceso
{	private int id;
	private LocalDateTime momentoFinalizacion;
	private int cantidadAfectados;
	private boolean terminoCorrectamente;
	private String comentarios;
	public ResultadoProceso(){}
	public LocalDateTime getMomentoFinalizacion() {
		return momentoFinalizacion;
	}
	public ResultadoProceso(LocalDateTime momentoFinalizacion, int cantidadAfectados, boolean terminoCorrectamente,String comentario) {
		this.momentoFinalizacion = momentoFinalizacion;
		this.cantidadAfectados= cantidadAfectados;
		this.terminoCorrectamente = terminoCorrectamente;
		this.comentarios = comentario;
	}
	public boolean terminoCorrectamente() {
		return terminoCorrectamente;
	}
	public String getDescripcion() {
		return comentarios;
	}
	public int getCantidadAfectados() {
		return cantidadAfectados;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isTerminoCorrectamente() {
		return terminoCorrectamente;
	}
	public void setTerminoCorrectamente(boolean terminoCorrectamente) {
		this.terminoCorrectamente = terminoCorrectamente;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public void setMomentoFinalizacion(LocalDateTime momentoFinalizacion) {
		this.momentoFinalizacion = momentoFinalizacion;
	}
	public void setCantidadAfectados(int cantidadAfectados) {
		this.cantidadAfectados = cantidadAfectados;
	}
}
