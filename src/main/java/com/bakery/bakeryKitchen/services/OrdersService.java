package com.bakery.bakeryKitchen.services;

import com.bakery.bakeryKitchen.data.OrderRepository;
import com.bakery.bakeryKitchen.models.Order;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {
    
    private final OrderRepository repository;

    @Autowired
    public OrdersService(OrderRepository repository) {
        this.repository = repository;
    }
    
    public Page<Order> findAll(Pageable pageable){
        return repository.findAll(pageable);
    }
    
    public Page<Order> findOrdersBetweenOrderDate(String startDate, String endDate, Pageable pageable) {
        LocalDateTime start;
        LocalDateTime end;
        try {
            start = LocalDate.parse(startDate).atStartOfDay();
            end = LocalDate.parse(endDate).atTime(23, 59);
        } catch (DateTimeParseException e) {
            return null;
        }
        return repository.findByOrderDateBetween(start, end, pageable);
    }

    public void setNotNew(Order order) {
        order.setNew(false);
        repository.save(order);
    }
    
}
