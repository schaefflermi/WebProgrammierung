package webuildit.myStartup;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import webuildit.myStartup.controller.CCTController;
import webuildit.myStartup.controller.CCTControllerImpl;
import webuildit.myStartup.model.Classification;
import webuildit.myStartup.model.Creditcardtransaction;
import webuildit.myStartup.model.Customer;
import webuildit.myStartup.model.Vendor;
import webuildit.myStartup.repository.CustomerRepository;
import webuildit.myStartup.repository.TransactionRepository;
import webuildit.myStartup.repository.VendorRepository;
import webuildit.myStartup.service.VendorService;
import webuildit.myStartup.service.VendorServiceImpl;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
@Slf4j
public class ManualTestBean {

    // Vorsicht diese Klasse ist nur für Übungszwecke,
    // sie sollte nie in einer produktiven Applikation sein.

    CCTControllerImpl CCTControllerImpl;
    TransactionRepository transactionRepository;
    VendorRepository vendorRepository;
    VendorService vendorService;
    CustomerRepository customerRepository;


    ManualTestBean(CCTControllerImpl CCTControllerImpl, TransactionRepository transactionRepository, VendorRepository vendorRepository, VendorService vendorService, CustomerRepository customerRepository){
        this.CCTControllerImpl = CCTControllerImpl;
        this.transactionRepository = transactionRepository;
        this.vendorRepository = vendorRepository;
        this.vendorService = vendorService;
        this.customerRepository = customerRepository;


    }

    @EventListener(ApplicationReadyEvent.class)
    public void callController(){

        // erstelle Beispielskunden und Transaktionen
        Customer customer_1 = new Customer("Peter Müller", "Hagenbeckstr. 38 22572 Hamburg", 60000);
        Customer customer_2 = new Customer("Anja Beck", "Köpenicker Straße 47 10179 Berlin", 70000);
        Customer customer_3 = new Customer("Tony Smith", "Gustav-Landauer-Bogen 5 80797 München", 90000);
        Customer customer_4 = new Customer("Rafael Fogel", "Wiesenstraße 13 76684 Östringen", 60000);
        Customer customer_5 = new Customer ("Andreas Müller", "Merianstraße 7 69168 Wiesloch", 130000);
        Customer customer_6 = new Customer("Fail Ure", "Can't do it 5 34921 Nothappening", 0);
        Vendor v1 = new Vendor("Thyssen Schachtbau Holding GmbH", "Sandstraße 107/135, 45473 Mülheim an der Ruhr", Classification.BERGBAU);
        Vendor v2 = new Vendor("ECORA GmbH", "Am Blätterrangen 2 95659 Arzberg", Classification.LANDFORSTWIRTSCHAFT);
        Vendor v3 = new Vendor( "SAP SE", "Dietmar-Hopp-Allee 16 69190 Walldorf", Classification.DIENSTLEISTUNGEN);
        Vendor v4 = new Vendor( "ATLAS Informationssysteme GmbH", "Carl-Reichstein-Straße 7/9 14770 Brandenburg an der Havel", Classification.DIENSTLEISTUNGEN);
        Vendor v5 = new Vendor("IFB Ingenieure GmbH", "Mozartstraße 19/2, 73663 Berglen", Classification.BAUGEWERBE);
        Vendor v6 = new Vendor("GOLDBECK GmbH", "Rosengartenweg 3, 67281 Kirchheim an der Weinstraße", Classification.BAUGEWERBE);
        Creditcardtransaction c1 = new Creditcardtransaction("Peter Müller buys some cole", 189, true,  LocalDate.of(2021,02,6));
        Creditcardtransaction c2 = new Creditcardtransaction("Anja Beck buys from an agriculture company", 75, true,  LocalDate.of(2021,01,20));
        Creditcardtransaction c3 = new Creditcardtransaction("Tony Smith has a little company using SAP software and buys it in his own name", 15000, true, LocalDate.of(2021, 01, 22));
        Creditcardtransaction c4 = new Creditcardtransaction("Fogel buys some house related stuff", 1050, true, LocalDate.of(2021, 01, 02));
        Creditcardtransaction c5 = new Creditcardtransaction("A Müller buys a home", 80000,true, LocalDate.of(2020, 04, 18) );
        Creditcardtransaction c6 = new Creditcardtransaction("P Müller plans on building a home", 1500, true, LocalDate.of(2020, 03, 25));
        Creditcardtransaction c7 = new Creditcardtransaction("Beck builds a terrasse", 3000, true, LocalDate.of(2021, 02,01));
        Creditcardtransaction c8 = new Creditcardtransaction("Fogel build a garage", 1200, true, LocalDate.of(2020, 04, 15));
        Creditcardtransaction c9 = new Creditcardtransaction("Smith bilds a home", 37058, true, LocalDate.of(2021, 02,16 ));
        Creditcardtransaction c10 = new Creditcardtransaction("A Müller buys his sister a flat", 30000, true, LocalDate.of(2020, 03, 19));
        Creditcardtransaction c11 = new Creditcardtransaction("fail1", 50, false, LocalDate.of(2020, 04, 19));
        Creditcardtransaction c12 = new Creditcardtransaction("fail2", 1550, false, LocalDate.of(2021, 01, 24));
        Creditcardtransaction c13 = new Creditcardtransaction("fail2", 1550, false, LocalDate.of(2021, 01, 24));
        Creditcardtransaction c14 = new Creditcardtransaction("fail2", 1550, false, LocalDate.of(2021, 01, 24));
        Creditcardtransaction c15 = new Creditcardtransaction("fail2", 1550, false, LocalDate.of(2021, 01, 24));
        Creditcardtransaction c16 = new Creditcardtransaction("fail1", 50, false, LocalDate.of(2020, 04, 19));
        Creditcardtransaction c17 = new Creditcardtransaction("fail2", 1550, false, LocalDate.of(2021, 01, 24));
        Creditcardtransaction c18 = new Creditcardtransaction("fail2", 1550, false, LocalDate.of(2021, 02, 24));
        Creditcardtransaction c19 = new Creditcardtransaction("fail2", 1550, false, LocalDate.of(2021, 02, 24));
        Creditcardtransaction c20 = new Creditcardtransaction("fail2", 1550, false, LocalDate.of(2021, 01, 24));
        Creditcardtransaction c21 = new Creditcardtransaction("fail2", 1550, false, LocalDate.of(2021, 01, 24));
        Creditcardtransaction c22 = new Creditcardtransaction("Beck builds a terrasse", 3000, true, LocalDate.of(2021, 02,03));




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
        c11.setCustomer(customer_6);
        c12.setCustomer(customer_6);
        c13.setCustomer(customer_6);
        c14.setCustomer(customer_6);
        c15.setCustomer(customer_6);
        c16.setCustomer(customer_3);
        c17.setCustomer(customer_3);
        c18.setCustomer(customer_3);
        c19.setCustomer(customer_3);
        c20.setCustomer(customer_3);
        c21.setCustomer(customer_1);
        c22.setCustomer(customer_2);

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
        c11.setVendor(v6);
        c12.setVendor(v2);
        c13.setVendor(v4);
        c14.setVendor(v3);
        c15.setVendor(v6);
        c16.setVendor(v2);
        c17.setVendor(v2);
        c18.setVendor(v2);
        c19.setVendor(v2);
        c20.setVendor(v2);
        c21.setVendor(v1);
        c22.setVendor(v6);



        // speichere alle Transaktionen
        this.transactionRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15));
        // speichern wir irgendwo Kunden & Verkäufer?
        v1.setTransactions(Arrays.asList(c1, c12, c21));
        v2.setTransactions(Arrays.asList(c2,c16, c17, c18, c19, c20));
        v3.setTransactions(Arrays.asList(c3));
        v4.setTransactions(Arrays.asList(c4));
        v5.setTransactions(Arrays.asList(c5));
        v6.setTransactions(Arrays.asList(c6, c7, c8, c9, c10, c22));
        this.vendorRepository.saveAll(Arrays.asList(v1,v2,v3,v4,v5,v6));

        //Ordne den Kunden Transaktionen zu
        customer_1.setTransactions(Arrays.asList(c1, c6, c21));
        customer_2.setTransactions(Arrays.asList(c2, c7, c22));
        customer_3.setTransactions(Arrays.asList(c3, c9, c16, c17, c18, c19, c20));
        customer_4.setTransactions(Arrays.asList(c4, c8));
        customer_5.setTransactions(Arrays.asList(c5, c10));
        customer_6.setTransactions(Arrays.asList(c11, c12, c13, c14, c15));
        this.customerRepository.saveAll(Arrays.asList(customer_1, customer_2, customer_3, customer_4,customer_5, customer_6));

        // Gibt alle Werte der ersten Aufgabe zurück
        vendorService.getStatisticsAboutMonth(2,2021);
        log.info(String.valueOf(transactionRepository.findAllByVendorandCurrentMonth(v6.getVUuid())));
        log.info("-------------Aufgabe 1.1 -------------");
        log.info(vendorService.getFeeForVendor(v6.getVUuid()));



    }
}
