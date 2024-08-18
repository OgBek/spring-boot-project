package com.springboot.reg.items.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import com.springboot.reg.items.Exception.ResourceNotFoundException;
import com.springboot.reg.items.model.Items;
import com.springboot.reg.items.repo.ItemRepo;


@Service
public class ItemServiceImpl implements ItemService {

	
	
	 private ItemRepo itemrepo;
	 
	
	public ItemServiceImpl(ItemRepo itemrepo) {
		super();
		this.itemrepo = itemrepo;
	}


	@Override
	public Items saveItem(Items item) {
		return itemrepo.save(item);
	}


	@Override
	public List<Items> getAllItems() {
		
		return itemrepo.findAll();
	}


	@Override
	public Items getItemsById(long id) {
		
		Optional<Items>  item= itemrepo.findById(id);
		
		if(item.isPresent()) {
			return item.get();
		}else {
			
			throw new ResourceNotFoundException("Items","Id",id);
			
		}
	}


	@Override
	public Items updateItem(Items item, long id) {
		Items Exitem = itemrepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Items","Id",id));
		Exitem.setIname(item.getIname());
		Exitem.setDescription(item.getDescription());
		
		itemrepo.save(Exitem);
		return Exitem;
	}




	


	@Override
	public void deleteItem(long id) {
	    if (!itemrepo.existsById(id)) {
	        throw new ResourceNotFoundException("Items", "Id", id);
	    }
	    itemrepo.deleteById(id);
	}


	@Override
	public boolean existsById(long id) {
		 return itemrepo.existsById(id);
	}


	
	

	

	
}
