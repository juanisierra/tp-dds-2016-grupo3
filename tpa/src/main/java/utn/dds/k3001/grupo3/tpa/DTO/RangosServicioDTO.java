package utn.dds.k3001.grupo3.tpa.DTO;

public class RangosServicioDTO {
public RangosServicioDTO(int numeroDia,int horarioDesde, int minutosDesde, int horarioHasta,  int minutosHasta) {
		super();
		this.numeroDia = numeroDia;
		this.minutosDesde = minutosDesde;
		this.horarioDesde = horarioDesde;
		this.minutosHasta = minutosHasta;
		this.horarioHasta = horarioHasta;
	}

int numeroDia,minutosDesde,horarioDesde,minutosHasta,horarioHasta;

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
