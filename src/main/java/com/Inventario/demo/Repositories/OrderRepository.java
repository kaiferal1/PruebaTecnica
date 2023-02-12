package com.Inventario.demo.Repositories;

import com.Inventario.demo.Entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order,String> {
    @Query("{ 'customer_id' : ?0 }")
    List<Order> findByCustomerId(String customerId);
}
