package br.com.fiap.Brain_Tech.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fiap.Brain_Tech.model.Empresa;
import br.com.fiap.Brain_Tech.repository.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository repository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public Empresa buscarUM(Long id) {
		Optional<Empresa> obj = repository.findById(id);
		return obj.get();
	}

	public List<Empresa> buscarTodos() {
		return repository.findAll();
	}

	public Empresa criar(Empresa empresa) {
		empresa.setPasswordHash(passwordEncoder.encode(empresa.getPassword()));
		empresa.setPassword(null);
		return repository.save(empresa);
	}

	public void apagar(Long id) {
		repository.deleteById(id);
	}

	public Empresa alterarDados(Empresa empresa, Long id) {
		Empresa entity = repository.getOne(id);
		atulizarDados(entity, empresa, 1);
		return repository.save(entity);
	}

	public Empresa alterarSenha(Empresa empresa, Long id) {
		Empresa entity = repository.getOne(id);
		atulizarDados(entity, empresa, 2);
		return repository.save(entity);
	}

	private void atulizarDados(Empresa entity, Empresa empresa, Integer marcador) {
		if (marcador == 1) {
			entity.setNomeEmpresa(empresa.getNomeEmpresa());
			entity.setNomePessoaContato(empresa.getNomePessoaContato());
			entity.setTelefoneContato(empresa.getTelefoneContato());
		} else {
			entity.setPasswordHash(empresa.getPasswordHash());
		}
	}
}
