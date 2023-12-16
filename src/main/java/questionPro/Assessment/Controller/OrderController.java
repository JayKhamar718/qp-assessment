package questionPro.Assessment.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import questionPro.Assessment.Repository.Order;
import questionPro.Assessment.Service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/view-orders")
	public Iterable<Order> getAllOrders() {
		return orderService.getAllOrders();
	}

	@GetMapping("/view-order/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
		Order order = orderService.getOrderById(id);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}

}
