package br.com.fiap.Brain_Tech.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import br.com.fiap.Brain_Tech.model.enums.PerfilUser;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_funcionario", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class Funcionario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String nome;

	@NotNull
	private String username;

	@Transient
	private String password;

	@JsonIgnore
	@NotNull
	private String passwordHash;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<>();

	public Funcionario() {
		addPerfil(PerfilUser.FUNCIONARIO);
	}

	public Funcionario(Long id, String nome, String username, String password, String passwordHash) {
		this.id = id;
		this.nome = nome;
		this.username = username;
		this.password = password;
		this.passwordHash = passwordHash;
		addPerfil(PerfilUser.FUNCIONARIO);
	}

	public Set<PerfilUser> getPerfis() {
		return perfis.stream().map(x -> PerfilUser.valueOf(x)).collect(Collectors.toSet());
	}

	public void addPerfil(PerfilUser perfil) {
		perfis.add(perfil.getCode());
	}

}
