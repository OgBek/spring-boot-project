package com.springboot.reg.items.repo;


import org.springframework.data.jpa.repository.JpaRepository;


import com.springboot.reg.items.model.Items;

public interface ItemRepo extends JpaRepository<Items, Long> {
	

}
