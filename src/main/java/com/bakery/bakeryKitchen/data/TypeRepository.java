package com.bakery.bakeryKitchen.data;

import com.bakery.bakeryKitchen.models.Type;
import org.springframework.data.repository.CrudRepository;



public interface TypeRepository extends CrudRepository<Type, Long> {
    
    public Type findByName(String name);
    public Type findById(long id);
    
}
