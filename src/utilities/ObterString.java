package utilities;

import java.util.InputMismatchException;
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
	
	private static void testarExcessao(Boolean testeFraseVazia) {
		if (testeFraseVazia) {
			throw new IllegalArgumentException("\nEsse input não pode ficar em branco. Tente novamente.");
		}
	}

}
