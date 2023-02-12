package com.Inventario.demo.Controllers;

import com.Inventario.demo.Repositories.ProductRepository;
import com.Inventario.demo.Entity.Product;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@Validated
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    //localhost:2070/api/products
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //localhost:2070/api/products
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> createProduct(@RequestBody @Validated Product request, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productRepository.save(request), HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Object> updateProduct(@PathVariable String productId, @RequestBody @Validated  Product request, BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent()){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
        request.setId(productId);
        return new ResponseEntity<>(productRepository.save(request), HttpStatus.OK);
    }

    //localhost:2070/api/products/{productId}
    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public Product deleteProduct(@PathVariable String productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent()){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado id ");
        }
        Product product = optionalProduct.get();
        productRepository.delete(product);
        return product;
    }

}
