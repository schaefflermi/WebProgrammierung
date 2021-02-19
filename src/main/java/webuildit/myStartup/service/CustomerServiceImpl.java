package webuildit.myStartup.service;

import org.springframework.stereotype.Service;
import webuildit.myStartup.dto.CreditcardtransactionDTO;
import webuildit.myStartup.dto.CustomerDTO;
import webuildit.myStartup.mapper.CustomerMapper;
import webuildit.myStartup.model.Creditcardtransaction;
import webuildit.myStartup.model.Customer;
import webuildit.myStartup.repository.CustomerRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class CustomerServiceImpl implements CustomerService{

    CustomerRepository customerRepository;
    CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper){
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        Customer tmp = new Customer(customerDTO.getCName(), customerDTO.getCAddress(), customerDTO.getNetIncome());
        this.customerRepository.save(tmp);
        return new CustomerDTO(tmp.getCUuid(), tmp.getCName(), tmp.getCAddress(), tmp.getNetIncome());
    }

    @Override
    public CustomerDTO getCustomerByUUID(UUID cUuid) {
        Customer tmp = this.customerRepository.findById(cUuid).get();
        return new CustomerDTO(tmp.getCUuid(), tmp.getCName(), tmp.getCAddress(), tmp.getNetIncome());
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> tmp = this.customerRepository.findAll();
        List<CustomerDTO> result = new ArrayList<>();
        for(Customer customer:tmp){
            result.add(new CustomerDTO(customer.getCUuid(), customer.getCName(), customer.getCAddress(), customer.getNetIncome()));
        }
        return result;
    }

    @Override
    @Transactional
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        Customer tmp = this.customerMapper.customerDTOToCustomer(customerDTO);
        this.customerRepository.save(tmp);
        return this.customerMapper.customerToCustomerDTO(tmp);
    }

    @Override
    public void removeCustomerById(UUID cUuid) {

        this.customerRepository.deleteById(cUuid);

    }

}
