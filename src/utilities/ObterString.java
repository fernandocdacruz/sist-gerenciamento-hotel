package utilities;

import java.util.Scanner;

public class ObterString {

	public static String obterString(Scanner scanner, String prompt) {
		String frase = null;
		boolean inputValido = false;
		boolean testeFraseVazia = false;
		while (!inputValido) {
			try {
				System.out.print(prompt);
				if (!testeFraseVazia) {
					scanner.nextLine();
				}
				frase = scanner.nextLine();
				testeFraseVazia = testarString(frase);
				testarExcessao(testeFraseVazia);
				inputValido = true;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		return frase;
	}

	private static boolean testarString(String frase) {
		boolean testeFraseVazia = false;
		if (frase.isBlank()) {
			testeFraseVazia = true;
		}
		return testeFraseVazia;
	}
	
	private static void testarExcessao(boolean testeFraseVazia) {
		if (testeFraseVazia) {
			throw new IllegalArgumentException(GerenciadorMensagens.OBTER_STRING_INPUT_EM_BRANCO);
		}
	}

}
