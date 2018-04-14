package br.usjt.arqsw.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Lucas Copque - 816112862
 *
 */
@Entity
public class Usuario {

	private int id;

	@NotNull(message = "Informe um e-mail")
	private String email;

	@NotNull(message = "Informe uma senha")
	private String senha;

	public Usuario() {

	}

	public Usuario(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", senha=" + senha + "]";
	}

}
