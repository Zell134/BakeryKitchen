package com.bakery.bakeryKitchen.data;

import com.bakery.bakeryKitchen.models.OrderElement;
import org.springframework.data.repository.CrudRepository;

public interface OrderElementRepository extends CrudRepository<OrderElement, Long>{
    
}
