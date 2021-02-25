package webuildit.myStartup.service;

import org.springframework.stereotype.Service;
import webuildit.myStartup.model.Startup;
import webuildit.myStartup.repository.CustomerRepository;
import webuildit.myStartup.repository.TransactionRepository;
import webuildit.myStartup.repository.VendorRepository;

import java.time.LocalDate;

@Service
public class StartupServiceImpl implements StartupService{

    CustomerRepository customerRepository;
    VendorRepository vendorRepository;
    TransactionRepository transactionRepository;

    public StartupServiceImpl(CustomerRepository customerRepository, VendorRepository vendorRepository, TransactionRepository transactionRepository){
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
        this.transactionRepository = transactionRepository;
    }
    public String compareIncomeBeetweenOneMonth(int month, int year) {
        // Das derzeitige Datum
        LocalDate date1 = LocalDate.now();
        // Derzeitiges Datum wird um 1 reduziert
        LocalDate date2 = date1.minusMonths(1);
        // Summe aller Transaktionen von einem bestimmten Monat
        Double List1 = transactionRepository.findSumOfAllTransactionsByMonth(month, year);
        if(List1 == null){
            List1 = 0.0;
        }
        // Summe aller Transaktionen von dem Vormonat
        Double List2 = 0.0;
        if(month == 1){
            month = 12;
            List2 = transactionRepository.findSumOfAllTransactionsByPreviousMonth3(month, year -1, date2);
        } else {
            List2 = transactionRepository.findSumOfAllTransactionsByPreviousMonth3(month -1, year, date2);

        }
        if(List2 == null){
            List2 = 0.0;
        }
        // Differenz der beiden Summen
        return String.valueOf(List1-List2);
    }

    public Startup getStatic(int month, int year){
        Startup s1 = new Startup();
        s1.setRevenue(transactionRepository.findSumOfAllTransactionsByDay(month, year));
        s1.setClassificationsUp(vendorRepository.findAllTop3Asc(month, year));
        s1.setClassificationsDown(vendorRepository.findTop3Desc(month, year));
        s1.setDifference(this.compareIncomeBeetweenOneMonth(month, year));

        return s1;
    }


}
