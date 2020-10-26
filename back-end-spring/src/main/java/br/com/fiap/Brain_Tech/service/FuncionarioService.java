package br.com.fiap.Brain_Tech.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fiap.Brain_Tech.model.Funcionario;
import br.com.fiap.Brain_Tech.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository repository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public Funcionario buscarUM(Long id) {
		Optional<Funcionario> obj = repository.findById(id);
		return obj.get();
	}

	public List<Funcionario> buscarTodos() {
		return repository.findAll();
	}

	public Funcionario criar(Funcionario funcionario) {
		funcionario.setPasswordHash(passwordEncoder.encode(funcionario.getPassword()));
		funcionario.setPassword(null);
		return repository.save(funcionario);
	}

	public void apagar(Long id) {
		repository.deleteById(id);
	}

	public Funcionario alterarDados(Funcionario funcionario, Long id) {
		Funcionario entity = repository.getOne(id);
		atulizarDados(entity, funcionario, 1);
		return repository.save(entity);
	}

	public Funcionario alterarSenha(Funcionario funcionario, Long id) {
		Funcionario entity = repository.getOne(id);
		atulizarDados(entity, funcionario, 2);
		return repository.save(entity);
	}

	private void atulizarDados(Funcionario entity, Funcionario funcionario, Integer marcador) {
		if (marcador == 1) {
			entity.setNome(funcionario.getNome());

		} else {
			entity.setPasswordHash(funcionario.getPasswordHash());
		}
	}
}
