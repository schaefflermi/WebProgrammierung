package webuildit.myStartup.controller;

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

    public StartupControllerImpl(StartupService startupService, CustomerController customerController){
        this.startupService = startupService;
        this.customerController = customerController;
    }

    @Override
    @GetMapping("/2/{month}/{year}")
    public StartupDTO getStatisticForOneMonth(@PathVariable("month") int month, @PathVariable("year") int year) {
        try {
           StartupDTO s1 = startupService.getStatistic(month, year);
            return s1;
        } catch (NullPointerException exception) {
            throw new NullPointerException();

        }
    }

}
