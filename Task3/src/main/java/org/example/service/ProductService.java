package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.exceptions.DataNotFoundException;
import org.example.model.Order;
import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product post(Product product){
        return productRepository.save(product);
    }

    public Product put(Product product,Long id){
        get(id);
        product.setProductId(id);
        return productRepository.save(product);
    }

    public Product get(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Нет записи с id=" + id));
        return product;
    }

    public void delete(Long id){
        get(id);
        productRepository.deleteById(id);
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }
}
