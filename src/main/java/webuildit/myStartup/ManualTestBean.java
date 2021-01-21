package webuildit.myStartup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import webuildit.myStartup.controller.MoneyController;
import webuildit.myStartup.model.Classification;
import webuildit.myStartup.model.CreditCardTransaction;
import webuildit.myStartup.model.Customer;
import webuildit.myStartup.model.Vendor;
import webuildit.myStartup.repository.TransactionRepository;

import java.time.LocalDate;
import java.util.Arrays;

@Component
@Slf4j
public class ManualTestBean {

    // Vorsicht diese Klasse ist nur für Übungszwecke,
    // sie sollte nie in einer produktiven Applikation sein

    MoneyController moneyController;
    TransactionRepository transactionRepository;

    ManualTestBean(MoneyController moneyController, TransactionRepository transactionRepository){
        this.moneyController = moneyController;
        this.transactionRepository=transactionRepository;

    }

    @EventListener(ApplicationReadyEvent.class)
    public void callController(){

        // erstelle Beispielskunden und Transaktionen
        Customer customer_1 = new Customer("Kunde1", "meineAdresse", 678834);
        Vendor v1 = new Vendor("Vendor1", "someAddress", Classification.BERGBAU);
        Vendor v2 = new Vendor("Ven2", "jklöafe", Classification.LANDFORSTWIRTSCHAFT);
        CreditCardTransaction c1 = new CreditCardTransaction("Descr", 189, true,  LocalDate.of(2014,11,6));
        CreditCardTransaction c2 = new CreditCardTransaction("whatever", 27, true,  LocalDate.of(2020,01,20));

        //Ordne den Transaktionen Kunden zu
        c1.setCustomer(customer_1);
        c1.setVendor(v1);

        // speichere alle Transaktionen
        this.transactionRepository.saveAll(Arrays.asList(c1, c2));

        // Test: gib einen Namen aus
        log.info(customer_1.getCName());

//        log.info(this.moneyController.getName());
//        log.info(this.moneyController.getServiceName());
//        log.info(this.artistService.getName());
    }

}
