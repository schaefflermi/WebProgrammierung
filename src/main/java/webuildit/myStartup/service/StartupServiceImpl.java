package webuildit.myStartup.service;

import org.springframework.stereotype.Service;
import webuildit.myStartup.dto.StartupDTO;
import webuildit.myStartup.mapper.StartupMapper;
import webuildit.myStartup.model.Startup;
import webuildit.myStartup.repository.CustomerRepository;
import webuildit.myStartup.repository.StartupRepository;
import webuildit.myStartup.repository.CreditcardtransactionRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class StartupServiceImpl implements StartupService{

    CustomerRepository customerRepository;
    StartupRepository startupRepository;
    CreditcardtransactionRepository transactionRepository;
    StartupMapper startupMapper;

    public StartupServiceImpl(CustomerRepository customerRepository, StartupRepository startupRepository, CreditcardtransactionRepository transactionRepository, StartupMapper startupMapper){
        this.customerRepository = customerRepository;
        this.startupRepository = startupRepository;
        this.transactionRepository = transactionRepository;
        this.startupMapper = startupMapper;
    }
    public Double compareIncomeBeetweenOneMonth(int month, int year) {
        // Das derzeitige Datum
        LocalDate date1 = LocalDate.now();
        // Derzeitiges Datum wird um 1 reduziert
        LocalDate date2 = date1.minusMonths(1);
        // Summe aller Transaktionen von einem bestimmten Monat
        Double List1 = startupRepository.findSumOfAllTransactionsByMonth(month, year);
        if(List1 == null){
            List1 = 0.0;
        }
        // Summe aller Transaktionen von dem Vormonat
        Double List2 = 0.0;
        if(month == 1){
            month = 12;
            List2 = startupRepository.findSumOfAllTransactionsByPreviousMonth3(month, year -1, date2);
        } else {
            List2 = startupRepository.findSumOfAllTransactionsByPreviousMonth3(month -1, year, date2);

        }
        if(List2 == null){
            List2 = 0.0;
        }
        // Differenz der beiden Summen
        return (List1-List2);
    }

    public StartupDTO getStatistic(int month, int year){
        Startup s1 = new Startup();
        try {


        s1.setRevenue(startupRepository.findSumOfAllTransactionsByDay(month, year));
        } catch (java.lang.NullPointerException nullPointerException) {
        s1.setRevenue(0);
        }
        s1.setClassificationsUp(String.valueOf(startupRepository.findAllTop3Asc(month, year)));
        s1.setClassificationsDown(String.valueOf(startupRepository.findTop3Desc(month, year)));
        s1.setCustomers(String.valueOf((customerRepository.findAllCustomerWithFiveFailedTransaction(month, year))));
        s1.setDifference(this.compareIncomeBeetweenOneMonth(month, year));

        startupRepository.save(s1);
        return new StartupDTO(s1.getSUuid(), s1.getRevenue(), s1.getClassificationsDown(), s1.getClassificationsUp(), s1.getCustomers(), s1.getDifference());
    }


}
