package webuildit.myStartup.service;

import webuildit.myStartup.dto.CustomerDTO;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    CustomerDTO addCustomer(CustomerDTO customerDTO);
    CustomerDTO getCustomerByUUID(UUID cUuid);
    List<CustomerDTO> getAllCustomers();
    CustomerDTO updateCustomer(CustomerDTO customerDTO);
    void removeCustomerById(UUID cUuid);

}
