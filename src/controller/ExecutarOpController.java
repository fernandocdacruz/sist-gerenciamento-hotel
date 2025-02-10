package controller;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.QuartoDao;
import model.dao.ReservaDao;
import model.entities.Quarto;
import model.entities.Reserva;
import model.enums.DisponibilidadeQuarto;
import utilities.GerenciadorMensagens;
import utilities.ObterInt;
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
		case 3: cancelarReserva();
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
			String cliente = ObterString.obterString(scanner, GerenciadorMensagens.EXECUTAR_OP_CONTROLLER_NOME_RESERVA);
			int numero = ObterInt.obterInt(scanner, GerenciadorMensagens.EXECUTAR_OP_CONTROLLER_NUMERO_QUARTO, 1);
			Quarto quarto = quartosDisponiveis.stream().filter(q -> q.getNumero() == numero).findFirst().orElse(null);
			if (quarto.getDisponivel() == DisponibilidadeQuarto.INDISPONIVEL) {
				System.out.println(GerenciadorMensagens.EXECUTAR_OP_CONTROLLER_QUARTOS_INDISPONIVEIS);
			} else {
				Reserva reserva = new Reserva(cliente, quarto);
				reservaDao.criarReserva(reserva);
				quartoDao.atualizarDisponibilidadeParaIndisponivel(reserva.getQuarto().getId());
				System.out.println(GerenciadorMensagens.EXECUTAR_OP_CONTROLLER_RESERVA_REALIZADA);
			}
	
		}
	}
	
	public void cancelarReserva() {
		List<Reserva> reservas = reservaDao.listarTodasReservas();
		if (reservas.isEmpty()) {
			System.out.println(GerenciadorMensagens.EXECUTAR_OP_CONTROLLER_RESERVAS_VAZIO);
		} else {
			System.out.println();
			reservas.forEach(System.out::println);
			int idReserva = ObterInt.obterInt(scanner, GerenciadorMensagens.EXECUTAR_OP_CONTROLLER_ID_RESERVA, 3);
			Reserva reserva = reservas.stream().filter(r -> r.getId() == idReserva).findFirst().orElse(null);
			reservaDao.deletarReserva(idReserva);
			quartoDao.atualizarDisponibilidadeParaDisponivel(reserva.getQuarto().getId());
			System.out.println(GerenciadorMensagens.EXECUTAR_OP_CONTROLLER_RESERVA_CANCELADA);
		}
	}

}
