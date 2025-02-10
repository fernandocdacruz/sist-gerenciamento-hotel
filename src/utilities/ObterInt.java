package utilities;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ObterInt {

	public static int obterInt(Scanner scanner, String prompt, int opcaoTeste) {
		int valor = 0;
		boolean inputValido = false;
		while (!inputValido) {
			try {
				System.out.print(prompt);
				valor = scanner.nextInt();
				testarValor(valor, opcaoTeste);
				inputValido = true;
			} catch (InputMismatchException e) {
				System.out.println(GerenciadorMensagens.OBTER_INT_INPUT_INVALIDO);
				scanner.next();
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		return valor;
	}

	public static void testarValor(int valor, int opcaoTeste) {
		switch (opcaoTeste) {
		case 1:
			testarOpcao1(valor);
			break;
		case 2:
			testarOpcao2(valor);
			break;
		case 3:
			testarOpcao3(valor);
			break;
		}
	}

	public static void testarOpcao1(int valor) {
		if (valor < 1 || valor > 10) {
			throw new IllegalArgumentException(GerenciadorMensagens.OBTER_INT_QUARTO_INEXISTENTE);
		}
	}
	
	public static void testarOpcao2(int valor) {
		if (valor < 0 || valor > 3) {
			throw new IllegalArgumentException(GerenciadorMensagens.OBTER_INT_MENU_CONTROLLER_OPÇAO_INVALIDA);
		}
	}
	
	public static void testarOpcao3(int valor) {
		if (valor < 1) {
			throw new IllegalArgumentException(GerenciadorMensagens.OBTER_INT_RESERVA_INEXISTENTE);
		}
	}

}
