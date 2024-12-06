package com.fullstack.controller;

import com.fullstack.exception.RecordNotFoundException;
import com.fullstack.model.Customer;
import com.fullstack.repository.CustomerRepository;
import com.fullstack.service.ICustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")

public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @PostMapping("/signup")
    public ResponseEntity<Customer> signUp(@RequestBody Customer customer) {


        return ResponseEntity.ok(customerService.signUp(customer));
    }

    @GetMapping("/signin/{custEmailId}/{custPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String custEmailId, @PathVariable String custPassword) {
        return ResponseEntity.ok(customerService.signIn(custEmailId, custPassword));
    }

    @GetMapping("/findbyid/{custId}")
    public ResponseEntity<Optional<Customer>> findById(@PathVariable int custId) {

        return ResponseEntity.ok(customerService.findById(custId));

    }

    @GetMapping("/findall")
    public ResponseEntity<List<Customer>> findAll() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/findbyname/{custName}")
    public ResponseEntity<List<Customer>> findByName(@PathVariable String custName) {
        return ResponseEntity.ok(customerService.findAll().stream().filter(cust -> cust.getCustName().equals(custName)).toList());
    }

    @GetMapping("/findbydob/{custDOB}")
    public ResponseEntity<List<Customer>> findByDOB(@PathVariable String custDOB) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        return ResponseEntity.ok(customerService.findAll().stream().filter(cust -> simpleDateFormat.format(cust.getCustDOB()).equals(custDOB)).toList());

    }

    @GetMapping("/findbyanyinput/{input}")
    public ResponseEntity<List<Customer>> findByAnyInput(@PathVariable String input) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return ResponseEntity.ok(customerService.findAll().stream().filter(cust -> cust.getCustName().equals(input)
                || simpleDateFormat.format(cust.getCustDOB()).equals(input)
                || String.valueOf(cust.getCustId()).equals(input)
                || String.valueOf(cust.getCustContactNumber()).equals(input)
                || cust.getCustEmailId().equals(input)).toList());
    }

    @GetMapping("/sortbyiddesc")
    public ResponseEntity<List<Customer>> sortByIdDesc() {
        return ResponseEntity.ok(customerService.findAll().stream().sorted(Comparator.comparing(Customer::getCustId).reversed()).toList());
    }

    @GetMapping("/sortbyname")
    public ResponseEntity<List<Customer>> sortByName() {
        return ResponseEntity.ok(customerService.findAll().stream().sorted(Comparator.comparing(Customer::getCustName)).toList());
    }

    @GetMapping("/sortbydob")
    public ResponseEntity<List<Customer>> sortByDOB() {
        return ResponseEntity.ok(customerService.findAll().stream().sorted(Comparator.comparing(Customer::getCustDOB)).toList());
    }

    @GetMapping("/sortbyaccbalance")
    public ResponseEntity<List<Customer>> sortByAccBalance() {
        return ResponseEntity.ok(customerService.findAll().stream().sorted(Comparator.comparing(Customer::getCustAccBalance)).toList());
    }

    @PutMapping("/update/{custId}")
    public ResponseEntity<Customer> update(@PathVariable int custId, @RequestBody Customer customer) {
        Customer customer1 = customerService.findById(custId).orElseThrow(() -> new RecordNotFoundException("Customer #ID Does Not Exist"));

        customer1.setCustName(customer.getCustName());
        customer1.setCustAddress(customer.getCustAddress());
        customer1.setCustContactNumber(customer.getCustContactNumber());
        customer1.setCustDOB(customer.getCustDOB());
        customer1.setCustEmailId(customer.getCustEmailId());
        customer1.setCustPassword(customer.getCustPassword());

        customer1.setCustAccBalance(customer.getCustAccBalance());

        return ResponseEntity.ok(customerService.update(customer1));
    }

    @PatchMapping("/changeaddress/{custId}/{custAddress}")
    public ResponseEntity<Customer> changeAddress(@PathVariable int custId, @PathVariable String custAddress) {

        Customer customer = customerService.findById(custId).orElseThrow(() -> new RecordNotFoundException("Customer #ID Does Not Exist"));


        customer.setCustAddress(custAddress);

        return ResponseEntity.ok(customerService.update(customer));
    }

    @DeleteMapping("/deletebyid/{custId}")
    public ResponseEntity<String> deleteById(@PathVariable int custId) {
        customerService.deleteById(custId);
        return ResponseEntity.ok("Data Deleted Successfully");
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAll() {
        customerService.deleteAll();
        return ResponseEntity.ok("All Data Deleted Successfully");
    }

}
