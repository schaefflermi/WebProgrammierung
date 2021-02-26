package webuildit.myStartup.controller;

import webuildit.myStartup.dto.CustomerDTO;

import java.util.List;
import java.util.UUID;

public interface CustomerController {

    CustomerDTO addCustomer(CustomerDTO customerDTO);
    CustomerDTO getCustomerByUUID(UUID cUuid);
    List<CustomerDTO> getAllCustomers();
    CustomerDTO updateCustomer(UUID cUuid, CustomerDTO customerDTO);
    void removeCustomerById(UUID cUuid);
    List<String> findAllCustomerWithFiveFailedTransaction(int month, int year);


}
