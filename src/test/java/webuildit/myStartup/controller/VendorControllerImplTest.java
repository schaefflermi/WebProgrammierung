package webuildit.myStartup.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import webuildit.myStartup.controller.VendorControllerImpl;
import webuildit.myStartup.dto.VendorDTO;
import webuildit.myStartup.model.Classification;
import webuildit.myStartup.model.Vendor;
import webuildit.myStartup.service.VendorService;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = VendorControllerImpl.class)
public class VendorControllerImplTest {

    @MockBean
    private VendorService vendorService;

    @MockBean
    private CustomerController customerController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // Test Entities
    private List<VendorDTO> vendorList;
    private VendorDTO vendor1;
    private VendorDTO vendor2;
    private VendorDTO vendor3;

    private final String controllerPath="/api/v1/vendors";

    @BeforeEach
    public void setUp(){
        vendor1 = new VendorDTO(UUID.fromString("6391b4d2-24d3-4991-840d-898c28169745"), "Thyssen Schachtbau Holding GmbH", "Sandstraße 107/135, 45473 Mülheim an der Ruhr", Classification.BERGBAU);
        vendor2 = new VendorDTO(UUID.fromString("d440b7ad-5e0d-4f17-9fac-aa506e863f09"), "ECORA GmbH", "Am Blätterrangen 2 95659 Arzberg", Classification.LANDFORSTWIRTSCHAFT);
        vendor3 = new VendorDTO(UUID.fromString("f624c5b1-6b40-468a-810f-f47600a8b1dd"), "SAP SE", "Dietmar-Hopp-Allee 16 69190 Walldorf", Classification.DIENSTLEISTUNGEN);
        this.vendorList = Arrays.asList(vendor1, vendor2, vendor3);
    }

    @Test
    public void shouldReturnAllVendors() throws Exception{
        when(vendorService.getAllVendors()).thenReturn(vendorList);

        this.mockMvc.perform(get(controllerPath))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(3)))
                .andExpect(jsonPath("$[0].vuuid", is(vendor1.getVUuid().toString())))
                .andExpect(jsonPath("$[0].vname", is(vendor1.getVName())))
                .andExpect(jsonPath("$[0].vaddress", is(vendor1.getVAddress())))
                .andExpect(jsonPath("$[0].classification", is(vendor1.getClassification().toString())))

                .andDo(print());
        verify(vendorService).getAllVendors();
    }


}
