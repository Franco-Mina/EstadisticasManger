package ar.edu.ubp.das.bean.ws;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ar.edu.ubp.das.bean.ReporteBean;

public class ReportesRequestBean {

	private Timestamp Fecha;
	private String    Token;
	private String    Usuario;
	private List<ReporteBean> Reportes = new ArrayList<ReporteBean>();
	
	public Timestamp getFecha() {
		return Fecha;
	}
	public void setFecha(Timestamp fecha) {
		Fecha = fecha;
	}
	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		Token = token;
	}
	public List<ReporteBean> getReportes() {
		return Reportes;
	}
	public void setReportes(List<ReporteBean> reportes) {
		Reportes = reportes;
	}
	public String getUsuario() {
		return Usuario;
	}
	public void setUsuario(String usuario) {
		Usuario = usuario;
	}	
}
