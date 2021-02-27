package webuildit.myStartup.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import webuildit.myStartup.dto.CustomerDTO;
import webuildit.myStartup.service.CustomerService;


import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerControllerImpl implements CustomerController{

    CustomerService customerService;

    public CustomerControllerImpl(CustomerService customerService){
        this.customerService = customerService;
    }

    @Override
    @PostMapping
    public CustomerDTO addCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        try {
            return this.customerService.addCustomer(customerDTO);
        } catch (ConstraintViolationException constraintViolationException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @GetMapping("/{cuuid}")
    public CustomerDTO getCustomerByUUID(@PathVariable("cuuid") UUID cUuid) {
        return this.customerService.getCustomerByUUID(cUuid);
    }

    @Override
    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return this.customerService.getAllCustomers();
    }

    @Override
    @PutMapping("/{cuuid}")
    public CustomerDTO updateCustomer(@PathVariable("cuuid") UUID cUuid, @Valid @RequestBody CustomerDTO customerDTO) {
        customerDTO.setCUuid(cUuid);
        try {
            return this.customerService.updateCustomer(customerDTO);
        } catch (EntityNotFoundException notFoundException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (NoSuchElementException noSuchElementException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @DeleteMapping("/{cuuid}")
    public void removeCustomerById(@PathVariable("cuuid") UUID cUuid) {
        try {
            this.customerService.removeCustomerById(cUuid);
        } catch (EntityNotFoundException notFoundException){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @GetMapping("/1.4/{month}/{year}")
    public List<String> findAllCustomerWithFiveFailedTransaction(@PathVariable ("month") int month, @PathVariable("year") int year) {
            List<String> tmp = customerService.findAllCustomerWithFiveFailedTransaction(month, year);
            return tmp;
    }

}
