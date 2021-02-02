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
import webuildit.myStartup.repository.VendorRepository;
import webuildit.myStartup.service.VendorService;
import webuildit.myStartup.service.VendorServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class ManualTestBean {

    // Vorsicht diese Klasse ist nur für Übungszwecke,
    // sie sollte nie in einer produktiven Applikation sein

    CCTControllerImpl CCTControllerImpl;
    TransactionRepository transactionRepository;
    VendorRepository vendorRepository;
    VendorService vendorService;


    ManualTestBean(CCTControllerImpl CCTControllerImpl, TransactionRepository transactionRepository, VendorRepository vendorRepository, VendorService vendorService){
        this.CCTControllerImpl = CCTControllerImpl;
        this.transactionRepository=transactionRepository;
        this.vendorRepository=vendorRepository;
        this.vendorService=vendorService;


    }

    @EventListener(ApplicationReadyEvent.class)
    public void callController(){

        // erstelle Beispielskunden und Transaktionen
        Customer customer_1 = new Customer("Peter Müller", "Hagenbeckstr. 38 22572 Hamburg", 60000);
        Customer customer_2 = new Customer("Anja Beck", "Köpenicker Straße 47 10179 Berlin", 70000);
        Customer customer_3 = new Customer("Tony Smith", "Gustav-Landauer-Bogen 5 80797 München", 90000);
        Customer customer_4 = new Customer("Rafael Fogel", "Wiesenstraße 13 76684 Östringen", 60000);
        Customer customer_5 = new Customer ("Andreas Müller", "Merianstraße 7 69168 Wiesloch", 130000);
        Vendor v1 = new Vendor(1,"Thyssen Schachtbau Holding GmbH", "Sandstraße 107/135, 45473 Mülheim an der Ruhr", Classification.BERGBAU);
        Vendor v2 = new Vendor(2,"ECORA GmbH", "Am Blätterrangen 2 95659 Arzberg", Classification.LANDFORSTWIRTSCHAFT);
        Vendor v3 = new Vendor( 3, "SAP SE", "Dietmar-Hopp-Allee 16 69190 Walldorf", Classification.DIENSTLEISTUNGEN);
        Vendor v4 = new Vendor( 4, "ATLAS Informationssysteme GmbH", "Carl-Reichstein-Straße 7/9 14770 Brandenburg an der Havel", Classification.DIENSTLEISTUNGEN);
        Vendor v5 = new Vendor(5, "IFB Ingenieure GmbH", "Mozartstraße 19/2, 73663 Berglen", Classification.BAUGEWERBE);
        Vendor v6 = new Vendor(6, "GOLDBECK GmbH", "Rosengartenweg 3, 67281 Kirchheim an der Weinstraße", Classification.BAUGEWERBE);
        Creditcardtransaction c1 = new Creditcardtransaction("Peter Müller buys some cole", 189, true,  LocalDate.of(2020,11,6));
        Creditcardtransaction c2 = new Creditcardtransaction("Anja Beck buys from an agriculture company", 75, true,  LocalDate.of(2020,01,20));
        Creditcardtransaction c3 = new Creditcardtransaction("Tony Smith has a little company using SAP software and buys it in his own name", 15000, true, LocalDate.of(2021, 01, 22));
        Creditcardtransaction c4 = new Creditcardtransaction("Fogel buys some house related stuff", 1050, true, LocalDate.of(2019, 06, 17));
        Creditcardtransaction c5 = new Creditcardtransaction("A Müller buys a home", 80000,true, LocalDate.of(2020, 03, 18) );
        Creditcardtransaction c6 = new Creditcardtransaction("P Müller plans on building a home", 1500, true, LocalDate.of(2020, 03, 25));
        Creditcardtransaction c7 = new Creditcardtransaction("Beck builds a terrasse", 3000, true, LocalDate.of(2019, 03,27));
        Creditcardtransaction c8 = new Creditcardtransaction("Fogel build a garage", 1200, true, LocalDate.of(2020, 04, 03));
        Creditcardtransaction c9 = new Creditcardtransaction("Smith bilds a home", 37058, true, LocalDate.of(2020, 03,15 ));
        Creditcardtransaction c10 = new Creditcardtransaction("A Müller buys his sister a flat", 30000, true, LocalDate.of(2020, 03, 19));


// do Fehlermeldung, wenn sum > netIncome -> kunde muss zahlen können!!!

        //Ordne den Transaktionen Kunden zu
        c1.setCustomer(customer_1);
        c2.setCustomer(customer_2);
        c3.setCustomer(customer_3);
        c4.setCustomer(customer_4);
        c5.setCustomer(customer_5);
        c6.setCustomer(customer_1);
        c7.setCustomer(customer_2);
        c8.setCustomer(customer_4);
        c9.setCustomer(customer_3);
        c10.setCustomer(customer_5);
        //Ordne den Transaktionen Verkäufer zu
        c1.setVendor(v1);
        c2.setVendor(v2);
        c3.setVendor(v3);
        c4.setVendor(v4);
        c5.setVendor(v5);
        c6.setVendor(v6);
        c7.setVendor(v6);
        c8.setVendor(v6);
        c9.setVendor(v6);
        c10.setVendor(v6);


        // speichere alle Transaktionen
        this.transactionRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10));
        List<Creditcardtransaction> cct = new ArrayList<>();
        cct.add(c1);
        cct.add(c2);
        // speichern wir irgendwo Kunden & Verkäufer?
        v1.setTransactions(Arrays.asList(c1));
        v2.setTransactions(Arrays.asList(c2));
        v3.setTransactions(Arrays.asList(c3));
        v4.setTransactions(Arrays.asList(c4));
        v5.setTransactions(Arrays.asList(c5));
        v6.setTransactions(Arrays.asList(c6, c7, c8, c9, c10));
        this.vendorRepository.saveAll(Arrays.asList(v1,v2,v3,v4,v5,v6));

        // Test: gib einen Namen aus
       /* log.info("--------------------------------------------------1");
        log.info(this.transactionRepository.findAll().get(1).getDescription());*/
        /*log.info("--------------------------------------------------1");
       // this.transactionRepository.findAll().get(1).getDescription();
        log.info(String.valueOf(cct.get(0).getSum()));
        log.info("--------------------------------------------------1");
        log.info(cct.get(0).getDescription());*/
       /* log.info("Summe wird ausgegeben");
        log.info(String.valueOf(transactionRepository.findAll().get(0).getSum()));
        log.info("Summe wurde ausgegeben");
        log.info("Summe wird wiederholt ausgegeben");*/
        //log.info(String.valueOf(transactionRepository.findAll().get(0).getSum()));
       /* log.info("Summe wurde wiederholt ausgegeben");
        log.info(CCTControllerImpl.getTransactionFee());
        log.info("--------------------------------------------------1");
        log.info(String.valueOf(cct.get(0).getSum()));
        log.info("--------------------------------------------------1");*/
        log.info(String.valueOf(vendorService.getTransactionFee()));
        //vendorService.getTransactionFee();
     //   CCTControllerImpl.getTransaction(c1.getTUuid());
       // log.info(String.valueOf(transactionRepository.findBytUuid(c1.getTUuid())));
        log.info("find by v uuid:");
        //log.info(String.valueOf(transactionRepository.findByVendorUuid(v1.getVUuid())));
        // log.info(String.valueOf(c1.getTUuid()));

       // CCTControllerImpl.getTransactionFees(v1.getVUuid());


//        log.info(this.moneyController.getName());
//        log.info(this.moneyController.getServiceName());
//        log.info(this.artistService.getName());

        log.info("------ alle ... in einer KLassifikation");
       // log.info(String.valueOf(this.vendorService.findByClassification(Classification.BAUGEWERBE)));
        log.info(String.valueOf(this.vendorService.getIncomeForClassification(Classification.BAUGEWERBE)));



     //   log.info("--------- Get fees for startup - funktioniert!!!: -----------");
       // log.info(String.valueOf(vendorService.getTransactionFeeForStartup(LocalDate.of(2020, 03, 01), LocalDate.of(2020, 03, 31))));
    }
}
