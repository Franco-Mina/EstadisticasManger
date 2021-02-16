package ar.edu.ubp.das.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.edu.ubp.das.bean.ReporteBean;
import ar.edu.ubp.das.db.Dao;

public class MSReporteDao extends Dao<ReporteBean, ReporteBean>{

	public MSReporteDao(String provider, String connectionString) {
		super(provider,connectionString);
	}
	
	@Override
	public ReporteBean delete(ReporteBean arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReporteBean insert(ReporteBean reporte) throws SQLException {
		this.connect();
		
		this.setProcedure("dbo.GENERAR_REPORTES");
		this.executeUpdate();
		
		return reporte;
	}

	@Override
	public ReporteBean make(ResultSet arg0) throws SQLException {
		ReporteBean reporte = new ReporteBean();
		
		reporte.setId(arg0.getInt(1));
		reporte.setIdServicio(arg0.getString(2));		
		reporte.setTotalHoy(arg0.getInt(3));
		reporte.setTotalCanceladasHoy(arg0.getInt(4));
		reporte.setFechaDesde(arg0.getTimestamp(5));
		reporte.setFechaHasta(arg0.getTimestamp(6));
		reporte.setEnviada(arg0.getBoolean(7));
		
		return reporte;
	}

	@Override
	public List<ReporteBean> select(ReporteBean arg0) throws SQLException {
		this.connect();		
		this.setProcedure("dbo.OBTENER_REPORTES_PENDIENTES");		
										
		return this.executeQuery();
	}
	
	@Override
	public ReporteBean update(ReporteBean reporte) throws SQLException {
		this.connect();		
		this.setProcedure("dbo.MARCAR_REPORTE_ENVIADO(?)");
		this.setParameter(1, reporte.getId());
		
		this.executeUpdate();
		
		return reporte;
	}

	@Override
	public boolean valid(ReporteBean arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}


}
