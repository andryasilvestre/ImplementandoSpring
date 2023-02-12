package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@ComponentScan
//@EnableAutoConfiguration
@SpringBootApplication
@RestController
@RequestMapping("api/v1/customer")
public class Main {
    //injeção repositório
    private static CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        Main.customerRepository = customerRepository;
    }

    record NewCustomerRequest(
            String name,
            String email,
            Integer age
    ) {
        public void addCustomer (@RequestBody NewCustomerRequest request) {
            Customer customer = new Customer();
            Customer.setName(request.name());
            Customer.setEmail(request.email());
            Customer.setAge(request.age());
            customerRepository.save(customer);
        }
    }

    //add edit

    @PutMapping("{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer id,
                               @RequestBody Customer customer) {
        customerRepository.save(customer);
    }


    @DeleteMapping("{customerID}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }
//
//    @GetMapping("/")
//    public String defaultValue() {
//        return "This is the main page...";
//    }
//    @GetMapping("/greet")
//    public GreetResponse greet() {
//        GreetResponse response = new GreetResponse(
//                "Hello",
//                List.of("Java", "Python", "C"),
//                new Person("Ândrya", 21, 2)
//        );
//        return response;
//
//        //redundante - pode dar return new response = new greetresponse()....
//    }
//
//    record Person(String name, int age, double savings) {
//
//    }
//    record GreetResponse(
//            String greet,
//            List<String> favProgrammingLanguages,
//            Person person
//    ) {
//
//    }
}

