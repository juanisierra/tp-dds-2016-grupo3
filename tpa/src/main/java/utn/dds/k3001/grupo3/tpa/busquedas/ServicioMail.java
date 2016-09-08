package utn.dds.k3001.grupo3.tpa.busquedas;

public abstract  class ServicioMail {

	public abstract void enviarMail(String mail,String asunto,String mensaje);

	public static void notificarAdministrador(String mailAdministrador, String asunto, String cuerpo) {
		// TODO Auto-generated method stub
		
	}

}
