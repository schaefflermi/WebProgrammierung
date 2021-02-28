package webuildit.myStartup.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import webuildit.myStartup.dto.CreditcardtransactionDTO;
import webuildit.myStartup.service.CreditcardtransactionService;

import java.time.LocalDate;
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

@WebMvcTest(controllers = CreditcardtransactionControllerImpl.class)
public class CreditcardtransactionControllerImplTest {

    @MockBean
    private CreditcardtransactionService creditcardtransactionService;


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // Test Entities
    private List<CreditcardtransactionDTO> creditcardtransactionList;
    private CreditcardtransactionDTO cct1;
    private CreditcardtransactionDTO cct2;
    private CreditcardtransactionDTO cct3;

    private final String controllerPath="/api/v1/ccts";

    @BeforeEach
    public void setUp(){
        cct1 = new CreditcardtransactionDTO(UUID.fromString("6cfcd405-8e40-45ac-b9b9-4300f8e22f86"), "Anja Beck buys from an agriculture company", 75, true,  LocalDate.of(2021,01,20));
        cct2 = new CreditcardtransactionDTO(UUID.fromString("0184a84c-d1e1-4317-8fef-7c63ed7ce3e1"), "A Müller buys a home", 80000,true, LocalDate.of(2020, 04, 18) );
        cct3 = new CreditcardtransactionDTO(UUID.fromString("b5bda572-6c8d-4e54-b891-69eb23468d28"), "fail1", 50, false, LocalDate.of(2020, 04, 19));
        this.creditcardtransactionList = Arrays.asList(cct1, cct2, cct3);
    }

    @Test
    public void shouldReturnAllCreditCardTransactions() throws Exception{
        when(creditcardtransactionService.getAllCreditcardtransaction()).thenReturn(creditcardtransactionList);

        this.mockMvc.perform(get(controllerPath))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(3)))
                .andExpect(jsonPath("$[0].tuuid", is(cct1.getTUuid().toString())))
                .andExpect(jsonPath("$[0].description", is(cct1.getDescription())))
                .andExpect(jsonPath("$[0].sum", is(cct1.getSum())))
                .andExpect(jsonPath("$[0].status", is(cct1.isStatus())))
                .andExpect(jsonPath("$[0].tdate", is(cct1.getTdate().toString())))
                .andDo(print());
        verify(creditcardtransactionService).getAllCreditcardtransaction();
    }


    @Test
    public void shouldReturnOneCreditcardtransaction() throws Exception{
        when(creditcardtransactionService.getCreditcardtransactionByUUID(any())).thenReturn(cct2);
        this.mockMvc.perform(get(controllerPath+"/"+cct2.getTUuid()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tuuid", is(cct2.getTUuid().toString())))
                .andExpect(jsonPath("$.description", is(cct2.getDescription())))
                .andExpect(jsonPath("$.sum", is(cct2.getSum())))
                .andExpect(jsonPath("$.status", is(cct2.isStatus())))
                .andExpect(jsonPath("$.tdate", is(cct2.getTdate().toString())))
                .andDo(print());
        verify(creditcardtransactionService).getCreditcardtransactionByUUID(any());
    }

    @Test
    public void shouldAddCreditcardtransaction() throws Exception{
        when(creditcardtransactionService.addCreditcardtransaction(any())).thenReturn((cct2));
        this.mockMvc.perform(post(controllerPath)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(cct2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tuuid", is(cct2.getTUuid().toString())));
        verify(creditcardtransactionService).addCreditcardtransaction(any());
    }

    @Test
    public void shouldUpdateCreditcardtransaction() throws Exception{
        when(creditcardtransactionService.updateCreditcardtransaction(any())).thenReturn(cct3);
        this.mockMvc.perform(put(controllerPath+"/"+cct3.getTUuid())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(cct3)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tuuid", is(cct3.getTUuid().toString())));
        verify(creditcardtransactionService).updateCreditcardtransaction(any());
    }

    @Test
    public void shouldDeleteCreditcardtransaction() throws Exception{
        mockMvc.perform(delete(controllerPath+"/"+cct2.getTUuid()))
                .andExpect(status().isOk());
        verify(creditcardtransactionService).removeCreditcardtransactionById(any());
    }
}
