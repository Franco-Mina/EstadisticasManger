package ar.edu.ubp.das.manager;

import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;

import ar.edu.ubp.das.bean.ReporteBean;
import ar.edu.ubp.das.bean.ws.ReportesRequestBean;
import ar.edu.ubp.das.bean.ws.ReportesResponseBean;
import ar.edu.ubp.das.conections.ConnectionManager;
import ar.edu.ubp.das.db.Dao;
import ar.edu.ubp.das.db.DaoFactory;
import ar.edu.ubp.das.logger.Logger;
import ar.edu.ubp.das.token.db.ConsoleTokenManger;

public class EstadisticasManager {
	
	private final String cadenaConexion = "jdbc:sqlserver://172.10.3.106;databaseName=gobierno_provincial;user=sa;password=Francomina1";
	private final String usuario        = "sa";
	private final String password       = "Francomina1";
	private final String logPath = "c:/Logger/Estadisticas/";
	
	public int GenerarReportes() {
		
		int intentos = 0;
		boolean exito = false;		
				
		try {
			do {
				exito = this.CrearReportes() == 0;
				if(!exito) intentos +=1;
			}while (intentos < 3 && !exito);
			
			if(!exito) {
				Logger.getLogger(this.logPath).escribirLog("No se pudieron generar los reportes.");
				return -1;
			}
			
			List<ReporteBean> listaReportes = this.ObtenerReportesNoEnviados();
			
			//No se pudieron recuperar los reportes
			if(listaReportes == null ) return -1;
			//No hay reportes pendientes
			if(listaReportes.isEmpty()) return 0;
			
			//Llamar al servicio
			ReportesRequestBean reportesRequest = new ReportesRequestBean();
			reportesRequest.setUsuario("u1");
			reportesRequest.setReportes(listaReportes);
			
			Gson gson = new Gson();
			
			ConnectionManager connectionManager = new ConnectionManager("src/ar/edu/ubp/das/manager/conexiones.xml", 
					new  ConsoleTokenManger(this.cadenaConexion,this.usuario,this.password));		
			
			String json = connectionManager.callApi(2, reportesRequest);		
			
			ReportesResponseBean response = gson.fromJson(json, ReportesResponseBean.class);
			
			if(response.getRespuesta() == 1) {
				int actualizados = this.MarcarEnviados(listaReportes);
				if(actualizados != 0) {
					Logger.getLogger(this.logPath).escribirLog("No se pudieron marcar como enviados los reportes.");
				}else {
					this.MarcarEnviados(listaReportes);
				}
			}else {
				Logger.getLogger(this.logPath).escribirLog("Ocurrio un error al enviar los reportes, se volvera a intentar la proxima vez que se generen nuevos reportes.");
			}
			
			return 0;
		} catch (Exception e) {
			Logger.getLogger(this.logPath).escribirLog(e);
			return -1;
		}			
	}

	private int CrearReportes(){
		try {
			Dao<ReporteBean, ReporteBean> dao = DaoFactory.getDao("Reporte", "ar.edu.ubp.das", 
					"com.microsoft.sqlserver.jdbc.SQLServerDriver", this.cadenaConexion, "MS");
			
			dao.insert(null);
			
			return 0;			
		} catch (SQLException e) {
			Logger.getLogger(this.logPath).escribirLog(e);
			return -1;
		}
	}
	
	private List<ReporteBean> ObtenerReportesNoEnviados(){
		try {
			Dao<ReporteBean, ReporteBean> dao = DaoFactory.getDao("Reporte", "ar.edu.ubp.das", 
					"com.microsoft.sqlserver.jdbc.SQLServerDriver", this.cadenaConexion, "MS");
			
			List<ReporteBean> listaReportes = dao.select(null);
			
			return listaReportes;			
		} catch (SQLException e) {
			Logger.getLogger(this.logPath).escribirLog(e);
			return null;
		}
	}
	
	private int MarcarEnviados(List<ReporteBean> listaReportes) {
		try {
			Dao<ReporteBean, ReporteBean> dao = DaoFactory.getDao("Reporte", "ar.edu.ubp.das", 
					"com.microsoft.sqlserver.jdbc.SQLServerDriver", this.cadenaConexion, "MS");
			
			for (ReporteBean reporte : listaReportes) {
				dao.update(reporte);
			}
			
			return 0;			
		} catch (SQLException e) {
			Logger.getLogger(this.logPath).escribirLog(e);
			return -1;
		}
	}
}
