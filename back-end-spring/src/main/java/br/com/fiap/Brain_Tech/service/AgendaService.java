package br.com.fiap.Brain_Tech.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.Brain_Tech.model.Agenda;
import br.com.fiap.Brain_Tech.model.Pedido;
import br.com.fiap.Brain_Tech.model.DTO.AgendaCriarDTO;
import br.com.fiap.Brain_Tech.model.enums.StatusPagamento;
import br.com.fiap.Brain_Tech.repository.AgendaRepository;

@Service
public class AgendaService {

	private SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

	@Autowired
	private AgendaRepository repository;

	@Autowired
	private PedidoService pedidoService;

	public Agenda buscarUM(Long id) {
		Optional<Agenda> obj = repository.findById(id);
		return obj.get();
	}

	public List<Agenda> buscarTodos() {
		return repository.findAll();
	}

	public List<Agenda> buscarEntreData(String dataParametro) throws ParseException {

		String dia = dataParametro.substring(0, 2);
		String mes = dataParametro.substring(2, 4);
		String ano = dataParametro.substring(4, dataParametro.length());

		Date dtInicio = stf.parse(dia + "/" + mes + "/" + ano + " 00:00");
		Date dtFim = stf.parse(dia + "/" + mes + "/" + ano + " 23:59");

		return repository.buscarEntreDatas(dtInicio, dtFim);
	}

	public Agenda criar(AgendaCriarDTO agendaDTO) throws ParseException {
		Pedido pedido = pedidoService.buscarUM(agendaDTO.getPedidoId());
		Agenda agenda = new Agenda();
		if (pedido.getPerfil().getCode() == 2) {
			pedido.setStatusPagamento(StatusPagamento.RETIRADA_AGENDADA);
			agenda.setDtMarcada(stf.parse(agendaDTO.getData()));
			agenda.setPedido(pedido);
		}
		return repository.save(agenda);
	}

	public Agenda retirada(Long id) {
		Agenda agenda = buscarUM(id);
		agenda.getPedido().setStatusPagamento(StatusPagamento.RETIRADO);
		agenda.setRetirado(true);
		return repository.save(agenda);
	}

	public void apagar(Long id) {
		repository.deleteById(id);
	}

}
