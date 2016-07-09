package utn.dds.k3001.grupo3.tpa.busquedas;

public interface ServicioMail {

	public void enviarMail(String mail,String asunto,String mensaje);

	public void notificarAdministrador(String mailAdministrador, String asunto, String cuerpo);

}
