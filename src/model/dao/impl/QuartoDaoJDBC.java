package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.QuartoDao;
import model.entities.Quarto;
import model.enums.DisponibilidadeQuarto;

public class QuartoDaoJDBC implements QuartoDao {

	private Connection conn;
	
	public QuartoDaoJDBC(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public List<Quarto> acharQuartosDisponiveis() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"select quartos.* from quartos where disponivel = DISPONIVEL");
			rs = st.executeQuery();
			List<Quarto> quartos = new ArrayList<>();
			while (rs.next()) {
				Quarto quarto = instanciarQuarto(rs);
				quartos.add(quarto);
			}
			return quartos;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	private Quarto instanciarQuarto(ResultSet rs) throws SQLException {
		Quarto quarto = new Quarto();
		quarto.setId(rs.getInt("id"));
		quarto.setNumero(rs.getInt("numero"));
		quarto.setDisponivel(DisponibilidadeQuarto.valueOf(rs.getString("disponivel")));
		return quarto;
	}

	@Override
	public void atualizarDisponibilidade(int id) {
		
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE quartos SET disponivel = 'INDISPONIVEL' WHERE id = ?");
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

}
