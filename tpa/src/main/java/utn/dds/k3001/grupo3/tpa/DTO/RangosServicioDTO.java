package utn.dds.k3001.grupo3.tpa.DTO;

public class RangosServicioDTO {
	
	int numeroDia, minutosDesde, horarioDesde, minutosHasta, horarioHasta;
	
	public RangosServicioDTO(int numeroDia, int horarioDesde, int minutosDesde, int horarioHasta,  int minutosHasta) {
			this.numeroDia = numeroDia;
			this.minutosDesde = minutosDesde;
			this.horarioDesde = horarioDesde;
			this.minutosHasta = minutosHasta;
			this.horarioHasta = horarioHasta;
	}
	public int getNumeroDia() {
		return numeroDia;
	}
	public int getMinutosDesde() {
		return minutosDesde;
	}
	public int getHorarioDesde() {
		return horarioDesde;
	}
	public int getMinutosHasta() {
		return minutosHasta;
	}
	public int getHorarioHasta() {
		return horarioHasta;
	}
}
