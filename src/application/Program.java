package application;

import java.util.Locale;
import java.util.Scanner;

import controller.ExecutarOpController;
import controller.MenuController;
import utilities.GerenciadorMensagens;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner scanner = new Scanner(System.in);
		MenuController menu = new MenuController(scanner);
		ExecutarOpController executarOp = new ExecutarOpController(scanner);
		System.out.println(GerenciadorMensagens.PROGRAM_ABERTURA);
		int menuOp = 0;
		do {
			menu.mostrarMenu();
			menuOp = menu.obterOpMenu();
			executarOp.executarOp(menuOp);
		} while (menuOp != 0);
		System.out.println(GerenciadorMensagens.PROGRAM_ENCERRAMENTO);
		scanner.close();
	}
}
