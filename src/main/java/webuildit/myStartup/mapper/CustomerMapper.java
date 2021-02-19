package webuildit.myStartup.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import webuildit.myStartup.dto.CustomerDTO;
import webuildit.myStartup.model.Customer;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO customerToCustomerDTO(Customer customer);

    List<CustomerDTO> customersToCustomersDTO(List<Customer> customers);

    @InheritInverseConfiguration
    Customer customerDTOToCustomer(CustomerDTO customerDTO);

    List<Customer> customerDTOsToCustomers(List<CustomerDTO> customerDTOToList);

}
