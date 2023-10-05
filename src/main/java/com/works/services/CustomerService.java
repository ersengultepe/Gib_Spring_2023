package com.works.services;

import com.works.entities.Customer;
import com.works.entities.dto.CustomerDto;
import com.works.repositories.CustomerRepository;
import com.works.utils.Util;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    final CustomerRepository customerRepository;
    final ModelMapper modelMapperA;
    final HttpServletRequest req;

    public ResponseEntity register(Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsIgnoreCase(customer.getEmail());
        if (optionalCustomer.isPresent()) {
            return Util.fail("This email use! - " + customer.getEmail(), HttpStatus.BAD_REQUEST);
        }else {
            customerRepository.save(customer);
            return Util.success(customer);
        }
    }

    public ResponseEntity login( String email, String password ) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsIgnoreCaseAndPasswordEquals(email, password);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();

            //CustomerDto dto = new CustomerDto();
            //BeanUtils.copyProperties(customer, dto);

            CustomerDto dto = modelMapperA.map(customer, CustomerDto.class);
            req.getSession().setAttribute("user", dto);
            return Util.success(dto);
        }
        return Util.fail("Email or Password Fail", HttpStatus.UNAUTHORIZED);
    }

}
