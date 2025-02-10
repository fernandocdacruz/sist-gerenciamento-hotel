package controller;

import java.util.Scanner;

import utilities.GerenciadorMensagens;
import utilities.ObterInt;

public class MenuController {

	private Scanner scanner;

	public MenuController(Scanner scanner) {
		this.scanner = scanner;
	}

	public void mostrarMenu() {
		System.out.println(GerenciadorMensagens.MENU_CONTROLLER_MENU_OP_0);
		System.out.println(GerenciadorMensagens.MENU_CONTROLLER_MENU_OP_1);
		System.out.println(GerenciadorMensagens.MENU_CONTROLLER_MENU_OP_2);
		System.out.println(GerenciadorMensagens.MENU_CONTROLLER_MENU_OP_3);
	}

	public int obterOpMenu() {
		int op = ObterInt.obterInt(scanner, GerenciadorMensagens.MENU_CONTROLLER_DIGITE_OPÇAO, 2);
		return op;
	}

}
