package com.springboot.reg.items.Controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;          // Import SLF4J Logger
import org.slf4j.LoggerFactory;   // Import SLF4J LoggerFactory

import com.springboot.reg.items.model.Items;
import com.springboot.reg.items.service.ItemService;

@CrossOrigin(origins = "http://localhost:9099")
@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class); // Create logger

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String getItems(Model model) {
        List<Items> items = itemService.getAllItems();
        model.addAttribute("items", items);
        return "items";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Items> getItemById(@PathVariable("id") long itemId) {
        return new ResponseEntity<>(itemService.getItemsById(itemId), HttpStatus.OK);
    }

    @GetMapping("/{id}/edit")
    public String editItem(@PathVariable("id") long itemId, Model model) {
        Items item = itemService.getItemsById(itemId);
        model.addAttribute("item", item);
        return "edit"; 
    }

    @PostMapping("/{id}")
    public String updateItem(@PathVariable("id") long id, @ModelAttribute Items item) {
        itemService.updateItem(item, id);
        return "redirect:/items"; 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteItem(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        
        if (!itemService.existsById(id)) {
            logger.warn("Attempted to delete non-existent item with ID: " + id);
            response.put("message", "Item not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        
        itemService.deleteItem(id);
        logger.info("Item with ID " + id + " deleted successfully.");
        response.put("message", "Item deleted successfully!");
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/add")
    public String showAddItemForm(Model model) {
        model.addAttribute("item", new Items());
        return "add"; 
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute Items item) {
        itemService.saveItem(item);
        return "redirect:/items"; 
    }
}