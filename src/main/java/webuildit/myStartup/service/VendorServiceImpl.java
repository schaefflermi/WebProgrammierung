package webuildit.myStartup.service;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webuildit.myStartup.model.Classification;
import webuildit.myStartup.model.Creditcardtransaction;
import webuildit.myStartup.model.Customer;
import webuildit.myStartup.model.Vendor;
import webuildit.myStartup.repository.CustomerRepository;
import webuildit.myStartup.repository.TransactionRepository;
import webuildit.myStartup.repository.VendorRepository;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.*;

@Service
public class VendorServiceImpl implements VendorService {
    TransactionRepository transactionRepository;
    VendorRepository vendorRepository;
    CustomerRepository customerRepository;

    @Autowired
    public VendorServiceImpl(TransactionRepository transactionRepository, VendorRepository vendorRepository, CustomerRepository customerRepository){
        this.transactionRepository = transactionRepository;
        this.vendorRepository=vendorRepository;
        this.customerRepository=customerRepository;

    }
    // this method calculate
    @Override
    public Double compareIncomeBeetweenOneMonth(int month, int year) {
        // Das derzeitige Datum
        LocalDate date1 = LocalDate.now();
        // Derzeitiges Datum wird um 1 reduziert
        LocalDate date2 = date1.minusMonths(1);
        // Summe aller Transaktionen von einem bestimmten Monat
        Double List1 = transactionRepository.findSumOfAllTransactionsByMonth(month, year);
        // Summe aller Transaktionen von dem Vormonat
        Double List2 = transactionRepository.findSumOfAllTransactionsByPreviousMonth3(month -1, year, date2);
        // Differenz der beiden Summen
        return List1-List2;
    }
    // Gibt alle Werte für Aufgabe 1 zurück
    @Override
    public void getStatisticsAboutMonth(int month, int year) {
       System.out.println("1.2 aktuellen Einnahmen des StartUps für diesen Monat " + transactionRepository.findSumOfAllTransactionsByDay(month, year));
       System.out.println("1.3 drei Gewerbe mit den höchsten Einkommen " + vendorRepository.findTop3Desc());
       System.out.println("1.3 drei Gewerbe mit den niedrigsten Einkommen " + vendorRepository.findAllTop3Asc());
       System.out.println("1.4 alle Kunden mit mindestens 5 erfolglosen Transaktionen in dem Monat "+ customerRepository.findAllCustomerWithFiveFailedTransaction(month, year));
       System.out.println("1.5 Differenz zwischen den Einnahmen des eingegebenen Monats mit dem Vormonat "+ this.compareIncomeBeetweenOneMonth(month, year));
    }
}
