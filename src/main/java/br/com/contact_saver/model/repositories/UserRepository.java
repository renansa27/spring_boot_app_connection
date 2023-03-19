package br.com.contact_saver.model.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.contact_saver.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	List<User> findUserByEmail(String email);
	
	User findUserByEmailAndPassword(String email, String password);
	
	List<User> findUserByPassword(String passowrd);
	
	List<User> findUserByNome(String nome);
}
