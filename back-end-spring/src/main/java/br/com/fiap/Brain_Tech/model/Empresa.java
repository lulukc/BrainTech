package br.com.fiap.Brain_Tech.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import br.com.fiap.Brain_Tech.model.enums.PerfilUser;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor

@Data
@Entity
@Table(name = "tb_empresa", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String nomeEmpresa;
	@NotNull
	private String cnpjEmpresa;
	@NotNull
	private String emailEmpresa;

	@NotNull
	private String username;

	@Transient
	private String password;

	@JsonIgnore
	@NotNull
	private String passwordHash;

	@NotNull
	private String nomePessoaContato;
	@NotNull
	private String telefoneContato;

	private Integer perfil;

	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;

	@JsonIgnore
	@OneToMany(mappedBy = "empresa")
	private List<Pedido> pedidos = new ArrayList<>();

	public Empresa() {
		setPerfil(PerfilUser.EMPRESA);
	}

	public Empresa(String nomeEmpresa, String cnpjEmpresa, String emailEmpresa, String username, String password,
			String passwordHash, String nomePessoaContato, String telefoneContato, Endereco endereco) {
		this.nomeEmpresa = nomeEmpresa;
		this.cnpjEmpresa = cnpjEmpresa;
		this.emailEmpresa = emailEmpresa;
		this.username = username;
		this.password = password;
		this.passwordHash = passwordHash;
		this.nomePessoaContato = nomePessoaContato;
		this.telefoneContato = telefoneContato;
		this.endereco = endereco;
		setPerfil(PerfilUser.EMPRESA);
	}

	public PerfilUser getPerfil() {
		return PerfilUser.valueOf(perfil);
	}

	public void setPerfil(PerfilUser perfil) {
		if (perfil != null) {
			this.perfil = perfil.getCode();
		}
	}

}