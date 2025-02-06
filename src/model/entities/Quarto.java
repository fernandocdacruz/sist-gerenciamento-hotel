package model.entities;

import model.enums.DisponibilidadeQuarto;

public class Quarto {

	public int id;
	public int numero;
	public DisponibilidadeQuarto disponivel;
	
	public Quarto() {
	}
	
	public Quarto(int id, int numero, DisponibilidadeQuarto disponivel) {
		super();
		this.id = id;
		this.numero = numero;
		this.disponivel = disponivel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public DisponibilidadeQuarto getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(DisponibilidadeQuarto disponivel) {
		this.disponivel = disponivel;
	}

	@Override
	public String toString() {
		return "Quarto " + numero + " - DISPONIVEL: " + testeDisponibilidadeQuarto();
	}
	
	public String testeDisponibilidadeQuarto() {
		return disponivel == DisponibilidadeQuarto.DISPONIVEL ? "SIM" : "NÃO";
	}
	
}
