package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.ReservaDao;
import model.entities.Quarto;
import model.entities.Reserva;
import model.enums.DisponibilidadeQuarto;

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

	@Override
	public void deletarReserva(int id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM reservas WHERE id = ?");
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Reserva> listarTodasReservas() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT r.id as reserva_id, r.cliente, q.*"
					+ " FROM reservas r "
					+ "INNER JOIN quartos q "
					+ "WHERE r.id_quarto = q.id;");
			rs = st.executeQuery();
			List<Reserva> reservas = new ArrayList<>();
			while (rs.next()) {
				Reserva reserva = instanciarReserva(rs);
				reservas.add(reserva);
			}
			return reservas;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	public Reserva instanciarReserva(ResultSet rs) throws SQLException {
		Reserva reserva = new Reserva();
		reserva.setId(rs.getInt("reserva_id"));
		reserva.setCliente(rs.getString("cliente"));
		Quarto quarto = new Quarto();
		quarto.setId(rs.getInt("id"));
		quarto.setNumero(rs.getInt("numero"));
		quarto.setDisponivel(DisponibilidadeQuarto.valueOf(rs.getString("disponivel")));
		reserva.setQuarto(quarto);
		return reserva;
	}

}
