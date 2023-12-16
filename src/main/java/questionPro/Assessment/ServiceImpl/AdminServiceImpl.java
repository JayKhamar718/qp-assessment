package questionPro.Assessment.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import questionPro.Assessment.Repository.GroceryItem;
import questionPro.Assessment.Repository.GroceryItemRepository;
import questionPro.Assessment.Service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private GroceryItemRepository groceryItemRepository;

	@Override
	public GroceryItem addGroceryItem(GroceryItem groceryItem) {
		return groceryItemRepository.save(groceryItem);
	}

	@Override
	public Iterable<GroceryItem> viewAllGroceryItems() {
		return groceryItemRepository.findAll();
	}

	@Override
	public Optional<GroceryItem> getItemById(Long id) {
		return groceryItemRepository.findById(id);
	}

	@Override
	public void removeGroceryItem(Long id) {
		groceryItemRepository.deleteById(id);
	}

	@Override
	public GroceryItem updateGroceryItem(Long id, GroceryItem updatedItem) {
		updatedItem.setId(id);
		return groceryItemRepository.save(updatedItem);
	}

	@Override
	public void manageInventory(Long id, int quantity) {
		GroceryItem groceryItem = groceryItemRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Grocery item not found"));
		groceryItem.setInventory(groceryItem.getInventory() + quantity);
		groceryItemRepository.save(groceryItem);
	}
}
