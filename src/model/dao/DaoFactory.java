package model.dao;

import db.DB;
import model.dao.impl.QuartoDaoJDBC;

public class DaoFactory {
	
	public static QuartoDao createQuartoDao() {
		return new QuartoDaoJDBC(DB.getConnection());
	}
	
}
