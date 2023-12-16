package questionPro.Assessment.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import questionPro.Assessment.Repository.Order;
import questionPro.Assessment.Repository.User;
import questionPro.Assessment.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/view-users")
	public Iterable<User> viewAllUsers() {
		return userService.viewAllUsers();
	}

	@GetMapping("/view-user/{id}")
	public Optional<User> viewUserById(@PathVariable Long id) {
		return userService.viewUserById(id);
	}

	@PostMapping("/place-order")
	public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
		Order placedOrder = userService.placeOrder(order);
		return new ResponseEntity<>(placedOrder, HttpStatus.CREATED);
	}

	@PostMapping("/create-user")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User createdUser = userService.createUser(user);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
}