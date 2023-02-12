package com.Inventario.demo.Controllers;

import com.Inventario.demo.Repositories.OrderRepository;
import com.Inventario.demo.Repositories.ProductRepository;
import com.Inventario.demo.Entity.Order;
import com.Inventario.demo.Entity.Product;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    //localhost:2070/api/products
    @GetMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAllOrders(String customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    //localhost:2070/api/products
    @PostMapping
    public ResponseEntity<Object> createOrder(@Validated  @RequestBody Order request,BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        request.setDate(new Date());
        return new ResponseEntity<>(orderRepository.save(request),HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Object> updateOrder(@PathVariable String orderId, @Validated  @RequestBody Order request, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (!orderOptional.isPresent()){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Orden no encontrado");
        }
        request.setId(orderId);
        return new ResponseEntity<>(orderRepository.save(request),HttpStatus.OK);
    }

    //localhost:2070/api/products/{productId}
    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public Order deleteOrder(@PathVariable String orderId) {
        Optional<Order> optionalProduct = orderRepository.findById(orderId);
        if (!optionalProduct.isPresent()){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Orden no encontrado id ");
        }
        Order order = optionalProduct.get();
        orderRepository.delete(order);
        return order;
    }
}
