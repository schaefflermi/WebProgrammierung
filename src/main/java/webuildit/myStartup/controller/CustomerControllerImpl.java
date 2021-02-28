package webuildit.myStartup.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    // Konstruktor
    public CustomerControllerImpl(CustomerService customerService){
        this.customerService = customerService;
    }

    // Methode mit Dokumentation, die alle Kunden zurückgibt
    @Override
    @Operation(summary = "Returns a list of all customers who are saved in the database.", description = "This " +
            "operation returns all customers who are saved in the database in form of CustomerDTO. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All customers are returned successfully."),
            @ApiResponse(responseCode = "500", description = "An error occurred during processing.")
    })
    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return this.customerService.getAllCustomers();
    }


    // Methode mit Dokumentation, die die Details für einen Kunden zurückgibt
    @Override
    @Operation(summary = "Returns all details for a customer.", description = "This operation returns all details" +
            "for the selected customerUUID in form of the CustomerDTO.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The customer details are returned successfully"),
            @ApiResponse(responseCode = "500", description = "An error occurred during processing")
    })
    @GetMapping("/{cuuid}")
    public CustomerDTO getCustomerByUUID(@Parameter(description = "The unique customerUUID")
            @PathVariable("cuuid") UUID cUuid) {
        try{
            return this.customerService.getCustomerByUUID(cUuid);
        } catch(NoSuchElementException noSuchElementException){
            throw new NoSuchElementException("The customer with this UUID does not exist.");
        }
    }


    // Methode mit Dokumentation, die einen Kunden hinzufügt
    @Override
    @Operation(summary = "Adds a new customer to the database.", description = "This operation " +
            "creates and persists the given customer in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The customer has been successfully added."),
            @ApiResponse(responseCode = "400", description = "The passed customer details are not valid"),
            @ApiResponse(responseCode = "500", description = "An error occurred during processing")
    })
    @PostMapping
    public CustomerDTO addCustomer(@Parameter(description = "The new customerDTO which will be stored.")
            @Valid @RequestBody CustomerDTO customerDTO) {
        try {
            return this.customerService.addCustomer(customerDTO);
        } catch (ConstraintViolationException constraintViolationException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    // Methode mit Dokumentation, die die Daten eines Kunden ändert
    @Override
    @Operation(summary = "Updates a customer in the database with the given input.", description = "This operation " +
            "updates a given customer in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The customer has been successfully changed."),
            @ApiResponse(responseCode = "400", description = "The passed customer details are not valid"),
            @ApiResponse(responseCode = "500", description = "An error occurred during processing")
    })
    @PutMapping("/{cuuid}")
    public CustomerDTO updateCustomer(
            @Parameter(description = "The unique customerUUID")
            @PathVariable("cuuid") UUID cUuid,
            @Parameter(description = "The updated customerDTO")
            @Valid @RequestBody CustomerDTO customerDTO) {
        customerDTO.setCUuid(cUuid);
        try {
            return this.customerService.updateCustomer(customerDTO);
        } catch (EntityNotFoundException notFoundException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (NoSuchElementException noSuchElementException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    // Methode mit Dokumentation, die einen Kunden aus der Datenbank löscht
    @Override
    @Operation(summary = "Removes the referenced customer from the database.", description = "This operation " +
            "removes the customer which is referenced in form of the passed customerUUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The customer is successfully removed"),
            @ApiResponse(responseCode = "404", description = "The given customerUUID does not exist in the database"),
            @ApiResponse(responseCode = "500", description = "An error occurred during processing")
    })
    @DeleteMapping("/{cuuid}")
    public void removeCustomerById(@Parameter(description = "The unique customerUUID")
            @PathVariable("cuuid") UUID cUuid) {
        try {
            this.customerService.removeCustomerById(cUuid);
        } catch (EntityNotFoundException notFoundException){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    // gibt alle Kunden mit 5 fehlgeschlagenen Transaktionen zurück
    @Override
    @Operation(summary = "Returns all customers with five or more failed transactions.", description = "This operation returns all names " +
            "of customers with five or more failed transactions as a String.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The customer names are returned successfully"),
         //   @ApiResponse(responseCode = "404", description = "The given parameters do not match the validation criteria"),
            @ApiResponse(responseCode = "500", description = "An error occurred during processing")
    })
    @GetMapping("/1.4/{month}/{year}")
    public List<String> findAllCustomerWithFiveFailedTransaction(
            @Parameter(description = "Month:")
            @PathVariable ("month") int month,
            @Parameter(description = "Year:")
            @PathVariable("year") int year) {
        // brauchen wir hier ein Try-Catch?? Wenn ja, welche Exception wollen wir abfangen?
            List<String> tmp = customerService.findAllCustomerWithFiveFailedTransaction(month, year);
            return tmp;
    }
}
