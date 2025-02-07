package controller;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.QuartoDao;
import model.dao.ReservaDao;
import model.entities.Quarto;
import model.entities.Reserva;
import model.enums.DisponibilidadeQuarto;
import utilities.GerenciadorMensagens;
import utilities.ObterString;

public class ExecutarOpController {
	
	private Scanner scanner;

	public ExecutarOpController(Scanner scanner) {
		super();
		this.scanner = scanner;
	}

	private QuartoDao quartoDao = DaoFactory.createQuartoDao();
	private ReservaDao reservaDao = DaoFactory.createReservaDao();
	
	public void executarOp(int op) {
		switch (op) {
		case 1: listarQuartosDisponiveis();
			break;
		case 2: fazerReserva();
			break;
		case 3:
			break;
		case 4:
			break;
		}
	}
	
	public void listarQuartosDisponiveis() {
		List<Quarto> quartosDisponiveis = quartoDao.acharQuartosDisponiveis();
		if (quartosDisponiveis.isEmpty()) {
			System.out.println(GerenciadorMensagens.EXECUTAR_OP_CONTROLLER_QUARTOS_INDISPONIVEIS);
		} else {
			System.out.println(GerenciadorMensagens.EXECUTAR_OP_CONTROLLER_QUARTOS_DISPONIVEIS);
			quartosDisponiveis.forEach(System.out::println);
		}
	}
	
	public void fazerReserva() {
		List<Quarto> quartosDisponiveis = quartoDao.acharQuartosDisponiveis();
		if (quartosDisponiveis.isEmpty()) {
			System.out.println(GerenciadorMensagens.EXECUTAR_OP_CONTROLLER_QUARTOS_INDISPONIVEIS);
		} else {
			String cliente = ObterString.obterString(scanner, "\nDigite o nome do responsável pela reserva: ");
			System.out.print("Digite o número do quarto: ");
			int numero = scanner.nextInt();
			Quarto quarto = quartosDisponiveis.stream().filter(q -> q.getNumero() == numero).findFirst().orElse(null);
			if (quarto.getDisponivel() == DisponibilidadeQuarto.INDISPONIVEL) {
				System.out.println("\nQuarto indisponível. Tente novamente.");
			} else {
				Reserva reserva = new Reserva(cliente, quarto);
				reservaDao.criarReserva(reserva);
				quartoDao.atualizarDisponibilidade(reserva.getQuarto().getId());
				System.out.println("\nReserva feita com sucesso!");
				
			}
		}
	}
	
}
