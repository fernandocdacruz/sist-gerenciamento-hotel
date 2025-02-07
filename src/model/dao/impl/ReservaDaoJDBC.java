package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbException;
import model.dao.ReservaDao;
import model.entities.Reserva;

public class ReservaDaoJDBC implements ReservaDao {

	private Connection conn;
	
	public ReservaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void criarReserva(Reserva reserva) {
		
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO reservas"
					+ "(cliente, id_quarto)"
					+ "VALUES "
					+ "(?, ?)");
			
			st.setString(1, reserva.getCliente());
			st.setInt(2, reserva.getQuarto().getId());
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

}
