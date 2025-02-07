package model.dao;

import db.DB;
import model.dao.impl.QuartoDaoJDBC;
import model.dao.impl.ReservaDaoJDBC;

public class DaoFactory {
	
	public static QuartoDao createQuartoDao() {
		return new QuartoDaoJDBC(DB.getConnection());
	}
	
	public static ReservaDao createReservaDao() {
		return new ReservaDaoJDBC(DB.getConnection());
	}
	
}
