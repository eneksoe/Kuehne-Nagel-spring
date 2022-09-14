package com.example.Kuehne.Nagel.resolver;


import com.example.Kuehne.Nagel.exception.ResolvingException;
import com.example.Kuehne.Nagel.models.Customer;
import com.example.Kuehne.Nagel.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomerResolver {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer resolveByEmail(String email) {
        final Customer customer = customerRepository.findByEmail(email);
        if (customer == null) {
            String message = "Customer with email: " + email + " does not exist in the system!";
            log.warn(message);
            throw new ResolvingException(message);
        }
        return customer;
    }
}
