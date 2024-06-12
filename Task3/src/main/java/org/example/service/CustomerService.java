package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.exceptions.DataNotFoundException;
import org.example.model.Customer;
import org.example.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer post(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer put(Customer customer,Long id){
        get(id);
        customer.setCustomerId(id);
        return customerRepository.save(customer);
    }

    public Customer get(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Нет записи с id=" + id));
        return customer;
    }

    public void delete(Long id){
        get(id);
        customerRepository.deleteById(id);
    }
}
