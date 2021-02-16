package ar.edu.ubp.das.bean.ws;

public class ReportesResponseBean {

	private int    Id_Respuesta;
	private int    Respuesta;
	private String Mensaje;
	public int getId_Respuesta() {
		return Id_Respuesta;
	}
	public void setId_Respuesta(int id_Respuesta) {
		Id_Respuesta = id_Respuesta;
	}
	public int getRespuesta() {
		return Respuesta;
	}
	public void setRespuesta(int respuesta) {
		Respuesta = respuesta;
	}
	public String getMensaje() {
		return Mensaje;
	}
	public void setMensaje(String mensaje) {
		Mensaje = mensaje;
	}
}
