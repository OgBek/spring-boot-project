package com.springboot.reg.items.service;


import java.util.List;



import com.springboot.reg.items.model.Items;

public interface ItemService {

	Items saveItem(Items item);
	List<Items> getAllItems();
	Items getItemsById(long id);
	Items updateItem(Items item, long id);
	
	void deleteItem(long id);
	boolean existsById(long id);
}
