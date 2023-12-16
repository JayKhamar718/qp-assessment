package questionPro.Assessment.ServiceImpl;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import questionPro.Assessment.Repository.GroceryItem;
import questionPro.Assessment.Repository.GroceryItemRepository;
import questionPro.Assessment.Repository.Order;
import questionPro.Assessment.Repository.OrderRepository;
import questionPro.Assessment.Repository.OrderStatus;
import questionPro.Assessment.Repository.User;
import questionPro.Assessment.Repository.UserRepository;
import questionPro.Assessment.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private GroceryItemRepository groceryItemRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Iterable<User> viewAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> viewUserById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public Order placeOrder(Order order) {

		Order savedOrder;
		try {

			if (Objects.isNull(order.getGroceryItems())) {
				throw new RuntimeException("No Item Added in the cart.");
			}
			for (GroceryItem groceryItem : order.getGroceryItems()) {
				if (groceryItem.getId() == null) {
					throw new RuntimeException("Order Id not found");
				}
				Optional<GroceryItem> optionalGroceryItem = groceryItemRepository.findById(groceryItem.getId());
				if (optionalGroceryItem.isEmpty()) {
					throw new RuntimeException("Item Not found.");
				}
				if (optionalGroceryItem.get().getInventory() < groceryItem.getInventory()) {
					throw new RuntimeException("Item out of stock : " + groceryItem.getName());
				}
			}

			if (Objects.isNull(order.getUser())) {
				throw new RuntimeException("User Not found.");
			}
			Optional<User> optionalUser = userRepository.findById(order.getUser().getId());
			if (optionalUser.isEmpty()) {
				throw new RuntimeException("User Not found.");
			}

			double totalAmount = calculateTotalAmount(order.getGroceryItems());
			order.setTotalAmount(totalAmount);
			order.setStatus(OrderStatus.PENDING);
			savedOrder = orderRepository.save(order);
			updateInventory(savedOrder.getGroceryItems());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

		return savedOrder;
	}

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	private double calculateTotalAmount(Iterable<GroceryItem> groceryItems) {
		return StreamSupport.stream(groceryItems.spliterator(), false).mapToDouble(GroceryItem::getPrice).sum();
	}

	private void updateInventory(Iterable<GroceryItem> groceryItems) {
		for (GroceryItem groceryItem : groceryItems) {
			GroceryItem updateGroceryItem = groceryItemRepository.findById(groceryItem.getId()).get();
			updateGroceryItem.setInventory(updateGroceryItem.getInventory() - groceryItem.getInventory());
			groceryItemRepository.save(updateGroceryItem);
		}
	}
}