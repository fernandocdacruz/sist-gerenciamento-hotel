package model.entities;

public class Reserva {

	private int id;
	private String cliente;
	private Quarto quarto;
	
	public Reserva() {
	}

	public Reserva(String cliente, Quarto quarto) {
		this.cliente = cliente;
		this.quarto = quarto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}
	
}
