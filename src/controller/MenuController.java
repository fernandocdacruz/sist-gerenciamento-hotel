package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import utilities.GerenciadorMensagens;

public class MenuController {
	
	private Scanner scanner;
	
	public MenuController(Scanner scanner) {
		this.scanner = scanner;
	}

	public void mostrarMenu() {
		System.out.println(GerenciadorMensagens.MENU_CONTROLLER_MENU_OP_0);
		System.out.println(GerenciadorMensagens.MENU_CONTROLLER_MENU_OP_1);
		System.out.println(GerenciadorMensagens.MENU_CONTROLLER_MENU_OP_2);
	}
	
	public int obterOpMenu() {
	int op = 0;
	boolean inputValido = false;
	while (!inputValido) {
		try {
			System.out.print(GerenciadorMensagens.MENU_CONTROLLER_DIGITE_OPÇAO);
			op = scanner.nextInt();
			testarOp(op);
			inputValido = true;
		} catch (InputMismatchException e) {
			System.out.println(GerenciadorMensagens.MENU_CONTROLLER_INPUT_INVALIDO);
			scanner.next();
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	return op;
	}
	
	public void testarOp(int op) {
		if (op < 0 || op > 2) {
			throw new IllegalArgumentException(GerenciadorMensagens.MENU_CONTROLLER_OPÇAO_INVALIDA);
		}
	}
	
}
