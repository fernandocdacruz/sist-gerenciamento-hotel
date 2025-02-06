package controller;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.QuartoDao;
import model.entities.Quarto;
import utilities.GerenciadorMensagens;

public class ExecutarOpController {

	private QuartoDao quartoDao = DaoFactory.createQuartoDao();
	
	public void executarOp(int op) {
		switch (op) {
		case 1: listarQuartosDisponiveis();
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		}
	}
	
	public void listarQuartosDisponiveis() {
		List<Quarto> quartosDisponiveis = quartoDao.acharQuartosDisponiveis();
		System.out.println(GerenciadorMensagens.EXECUTAR_OP_CONTROLLER_QUARTOS_DISPONIVEIS);
		quartosDisponiveis.forEach(System.out::println);
	}
	
}
