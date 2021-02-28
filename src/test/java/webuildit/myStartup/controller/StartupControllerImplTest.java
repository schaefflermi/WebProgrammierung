package webuildit.myStartup.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import webuildit.myStartup.dto.StartupDTO;
import webuildit.myStartup.service.StartupService;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StartupControllerImpl.class)
public class StartupControllerImplTest {


    @MockBean
    private StartupService startupService;

    @MockBean
    private CustomerController customerController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // Test Entities
    private StartupDTO startup;
    private int month;
    private int year;
    private double result;

    private final String controllerPath="/api/v1/startup";

    @BeforeEach
    public void setUp(){
        startup = new StartupDTO(UUID.fromString("6de14a89-3f3e-4ed0-9b80-3ee3b822a449"), 50000, "ClassUp", "ClassDown", "Customers", 12.0 );
        month = 01;
        year = 2021;
        result = 19.04;
    }

    @Test
    public void shouldReturnStatisticsForOneMonth() throws Exception{
        when(startupService.getStatistic(month, year)).thenReturn(startup);
          this.mockMvc.perform(get(controllerPath+"/2/"+month+"/"+year))
                  .andExpect(status().isOk())
                  .andExpect(jsonPath("$.revenue", is(startup.getRevenue())))
                  .andExpect(jsonPath("$.classificationsDown", is(startup.getClassificationsDown())))
                  .andExpect(jsonPath("$.classificationsUp", is(startup.getClassificationsUp())))
                  .andExpect(jsonPath("$.customers", is(startup.getCustomers())))
                  .andExpect(jsonPath("$.difference", is(startup.getDifference())))
                  .andExpect(jsonPath("$.suuid", is(startup.getSUuid().toString())))
                  .andDo(print());
          verify(startupService).getStatistic(month, year);
    }
}
