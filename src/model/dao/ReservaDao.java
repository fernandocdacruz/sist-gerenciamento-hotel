package model.dao;

import java.util.List;

import model.entities.Reserva;

public interface ReservaDao {

	void criarReserva(Reserva reserva);
	void deletarReserva(int id);
	List<Reserva> listarTodasReservas();
}
