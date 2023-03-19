package br.com.contact_saver.model;

public class UserResponse {
	private int statusCode;
	private int id;
	private String nome;
	private String email;
	private String password;
	private int age;

	public UserResponse(int statusCode, User user) {
		this.statusCode = statusCode;
		this.id = user.getId();
		this.nome = user.getNome();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.age = user.getAge();
	}
}