package questionPro.Assessment.Service;

import java.util.Optional;

import questionPro.Assessment.Repository.Order;
import questionPro.Assessment.Repository.User;

public interface UserService {

	Order placeOrder(Order order);

	User createUser(User user);

	Iterable<User> viewAllUsers();

	Optional<User> viewUserById(Long id);
}
