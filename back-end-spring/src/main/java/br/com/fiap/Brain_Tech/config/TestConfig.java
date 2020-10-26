package br.com.fiap.Brain_Tech.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.fiap.Brain_Tech.model.Empresa;
import br.com.fiap.Brain_Tech.model.Endereco;
import br.com.fiap.Brain_Tech.model.Estoque;
import br.com.fiap.Brain_Tech.model.Funcionario;
import br.com.fiap.Brain_Tech.model.Produto;
import br.com.fiap.Brain_Tech.model.enums.PerfilUser;
import br.com.fiap.Brain_Tech.repository.EstoqueRepository;
import br.com.fiap.Brain_Tech.repository.ProdutoRepository;
import br.com.fiap.Brain_Tech.service.EmpresaService;
import br.com.fiap.Brain_Tech.service.FuncionarioService;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private FuncionarioService funcionarioService;

	@Autowired
	private EstoqueRepository estoqueRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public void run(String... args) throws Exception {
		Endereco end1 = new Endereco(null, "rua generica", null, "20", "0974846", "bairo", "cidade", "estado");
		Empresa em1 = new Empresa("nome empresa", "1756748856647", "empresa@mail.com", "empresa1", "123456", null,
				"nome contato", "123455645", end1);
		empresaService.criar(em1);

		Produto pro1 = new Produto(null, "Negro de fumo", "pozinho preto", 1000.00);
		Produto pro2 = new Produto(null, "Fumo de Negro", "outro pozinho preto", 500.00);
		produtoRepository.saveAll(Arrays.asList(pro1, pro2));

		Estoque e1 = new Estoque(null, null, 1000000l, pro1);
		Estoque e2 = new Estoque(null, null, 1000000l, pro2);
		estoqueRepository.saveAll(Arrays.asList(e1, e2));

		Funcionario f1 = new Funcionario(null, "nome funcionario", "funcionario1", "12345", null);
		Funcionario f2 = new Funcionario(null, "nome funcionario", "funcionario2", "12345", null);
		f1.addPerfil(PerfilUser.ADMIN);

		funcionarioService.criar(f1);
		funcionarioService.criar(f2);
	}

}
