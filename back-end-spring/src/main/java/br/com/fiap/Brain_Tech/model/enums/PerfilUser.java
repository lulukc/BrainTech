package br.com.fiap.Brain_Tech.model.enums;

public enum PerfilUser {

	ADMIN(1, "ROLE_ADMIN"), FUNCIONARIO(2, "ROLE_CLIENTE"), EMPRESA(3, "ROLE_EMPRESA");

	private Integer code;
	private String descricao;

	private PerfilUser(Integer code, String descricao) {
		this.code = code;
		this.descricao = descricao;
	}

	public int getCode() {
		return code;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static PerfilUser valueOf(Integer code) {
		for (PerfilUser value : PerfilUser.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("invalide StatusPagamento code");

	}
}
