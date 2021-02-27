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
    public String getStatisticForOneMonth(@PathVariable("month") int month, @PathVariable("year") int year) {
        try {
            StartupDTO s1 = startupService.getStatistic(month, year);
            String tmp;
            tmp = "Statistik für den eingegeben Monat \n Summe: " + s1.getRevenue() + "\n"
                    + "Die 3 Gewerbe mit den höchsten Umsatz: " + s1.getClassificationsDown() + "\n"
                    + "Die 3 Gewerbe mit den niedrigsten Umsatz: " + s1.getClassificationsUp() + "\n"
                    + "Kunden mit 5 fehlgeschlagenen Transaktionen: " + s1.getCustomers() + "\n"
                    + "Differenz zum Vormonat: " + s1.getDifference();
            return tmp;
        } catch (NullPointerException exception) {
            throw new NullPointerException();

        }
    }

}
