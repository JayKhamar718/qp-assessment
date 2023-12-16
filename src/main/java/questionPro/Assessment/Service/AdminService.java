package questionPro.Assessment.Service;

import java.util.Optional;

import questionPro.Assessment.Repository.GroceryItem;

public interface AdminService {

	GroceryItem addGroceryItem(GroceryItem groceryItem);

	Iterable<GroceryItem> viewAllGroceryItems();

	Optional<GroceryItem> getItemById(Long id);

	void removeGroceryItem(Long id);

	GroceryItem updateGroceryItem(Long id, GroceryItem updatedItem);

	void manageInventory(Long id, int quantity);

}
