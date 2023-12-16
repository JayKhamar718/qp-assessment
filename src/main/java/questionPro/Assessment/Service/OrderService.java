package questionPro.Assessment.Service;

import questionPro.Assessment.Repository.Order;

public interface OrderService {

	Iterable<Order> getAllOrders();

	Order getOrderById(Long id);
}