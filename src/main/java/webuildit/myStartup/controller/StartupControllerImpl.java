package webuildit.myStartup.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webuildit.myStartup.dto.StartupDTO;
import webuildit.myStartup.service.StartupService;


@RestController
@RequestMapping("/api/v1/startup")
public class StartupControllerImpl implements StartupController{

    private StartupService startupService;
    private CustomerController customerController;

    // Konstruktor
    public StartupControllerImpl(StartupService startupService, CustomerController customerController){
        this.startupService = startupService;
        this.customerController = customerController;
    }

    // Methode mit Dokumentation, die alle gewünschten statistischen Werte für den gewählten Monat/Jahr zurückgibt
    @Override
    @Operation(summary = "Returns the statistics for the chosen month and year. These statistics include" +
            " the revenue the startup made that month, the three classifications with the highest and lowest revenue," +
            " all customers with five or more failed transactions and the revenue difference regarding the month before.",
            description = "This operation returns all statistics for the chosen month in year. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All statistics are returned successfully."),
            @ApiResponse(responseCode = "500", description = "An error occurred during processing.")
    })
    @GetMapping("/2/{month}/{year}")
    public StartupDTO getStatisticForOneMonth(@Parameter(description = "Month the Statistics are wanted for")
            @PathVariable("month") int month,
            @Parameter(description = "Year the Statistics are wanted for")
            @PathVariable("year") int year) {
        try {
           StartupDTO s1 = startupService.getStatistic(month, year);
            return s1;
        } catch (NullPointerException exception) {
            throw new NullPointerException();
        }
    }
}
