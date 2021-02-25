package webuildit.myStartup.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import webuildit.myStartup.dto.CustomerDTO;
import webuildit.myStartup.service.CustomerService;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CustomerControllerImpl.class)
public class CustomerControllerImplTest {

    @MockBean
    private CustomerService customerService;


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // Test Entities
    private List<CustomerDTO> customerList;
    private CustomerDTO customer1;
    private CustomerDTO customer2;
    private CustomerDTO customer3;

    private final String controllerPath="/api/v1/customers";

    @BeforeEach
    public void setUp(){
        customer1 = new CustomerDTO(UUID.fromString("1939552c-3ee0-47b6-b635-7d467af47d61"), "Andreas Müller", "Merianstraße 7 69168 Wiesloch", 130000);
        customer2 = new CustomerDTO(UUID.fromString("0ffaaf57-6ac3-46ac-a3b3-5dd908a98c9b"), "Anja Beck", "Köpenicker Straße 47 10179 Berlin", 70000);
        customer3 = new CustomerDTO(UUID.fromString("5ffaac1b-f85c-42c7-ac28-0be62734bec4"), "Tony Smith", "Gustav-Landauer-Bogen 5 80797 München", 90000);
        this.customerList = Arrays.asList(customer1, customer2, customer3);
    }

    @Test
    public void shouldReturnAllCustomers() throws Exception{
        when(customerService.getAllCustomers()).thenReturn(customerList);

        this.mockMvc.perform(get(controllerPath))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(3)))
                .andExpect(jsonPath("$[0].cuuid", is(customer1.getCUuid().toString())))
                .andExpect(jsonPath("$[0].cname", is(customer1.getCName())))
                .andExpect(jsonPath("$[0].caddress", is(customer1.getCAddress())))
                .andExpect(jsonPath("$[0].netIncome", is(customer1.getNetIncome())))
                .andDo(print());
        verify(customerService).getAllCustomers();
    }


    @Test
    public void shouldReturnOneCustomer() throws Exception{
        when(customerService.getCustomerByUUID(any())).thenReturn(customer1);
        this.mockMvc.perform(get(controllerPath+"/"+customer1.getCUuid()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cuuid", is(customer1.getCUuid().toString())))
                .andExpect(jsonPath("$.cname", is(customer1.getCName())))
                .andExpect(jsonPath("$.caddress", is(customer1.getCAddress())))
                .andExpect(jsonPath("$.netIncome", is(customer1.getNetIncome())))
                .andDo(print());
        verify(customerService).getCustomerByUUID(any());
    }

    @Test
    public void shouldAddCustomer() throws Exception{
        when(customerService.addCustomer(any())).thenReturn((customer2));
        this.mockMvc.perform(post(controllerPath)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(customer2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cuuid", is(customer2.getCUuid().toString())));
        verify(customerService).addCustomer(any());
    }

    @Test
    public void shouldUpdateCustomer() throws Exception{
        when(customerService.updateCustomer(any())).thenReturn(customer3);
        this.mockMvc.perform(put(controllerPath+"/"+customer3.getCUuid())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(customer3)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cuuid", is(customer3.getCUuid().toString())));
        verify(customerService).updateCustomer(any());
    }

    @Test
    public void shouldDeleteCustomer() throws Exception{
        mockMvc.perform(delete(controllerPath+"/"+customer2.getCUuid()))
                .andExpect(status().isOk());
        verify(customerService).removeCustomerById(any());
    }


}
