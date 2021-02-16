package ar.edu.ubp.das.bean;

import java.sql.Timestamp;

public class ReporteBean {

	private int Id;
	private Timestamp FechaDesde;
	private Timestamp FechaHasta;
	private String IdServicio;
	private int TotalHoy;
	private int TotalCanceladasHoy;
	private boolean Enviada;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public Timestamp getFechaDesde() {
		return FechaDesde;
	}
	public void setFechaDesde(Timestamp fechaDesde) {
		FechaDesde = fechaDesde;
	}
	public Timestamp getFechaHasta() {
		return FechaHasta;
	}
	public void setFechaHasta(Timestamp fechaHasta) {
		FechaHasta = fechaHasta;
	}
	public String getIdServicio() {
		return IdServicio;
	}
	public void setIdServicio(String idServicio) {
		IdServicio = idServicio;
	}
	public int getTotalHoy() {
		return TotalHoy;
	}
	public void setTotalHoy(int totalHoy) {
		TotalHoy = totalHoy;
	}
	public int getTotalCanceladasHoy() {
		return TotalCanceladasHoy;
	}
	public void setTotalCanceladasHoy(int totalCanceladasHoy) {
		TotalCanceladasHoy = totalCanceladasHoy;
	}
	public boolean isEnviada() {
		return Enviada;
	}
	public void setEnviada(boolean enviada) {
		Enviada = enviada;
	}
	
	
}
