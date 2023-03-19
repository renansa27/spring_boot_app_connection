package br.com.contact_saver.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.springframework.lang.Nullable;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Nullable
	private String nome;

	@NotBlank(message = "email é obrigatório")
	private String email;

	@NotBlank(message = "password é obrigatório")
	private String password;

	@Nullable
	private int age;

	@Transient
	private int statusCode;
	
	@Transient
	private String statusMessage;

	public User() {
	}

	public User(String nome, String email, String password, int age, int statusCode, String statusMessage) {
		super();
		this.nome = nome;
		this.email = email;
		this.password = password;
		this.age = age;
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
}
