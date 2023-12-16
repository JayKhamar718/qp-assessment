package questionPro.Assessment.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import questionPro.Assessment.Repository.GroceryItem;
import questionPro.Assessment.Service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/add-item")
	public ResponseEntity<GroceryItem> addGroceryItem(@RequestBody GroceryItem groceryItem) {
		GroceryItem addedItem = adminService.addGroceryItem(groceryItem);
		return new ResponseEntity<>(addedItem, HttpStatus.CREATED);
	}

	@GetMapping("/view-items")
	public Iterable<GroceryItem> viewAllGroceryItems() {
		return adminService.viewAllGroceryItems();
	}

	@GetMapping("/view-item/{id}")
	public Optional<GroceryItem> getItemById(@PathVariable Long id) {
		return adminService.getItemById(id);
	}

	@DeleteMapping("/remove-item/{id}")
	public ResponseEntity<Void> removeGroceryItem(@PathVariable Long id) {
		adminService.removeGroceryItem(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/update-item/{id}")
	public ResponseEntity<GroceryItem> updateGroceryItem(@PathVariable Long id, @RequestBody GroceryItem updatedItem) {
		GroceryItem updated = adminService.updateGroceryItem(id, updatedItem);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}

	@PatchMapping("/manage-inventory/{id}")
	public ResponseEntity<Void> manageInventory(@PathVariable Long id, @RequestParam int quantity) {
		adminService.manageInventory(id, quantity);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}