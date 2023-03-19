package br.com.contact_saver.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.contact_saver.model.User;
import br.com.contact_saver.model.repositories.UserRepository;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping
	@RequestMapping(path = "/signUp")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<User> novoUser(@Valid User user) {
		boolean isEmailNotUsed = checkEmailNotUsed(user.getEmail());
		if (isEmailNotUsed) {
			userRepository.save(user);
			return ResponseEntity.ok(user);
		} else {
			return null;
		}
	}

	@PostMapping
	@CrossOrigin
	@RequestMapping(path = "/login")
	public User obterUserByEmailAndPassword(@RequestBody User userParam) {
		try {
			User user = userRepository.findUserByEmailAndPassword(userParam.getEmail(), userParam.getPassword());
			if (user != null) {
				user.setStatusCode(200);
				return user;
			} else {
				return new User("", "", "", 0, 404, "Usuário não encontrado");
				// user.setStatusCode(-1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: " + e);
		}
		return null;
	}

	private boolean checkEmailNotUsed(String email) {
		List<User> listaUser = (List<User>) userRepository.findUserByEmail(email);
		return listaUser.isEmpty();
	}

	@GetMapping
	@CrossOrigin
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<List<User>> obterUsers() {
		List<User> listaUser = new ArrayList<User>();
		try {
			listaUser = (List<User>) userRepository.findAll();
		} catch (Exception e) {
			System.out.println("Error");
		}
		return ResponseEntity.ok(listaUser);
	}

	@DeleteMapping
	@ResponseStatus(code = HttpStatus.OK, reason = "OK")
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	@PutMapping
	@ResponseStatus(code = HttpStatus.OK, reason = "OK")
	public User updateUserById(User user) {
		User updateUser = userRepository.findById(user.getId()).orElseThrow();

		updateUser.setNome(user.getNome());
		updateUser.setEmail(user.getEmail());
		updateUser.setPassword(user.getPassword());
		updateUser.setAge(user.getAge());

		userRepository.save(updateUser);

		return updateUser;
	}
}
