package webuildit.myStartup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import webuildit.myStartup.controller.CCTController;
import webuildit.myStartup.controller.CCTControllerImpl;
import webuildit.myStartup.model.Classification;
import webuildit.myStartup.model.Creditcardtransaction;
import webuildit.myStartup.model.Customer;
import webuildit.myStartup.model.Vendor;
import webuildit.myStartup.repository.TransactionRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

@Component
@Slf4j
public class ManualTestBean {

    // Vorsicht diese Klasse ist nur für Übungszwecke,
    // sie sollte nie in einer produktiven Applikation sein

    CCTControllerImpl CCTControllerImpl;
    TransactionRepository transactionRepository;

    ManualTestBean(CCTControllerImpl CCTControllerImpl, TransactionRepository transactionRepository){
        this.CCTControllerImpl = CCTControllerImpl;
        this.transactionRepository=transactionRepository;

    }

    @EventListener(ApplicationReadyEvent.class)
    public void callController(){

        // erstelle Beispielskunden und Transaktionen
        Customer customer_1 = new Customer("Kunde1", "meineAdresse", 678834);
        Customer customer_2 = new Customer("Kunde1", "meineAdresse", 678834);
        Vendor v1 = new Vendor(1,"Vendor1", "someAddress", Classification.BERGBAU);
        Vendor v2 = new Vendor(2,"Ven2", "jklöafe", Classification.LANDFORSTWIRTSCHAFT);
        Creditcardtransaction c1 = new Creditcardtransaction("Descr", 189, true,  LocalDate.of(2014,11,6));
        Creditcardtransaction c2 = new Creditcardtransaction("whatever", 27, true,  LocalDate.of(2020,01,20));

        //Ordne den Transaktionen Kunden zu
        c1.setCustomer(customer_1);
        c1.setVendor(v1);
        c2.setVendor(v2);
        c2.setCustomer(customer_2);

        // speichere alle Transaktionen
        this.transactionRepository.saveAll(Arrays.asList(c1, c2));

        // Test: gib einen Namen aus
        String suuid = c1.getTUuid().toString();
        log.info("--------------------------------------------------");
        this.transactionRepository.findAllCreditcardtransactionBytUuid(c1.getTUuid());
        log.info("--------------------------------------------------");
        log.info("--------------------------------------------------");
     //   CCTControllerImpl.getTransaction(c1.getTUuid());
        log.info("--------------------------------------------------");
       // CCTControllerImpl.getTransactionFees(v1.getVUuid());


//        log.info(this.moneyController.getName());
//        log.info(this.moneyController.getServiceName());
//        log.info(this.artistService.getName());
    }

}
