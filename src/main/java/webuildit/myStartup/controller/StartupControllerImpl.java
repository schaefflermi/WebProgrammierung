package webuildit.myStartup.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webuildit.myStartup.mapper.StartupMapper;
import webuildit.myStartup.service.StartupService;

import java.util.List;

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
    @GetMapping("/1.5/{month}/{year}")
    public String compareIncomeBeetweenOneMonth(@PathVariable("month") int month, @PathVariable("year") int year) {
        String tmp = String.valueOf(startupService.compareIncomeBeetweenOneMonth(month, year));
        return tmp;
    }

    @Override
    @GetMapping("/1.2/{month}/{year}")
    public String findSumOfAllTransactionsByDay(@PathVariable("month") int month, @PathVariable("year") int year) {
        String sum = String.valueOf(startupService.findSumOfAllTransactionsByDay(month, year));
        return sum;
    }

    @Override
    @GetMapping("/1.3/{month}/{year}")
    public List<String> findTop3Desc(@PathVariable("month") int month, @PathVariable("year") int year) {
        List<String> tmp = startupService.findTop3Desc(month, year);
        return tmp;
    }

    @Override
    @GetMapping("/1.3.1/{month}/{year}")
    public List<String> findAllTop3Asc(@PathVariable("month") int month, @PathVariable("year") int year) {
        List<String> tmp = startupService.findAllTop3Asc(month, year);
        return tmp;
    }

    @Override
    @GetMapping("/2/{month}/{year}")
    public String getStatisticForOneMonth(@PathVariable("month") int month, @PathVariable("year") int year) {
        try {


            String tmp = "Statistik für den eingegeben Monat \nSumme: " + this.findSumOfAllTransactionsByDay(month, year) + "\n"
                    + "Die 3 Gewerbe mit den höchsten Umsatz: " + this.findTop3Desc(month, year) + "\n"
                    + "Die 3 Gewerbe mit den niedrigsten Umsatz: " + this.findAllTop3Asc(month, year) + "\n"
                    + "Kunden mit 5 fehlgeschlagenen Transaktionen: " + customerController.findAllCustomerWithFiveFailedTransaction(month, year) + "\n"
                    + "Differenz zum Vormonat: " + this.compareIncomeBeetweenOneMonth(month, year);
            return tmp;
        } catch (NullPointerException exception) {
            throw new NullPointerException();

        }
    }

}
