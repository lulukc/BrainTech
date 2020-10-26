package br.com.fiap.Brain_Tech.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fiap.Brain_Tech.model.Empresa;
import br.com.fiap.Brain_Tech.model.Funcionario;
import br.com.fiap.Brain_Tech.model.enums.PerfilUser;
import br.com.fiap.Brain_Tech.repository.EmpresaRepository;
import br.com.fiap.Brain_Tech.repository.FuncionarioRepository;
import br.com.fiap.Brain_Tech.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Funcionario funcionario = funcionarioRepository.findByUsername(username);
		Empresa empresa = empresaRepository.findByUsername(username);

		if (funcionario == null && empresa == null) {
			throw new UsernameNotFoundException(username);
		}
		UserSS userSS;

		if (funcionario == null) {
			Set<PerfilUser> perfis = new HashSet<>();
			perfis.add(empresa.getPerfil());
			userSS = new UserSS(empresa.getId(), empresa.getUsername(), empresa.getPasswordHash(), perfis);

		} else {
			userSS = new UserSS(funcionario.getId(), funcionario.getUsername(), funcionario.getPasswordHash(),
					funcionario.getPerfis());
		}

		return userSS;
	}

}
