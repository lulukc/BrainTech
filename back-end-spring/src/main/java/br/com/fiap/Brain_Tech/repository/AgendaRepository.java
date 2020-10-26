package br.com.fiap.Brain_Tech.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.Brain_Tech.model.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

	@Query("SELECT ag FROM Agenda ag WHERE ag.dtMarcada BETWEEN :dtInicio AND :dtFim")
	List<Agenda> buscarEntreDatas(@Param("dtInicio") Date dtInicio, @Param("dtFim") Date dtFim);
}
