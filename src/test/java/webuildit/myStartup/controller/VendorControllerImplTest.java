package webuildit.myStartup.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import webuildit.myStartup.dto.VendorDTO;
import webuildit.myStartup.model.Classification;
import webuildit.myStartup.service.VendorService;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Test
    public void shouldReturnOneVendor() throws Exception{
        when(vendorService.getVendorByUUID(any())).thenReturn(vendor1);
        this.mockMvc.perform(get(controllerPath+"/"+vendor1.getVUuid()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vuuid", is(vendor1.getVUuid().toString())))
                .andExpect(jsonPath("$.vname", is(vendor1.getVName())))
                .andExpect(jsonPath("$.vaddress", is(vendor1.getVAddress())))
                .andExpect(jsonPath("$.classification", is(vendor1.getClassification().toString())))
                .andDo(print());
        verify(vendorService).getVendorByUUID(any());
    }

    @Test
    public void shouldAddVendor() throws Exception{
        when(vendorService.addVendor(any())).thenReturn((vendor2));
        this.mockMvc.perform(post(controllerPath)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(vendor2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vuuid", is(vendor2.getVUuid().toString())));
        verify(vendorService).addVendor(any());
    }

    @Test
    public void shouldUpdateVendor() throws Exception{
        when(vendorService.updateVendor(any())).thenReturn(vendor3);
        this.mockMvc.perform(put(controllerPath+"/"+vendor3.getVUuid())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(vendor3)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vuuid", is(vendor3.getVUuid().toString())));
        verify(vendorService).updateVendor(any());
    }

    @Test
    public void shouldDeleteVendor() throws Exception{
            mockMvc.perform(delete(controllerPath+"/"+vendor2.getVUuid()))
                    .andExpect(status().isOk());
        verify(vendorService).removeVendorById(any());
    }



}
