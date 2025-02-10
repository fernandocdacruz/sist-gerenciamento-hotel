package model.dao;

import java.util.List;

import model.entities.Quarto;

public interface QuartoDao {
	List<Quarto> acharQuartosDisponiveis();
	void atualizarDisponibilidadeParaIndisponivel(int numero);
	void atualizarDisponibilidadeParaDisponivel(int numero);
}
