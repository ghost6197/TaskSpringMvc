package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.exceptions.DataNotFoundException;
import org.example.model.Order;
import org.example.model.Product;
import org.example.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order post(Order order){
        return orderRepository.save(order);
    }

    public Order put(Order order,Long id){
        get(id);
        order.setOrderId(id);
        return orderRepository.save(order);
    }

    public Order get(Long id){
        Order customer = orderRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Нет записи с id=" + id));
        Double totalPrice=customer.getProducts().stream().mapToDouble(Product::getPrice).sum();
        customer.setTotalPrice(totalPrice);
        return customer;
    }

    public void delete(Long id){
        get(id);
        orderRepository.deleteById(id);
    }
}
