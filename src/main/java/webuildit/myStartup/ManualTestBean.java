package webuildit.myStartup;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import webuildit.myStartup.controller.CreditcardtransactionControllerImpl;
import webuildit.myStartup.model.Classification;
import webuildit.myStartup.model.Creditcardtransaction;
import webuildit.myStartup.model.Customer;
import webuildit.myStartup.model.Vendor;
import webuildit.myStartup.repository.CustomerRepository;
import webuildit.myStartup.repository.CreditcardtransactionRepository;
import webuildit.myStartup.repository.VendorRepository;
import webuildit.myStartup.service.StartupService;
import webuildit.myStartup.service.VendorService;

import java.time.LocalDate;
import java.util.*;

@Component
@Slf4j
public class ManualTestBean {

    // Vorsicht diese Klasse ist nur für Übungszwecke,
    // sie sollte nie in einer produktiven Applikation sein.

    CreditcardtransactionControllerImpl CCTControllerImpl;
    CreditcardtransactionRepository transactionRepository;
    VendorRepository vendorRepository;
    VendorService vendorService;
    CustomerRepository customerRepository;
    StartupService startupService;


    ManualTestBean(CreditcardtransactionControllerImpl CCTControllerImpl, CreditcardtransactionRepository transactionRepository, VendorRepository vendorRepository, VendorService vendorService, CustomerRepository customerRepository, StartupService startupService){
        this.CCTControllerImpl = CCTControllerImpl;
        this.transactionRepository = transactionRepository;
        this.vendorRepository = vendorRepository;
        this.vendorService = vendorService;
        this.customerRepository = customerRepository;
        this.startupService = startupService;


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
        Vendor v6 = new Vendor("GOLDBECK GmbH", "Rosengartenweg 3, 67281 Kirchheim an der Weinstraße", Classification.ENERGIEVERSORGUNG);
        Vendor v7 = new Vendor("Thyssen Schachtbau Holding GmbH", "Sandstraße 107/135, 45473 Mülheim an der Ruhr", Classification.GESUNDHEITSSOZIALWESEN);

        Creditcardtransaction c1= new Creditcardtransaction("Peter Müller buys some cole", 189, true,  LocalDate.of(2020,01,6));
        Creditcardtransaction c2= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,01,6));
        Creditcardtransaction c3= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,01,6));
        Creditcardtransaction c4= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,01,6));
        Creditcardtransaction c5= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,01,6));
        Creditcardtransaction c6= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,01,6));
        Creditcardtransaction c7= new Creditcardtransaction("Anja Beck buys from an agriculture company", 5000, true,  LocalDate.of(2020,01,12));
        Creditcardtransaction c8= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,01,6));
        Creditcardtransaction c9= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,01,6));
        Creditcardtransaction c10= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,01,6));
        Creditcardtransaction c11= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,01,6));
        Creditcardtransaction c12= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,01,6));
        Creditcardtransaction c13 = new Creditcardtransaction("Tony Smith has a little company using SAP software and buys it in his own name", 345, true,  LocalDate.of(2020,02,12));
        Creditcardtransaction c14= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,02,6));
        Creditcardtransaction c15= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,02,6));
        Creditcardtransaction c16= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,02,6));
        Creditcardtransaction c17= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,02,6));
        Creditcardtransaction c18= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,02,6));
        Creditcardtransaction c19 = new Creditcardtransaction("Fogel buys some house related stuff", 698, true,  LocalDate.of(2020,02,13));
        Creditcardtransaction c20= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,02,6));
        Creditcardtransaction c21= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,02,6));
        Creditcardtransaction c22= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,02,6));
        Creditcardtransaction c23= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,02,6));
        Creditcardtransaction c24= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,02,6));
        Creditcardtransaction c25 = new Creditcardtransaction("A Müller buys a home", 123, true,  LocalDate.of(2020,04,13));
        Creditcardtransaction c26= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,04,6));
        Creditcardtransaction c27= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,04,6));
        Creditcardtransaction c28= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,04,6));
        Creditcardtransaction c29= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,04,6));
        Creditcardtransaction c30= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,04,6));
        Creditcardtransaction c31= new Creditcardtransaction("P Müller plans on building a home", 9000, true,  LocalDate.of(2020,04,4));
        Creditcardtransaction c32= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,04,6));
        Creditcardtransaction c33= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,04,6));
        Creditcardtransaction c34= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,04,6));
        Creditcardtransaction c35= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,04,6));
        Creditcardtransaction c36= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,04,6));
        Creditcardtransaction c37 = new Creditcardtransaction("Beck builds a terrasse", 1569, true,  LocalDate.of(2020,08,9));
        Creditcardtransaction c38= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,08,6));
        Creditcardtransaction c39= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,08,6));
        Creditcardtransaction c40= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,08,6));
        Creditcardtransaction c41= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,08,6));
        Creditcardtransaction c42= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,08,6));
        Creditcardtransaction c43= new Creditcardtransaction("Maier builds a pool", 1364, true,  LocalDate.of(2020,08,3));
        Creditcardtransaction c44= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,08,6));
        Creditcardtransaction c45= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,08,6));
        Creditcardtransaction c46= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,08,6));
        Creditcardtransaction c47= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,08,6));
        Creditcardtransaction c48= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,08,6));
        Creditcardtransaction c49= new Creditcardtransaction("Peter builds a tree house", 6000, true,  LocalDate.of(2021,02,3));
        Creditcardtransaction c50= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2021,02,6));
        Creditcardtransaction c51= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2021,02,6));
        Creditcardtransaction c52= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2021,02,6));
        Creditcardtransaction c53= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2021,02,6));
        Creditcardtransaction c54= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2021,02,6));
        Creditcardtransaction c55= new Creditcardtransaction("Megan builds a chair", 5000, true,  LocalDate.of(2021,02,3));
        Creditcardtransaction c56= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2021,02,6));
        Creditcardtransaction c57= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2021,02,6));
        Creditcardtransaction c58= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2021,02,6));
        Creditcardtransaction c59= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2021,02,6));
        Creditcardtransaction c60= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2021,02,6));
        Creditcardtransaction c61= new Creditcardtransaction("Maier builds a pool", 345, true,  LocalDate.of(2020,01,3));
        Creditcardtransaction c62= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,02,6));
        Creditcardtransaction c63= new Creditcardtransaction("Stephan builds a car", 789, true,  LocalDate.of(2020,03,3));
        Creditcardtransaction c64= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,04,6));
        Creditcardtransaction c65= new Creditcardtransaction("Marion builds a table", 535, true,  LocalDate.of(2020,05,3));
        Creditcardtransaction c66= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,06,6));
        Creditcardtransaction c67= new Creditcardtransaction("Sally builds a box", 452, true,  LocalDate.of(2020,07,3));
        Creditcardtransaction c68= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,08,6));
        Creditcardtransaction c69= new Creditcardtransaction("Robin creates a pool", 421, true,  LocalDate.of(2020,09,3));
        Creditcardtransaction c70= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,10,6));
        Creditcardtransaction c71= new Creditcardtransaction("Michi plans to build a chair", 345, true,  LocalDate.of(2020,11,3));
        Creditcardtransaction c72= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,12,6));
        Creditcardtransaction c73 = new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,01,6));
        Creditcardtransaction c74= new Creditcardtransaction("A Müller buys his sister a flat", 12, true,  LocalDate.of(2020,02,4));
        Creditcardtransaction c75= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,03,6));
        Creditcardtransaction c76= new Creditcardtransaction("A Maier buys his sister a chair", 8963, true,  LocalDate.of(2020,04,4));
        Creditcardtransaction c77= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,05,6));
        Creditcardtransaction c78= new Creditcardtransaction("A Peter buys his sister a car", 4785, true,  LocalDate.of(2020,06,4));
        Creditcardtransaction c79= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,07,6));
        Creditcardtransaction c80= new Creditcardtransaction("A Griffin buys his sister a flat", 3562, true,  LocalDate.of(2020,08,4));
        Creditcardtransaction c81= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,09,6));
        Creditcardtransaction c82= new Creditcardtransaction("A Megan buys her sister a flat", 45, true,  LocalDate.of(2020,10,4));
        Creditcardtransaction c83= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,11,6));
        Creditcardtransaction c84= new Creditcardtransaction("A Marry buys her sister a camera", 12, true,  LocalDate.of(2020,12,4));
        Creditcardtransaction c85= new Creditcardtransaction("A Man buys his sister a camera", 45, true,  LocalDate.of(2020,01,5));
        Creditcardtransaction c86= new Creditcardtransaction("Anja Beck buys from an  company", 988, true,  LocalDate.of(2020,02,5));
        Creditcardtransaction c87= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,03,6));
        Creditcardtransaction c88= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,04,6));
        Creditcardtransaction c89= new Creditcardtransaction("A Glory buys her sister a plane", 16, true,  LocalDate.of(2020,05,5));
        Creditcardtransaction c90= new Creditcardtransaction("A Sister buys her sister a camera", 14, true,  LocalDate.of(2020,06,5));
        Creditcardtransaction c91= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,07,6));
        Creditcardtransaction c92= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,08,6));
        Creditcardtransaction c93= new Creditcardtransaction("A Husband buys her sister a spoon", 58, true,  LocalDate.of(2020,09,5));
        Creditcardtransaction c94= new Creditcardtransaction("A Housewife buys her sister a kive", 89, true,  LocalDate.of(2020,10,5));
        Creditcardtransaction c95= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,11,6));
        Creditcardtransaction c96= new Creditcardtransaction("Fail", 189, false,  LocalDate.of(2020,12,6));
        Creditcardtransaction c97= new Creditcardtransaction("A Housewife buys her sister a kive", 56, true,  LocalDate.of(2021,1,6));
        Creditcardtransaction c98= new Creditcardtransaction("A Housewife buys her sister a box", 36, true,  LocalDate.of(2021,12,6));




// do Fehlermeldung, wenn sum > netIncome -> kunde muss zahlen können!!!

        // Ordne den Transaktionen Kunden zu
        //c1.setCustomer(customer_1);
        c1.setCustomer(customer_1);
        c2.setCustomer(customer_1);
        c3.setCustomer(customer_1);
        c4.setCustomer(customer_1);
        c5.setCustomer(customer_1);
        c6.setCustomer(customer_1);
        c7.setCustomer(customer_2);
        c8.setCustomer(customer_2);
        c9.setCustomer(customer_2);
        c10.setCustomer(customer_2);
        c11.setCustomer(customer_2);
        c12.setCustomer(customer_2);
        c13.setCustomer(customer_3);
        c14.setCustomer(customer_3);
        c15.setCustomer(customer_3);
        c16.setCustomer(customer_3);
        c17.setCustomer(customer_3);
        c18.setCustomer(customer_3);
        c19.setCustomer(customer_4);
        c20.setCustomer(customer_4);
        c21.setCustomer(customer_4);
        c22.setCustomer(customer_4);
        c23.setCustomer(customer_4);
        c24.setCustomer(customer_4);
        c25.setCustomer(customer_5);
        c26.setCustomer(customer_5);
        c27.setCustomer(customer_5);
        c28.setCustomer(customer_5);
        c29.setCustomer(customer_5);
        c30.setCustomer(customer_5);
        c31.setCustomer(customer_6);
        c32.setCustomer(customer_6);
        c33.setCustomer(customer_6);
        c34.setCustomer(customer_6);
        c35.setCustomer(customer_6);
        c36.setCustomer(customer_6);
        c37.setCustomer(customer_1);
        c38.setCustomer(customer_1);
        c39.setCustomer(customer_1);
        c40.setCustomer(customer_1);
        c41.setCustomer(customer_1);
        c42.setCustomer(customer_1);
        c43.setCustomer(customer_2);
        c44.setCustomer(customer_2);
        c45.setCustomer(customer_2);
        c46.setCustomer(customer_2);
        c47.setCustomer(customer_2);
        c48.setCustomer(customer_2);
        c49.setCustomer(customer_3);
        c50.setCustomer(customer_3);
        c51.setCustomer(customer_3);
        c52.setCustomer(customer_3);
        c53.setCustomer(customer_3);
        c54.setCustomer(customer_3);
        c55.setCustomer(customer_4);
        c56.setCustomer(customer_4);
        c57.setCustomer(customer_4);
        c58.setCustomer(customer_4);
        c59.setCustomer(customer_4);
        c60.setCustomer(customer_4);
        c61.setCustomer(customer_2);
        c62.setCustomer(customer_2);
        c63.setCustomer(customer_2);
        c64.setCustomer(customer_2);
        c65.setCustomer(customer_2);
        c66.setCustomer(customer_2);
        c67.setCustomer(customer_2);
        c68.setCustomer(customer_2);
        c69.setCustomer(customer_2);
        c70.setCustomer(customer_2);
        c71.setCustomer(customer_2);
        c72.setCustomer(customer_2);
        c73.setCustomer(customer_3);
        c74.setCustomer(customer_3);
        c75.setCustomer(customer_3);
        c76.setCustomer(customer_3);
        c77.setCustomer(customer_3);
        c78.setCustomer(customer_3);
        c79.setCustomer(customer_3);
        c80.setCustomer(customer_3);
        c81.setCustomer(customer_3);
        c82.setCustomer(customer_3);
        c83.setCustomer(customer_3);
        c84.setCustomer(customer_3);
        c85.setCustomer(customer_6);
        c86.setCustomer(customer_6);
        c87.setCustomer(customer_6);
        c88.setCustomer(customer_6);
        c89.setCustomer(customer_6);
        c90.setCustomer(customer_6);
        c91.setCustomer(customer_6);
        c92.setCustomer(customer_6);
        c93.setCustomer(customer_6);
        c94.setCustomer(customer_6);
        c95.setCustomer(customer_6);
        c96.setCustomer(customer_6);
        c97.setCustomer(customer_5);
        c98.setCustomer(customer_5);

        //Ordne den Transaktionen Verkäufer zu
        c1.setVendor(v1);
        c2.setVendor(v2);
        c3.setVendor(v3);
        c4.setVendor(v4);
        c5.setVendor(v5);
        c6.setVendor(v6);
        c7.setVendor(v7);
        c8.setVendor(v1);
        c9.setVendor(v2);
        c10.setVendor(v3);
        c11.setVendor(v4);
        c12.setVendor(v5);
        c13.setVendor(v6);
        c14.setVendor(v7);
        c15.setVendor(v1);
        c16.setVendor(v2);
        c17.setVendor(v3);
        c18.setVendor(v4);
        c19.setVendor(v5);
        c20.setVendor(v6);
        c21.setVendor(v7);
        c22.setVendor(v1);
        c23.setVendor(v2);
        c24.setVendor(v3);
        c25.setVendor(v4);
        c26.setVendor(v5);
        c27.setVendor(v6);
        c28.setVendor(v7);
        c29.setVendor(v1);
        c30.setVendor(v2);
        c31.setVendor(v3);
        c32.setVendor(v4);
        c33.setVendor(v5);
        c34.setVendor(v6);
        c35.setVendor(v7);
        c36.setVendor(v1);
        c37.setVendor(v2);
        c38.setVendor(v3);
        c39.setVendor(v4);
        c40.setVendor(v5);
        c41.setVendor(v6);
        c42.setVendor(v7);
        c43.setVendor(v1);
        c44.setVendor(v2);
        c45.setVendor(v3);
        c46.setVendor(v4);
        c47.setVendor(v5);
        c48.setVendor(v6);
        c49.setVendor(v7);
        c50.setVendor(v1);
        c51.setVendor(v2);
        c52.setVendor(v3);
        c53.setVendor(v4);
        c54.setVendor(v5);
        c55.setVendor(v6);
        c56.setVendor(v7);
        c57.setVendor(v1);
        c58.setVendor(v2);
        c59.setVendor(v3);
        c60.setVendor(v4);
        c61.setVendor(v5);
        c62.setVendor(v6);
        c63.setVendor(v7);
        c64.setVendor(v1);
        c65.setVendor(v2);
        c66.setVendor(v3);
        c67.setVendor(v4);
        c68.setVendor(v5);
        c69.setVendor(v6);
        c70.setVendor(v7);
        c71.setVendor(v1);
        c72.setVendor(v2);
        c73.setVendor(v3);
        c74.setVendor(v4);
        c75.setVendor(v5);
        c76.setVendor(v6);
        c77.setVendor(v7);
        c78.setVendor(v1);
        c79.setVendor(v2);
        c80.setVendor(v3);
        c81.setVendor(v4);
        c82.setVendor(v5);
        c83.setVendor(v6);
        c84.setVendor(v7);
        c85.setVendor(v1);
        c86.setVendor(v2);
        c87.setVendor(v3);
        c88.setVendor(v4);
        c89.setVendor(v5);
        c90.setVendor(v6);
        c91.setVendor(v7);
        c92.setVendor(v1);
        c93.setVendor(v2);
        c94.setVendor(v3);
        c95.setVendor(v4);
        c96.setVendor(v5);
        c97.setVendor(v6);
        c98.setVendor(v7);


        // speichern wir irgendwo Kunden & Verkäufer?
        /*v1.setTransactions(Arrays.asList(c1, c21));
        v2.setTransactions(Arrays.asList(c2, c12, c16, c17, c18, c19, c20));
        v3.setTransactions(Arrays.asList(c3));
        v4.setTransactions(Arrays.asList(c4));
        v5.setTransactions(Arrays.asList(c5));
        v6.setTransactions(Arrays.asList(c6, c7, c8, c9, c10, c11, c13, c14, c15, c22));


        //Ordne den Kunden Transaktionen zu
        customer_1.setTransactions(Arrays.asList(c1, c6, c21));
        customer_2.setTransactions(Arrays.asList(c2, c7, c22));
        customer_3.setTransactions(Arrays.asList(c3, c9, c16, c17, c18, c19, c20));
        customer_4.setTransactions(Arrays.asList(c4, c8));
        customer_5.setTransactions(Arrays.asList(c5, c10));
        customer_6.setTransactions(Arrays.asList(c11, c12, c13, c14, c15));*/
        //Speichere alle Daten in den Repositorys ab
        System.out.println("test2");
        this.customerRepository.saveAll(Arrays.asList(customer_1, customer_2, customer_3, customer_4, customer_5, customer_6));
        System.out.println("test4");
        this.vendorRepository.saveAll(Arrays.asList(v1,v2,v3,v4,v5,v6, v7));
        this.transactionRepository.saveAll(Arrays.asList(c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22));
        System.out.println("test3");

        System.out.println("test5");
        // Gibt alle Werte der ersten Aufgabe zurück

//        vendorService.getStatisticsAboutMonth(2,2021);
        log.info("-------------Aufgabe 1.1 -------------");
        log.info(vendorService.getFeeForVendor(v6.getVUuid()));
        log.info(String.valueOf(startupService.compareIncomeBeetweenOneMonth(1, 2021)));
        log.info(startupService.getStatistic(1,2021).getCustomers());



    }
}
