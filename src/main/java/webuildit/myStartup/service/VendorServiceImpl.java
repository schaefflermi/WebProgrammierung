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
    //Unbenutzt
    public List<Double> getTransactionFees(UUID tUuid){
        List<Double> result = new LinkedList<>();
        List<Creditcardtransaction> tmp = this.transactionRepository.findAllCreditcardtransactionBytUuid(tUuid);
        tmp.forEach(vendor -> result.add(vendor.getSum()));
        return result;
    }
    @Override
    public List<Creditcardtransaction> getAllCreditcardtransactions(UUID tUuid){
        return this.transactionRepository.findAll();

    }

//    public List<Creditcardtransaction> findByTdateGreaterThanAndTdateLessThan(LocalDate start, LocalDate end){
//        List<Creditcardtransaction> cctInMonth = this.findByTdateGreaterThanAndTdateLessThan(start, end);
//        return this.findByTdateGreaterThanAndTdateLessThan(start, end);
//    }

   /* // Methode, die per abgeleiteter Datenbankabfrage alle Transaktionen eines Monats abruft
    // und diese dann mit der Transaktionsfee verrechnet, sodass die Einnahmen des Startups ausgegeben werden
    // CHECK IF STATUS = TRUE !!!!!!!!!!!!!!
    public Double getTransactionFeeForStartup(LocalDate start, LocalDate end){
        double sum = 0;
        double fee = 0;
        List<Creditcardtransaction> cctInMonth = this.transactionRepository.findByTdateGreaterThanAndTdateLessThan(start, end);
        for (int i = 0; i < cctInMonth.size(); i++){
            sum = sum + cctInMonth.get(i).getSum();
        }
        fee = sum *0.02;
        return fee;
    }*/


    // Methode, die die Einnahmen des aktuellen Monats mit den Einnahmen des vorigen Monats vergleicht
    public Double compareIncome(int month, int year){
        // Variablen zum Zwischenspeichern der Berechnungen
        double sum1 = 0;
        double sum2 = 0;
        double income1 = 0;
        double income2 = 0;
        double differenz = 0;

        // check if aktueller Monat
        LocalDate currentDate = Calendar.getInstance().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (month == currentDate.getMonthValue() && year == currentDate.getYear()){
            // alle Transaktionen des gewählten Monats in Liste speichern
            List<Creditcardtransaction> cctUntilDay = this.transactionRepository.findAllTransactionsByDay(month, year);
            List<Creditcardtransaction> cctUntilDayMonthBefore;
            // alle Transaktionen des Monats zuvor in Liste speichern
            if(month == 01){
                cctUntilDayMonthBefore = this.transactionRepository.findAllTransactionsByMonth(12, year-1);
            } else{
                cctUntilDayMonthBefore = this.transactionRepository.findAllTransactionsByMonth(month-1, year);
            }

            // income Monat 1 bis zum aktuellen Tag
            for(int i = 0; i < cctUntilDay.size(); i++){
                sum1 = sum1 + cctUntilDay.get(i).getSum();
            }
            income1 = sum1 * 0.02; // man könnte auch einfach die Summen vergleichen, gefragt sind aber Einnahmen

            // income Monat 2 bis zum gleihen Tag
            for(int i = 0; i < cctUntilDay.size(); i++){
                sum2 = sum2 + cctUntilDayMonthBefore.get(i).getSum();
            }
            income2 = sum2 * 0.02; // man könnte auch einfach die Summen vergleichen, gefragt sind aber Einnahmen
        }
        // ansonsten berechne es für den gesamten Monat
        else {
            // alle Transaktionen des gewählten Monats in Liste speichern
            List<Creditcardtransaction> cctMonth = this.transactionRepository.findAllTransactionsByMonth(month, year);
            List<Creditcardtransaction> cctMonthBefore;
            // alle Transaktionen des Monats zuvor in Liste speichern
            if(month == 01){
                cctMonthBefore = this.transactionRepository.findAllTransactionsByMonth(12, year-1);
            } else{
                cctMonthBefore = this.transactionRepository.findAllTransactionsByMonth(month-1, year);
            }
            // income Monat 1
            for(int i = 0; i < cctMonth.size(); i++){
                sum1 = sum1 + cctMonth.get(i).getSum();
            }
            income1 = sum1 * 0.02; // man könnte auch einfach die Summen vergleichen, gefragt sind aber Einnahmen

            // income Monat 2
            for(int i = 0; i < cctMonthBefore.size(); i++){
                sum2 = sum2 + cctMonthBefore.get(i).getSum();
            }
            income2 = sum2 * 0.02; // man könnte auch einfach die Summen vergleichen, gefragt sind aber Einnahmen


        }
        // Vergleich income 1 mit income 2
        differenz = income1 - income2;
        System.out.println("Die Differenz der Einnahmen zwischen Monat 1 & 2 liegt bei: "+differenz);
        return differenz;
    }


    // brauchen wir das?
    public List<Creditcardtransaction> getCreditcardtransactionsByUuid(UUID tUuid){
        return this.transactionRepository.findBytUuid(tUuid);
    }

    // was machen wir hier??
    @Override
    public Double getTransactionFee(){
        double sum = 0;
        double fee = 0;
        int month = YearMonth.now().getMonthValue();
        int year = Year.now().getValue();
        List<Creditcardtransaction> cct = this.transactionRepository.findAll();
        for (int i = 0; i < cct.size(); i++){
            if(cct.get(i).getTdate().getMonthValue() == month && cct.get(i).getTdate().getYear() == year && cct.get(i).isStatus()){
                sum = sum + cct.get(i).getSum();
            }

        }
        fee = sum *0.02;
        return fee;
    }

    // berechnet den Gesamtumsatz für ein Gewerbe
    public Double getIncomeForClassification(Classification classification){
        List<Vendor> vByClass = this.vendorRepository.findByClassification(classification);
        double sumIncome=0;
        for (int i = 0; i < vByClass.size(); i++){
            for(int j=0; i< vByClass.get(i).getTransactions().size(); j++){
                if(vByClass.get(i).getTransactions().get(j).isStatus()) {
                    sumIncome = sumIncome + vByClass.get(i).getTransactions().get(j).getSum();
                }
            }
        }
        return sumIncome;
    }

    // findet alle Verkäufer einer Klassifikation
    public List<Vendor> findByClassification(Classification classification){
        return this.findByClassification(classification);
    }



//    // Methode um für den aktuellen Monat anfallenden Kosten eines Verkäufers zu berechnen
//    @Override
//    public Double getCostForVendorForCurrentMonth(UUID vUuid){
//        List<Creditcardtransaction> allVendors = this.transactionRepository.findAll();;
//        List<Creditcardtransaction> oneVendor = new ArrayList<>();
//        double sum = 0;
//        int month = YearMonth.now().getMonthValue();
//        int year = Year.now().getValue();
//
//        for(int i=0; i< allVendors.size(); i++){
//            // Checkt alle Transaktionen eines Verkäufers
//            if(allVendors.get(i).getVendor().getVUuid().equals(vUuid)){
//                // Checkt auf den aktuellen Monat, Jahr und Erfolg der Transaktion
//                if(allVendors.get(i).getTdate().getMonthValue() == month && allVendors.get(i).getTdate().getYear() == year && allVendors.get(i).isStatus()){
//                    oneVendor.add(allVendors.get(i));
//                }
//            }
//        }
//
//        //Berechnet die Summe der Transaktionen
//        for (int j = 0; j < oneVendor.size(); j++){
//            sum = sum + oneVendor.get(j).getSum();
//
//        }
//        //Errechnet die Transaktionsgebühren
//        return sum * 0.02;
//    }
    //Funktionsfähig
    //Gibt alle Kunden aus welche im Monat 5 erfolglose Transaktionen hatten -> manchmal aber den falschen Kunden ???
    //Es fehlt noch die implementierung des Parameters für den Monat
    @Override
    public void findDistinctByStatus(int month, int year){
        List<Creditcardtransaction> cct = transactionRepository.findDistinctByStatus(month, year);

        //Entfernt alles, was nicht mindestens 5 mal vorkommt
        for(int i = 0; i < cct.size(); i++) {
            for(int j = 0; j < cct.size(); j++){
                int sum = 0;
             if(cct.get(i).getCustomer().getCUuid().equals(cct.get(j).getCustomer().getCUuid())){
                 sum++;
             }
             if(sum < 4){
                 cct.remove(i);
             }
            }
        }
        //Entfernt doppelte Beiträge
        for(int i = 0; i < cct.size(); i++){
            //verhindert das die beiden Variablen den gleichen Wert haben, ansonsten wird jeder eintrag entfernt
            int j = i+1;
            for(;j < cct.size(); j++){
                if(cct.get(i).getCustomer().getCUuid().equals(cct.get(j).getCustomer().getCUuid())){
                    cct.remove(j);
                }
            };

        }
        for (int i = 0; i < cct.size(); i++){
            System.out.println(cct.get(i).getCustomer().getCUuid());
            System.out.println(cct.get(i).getCustomer().getCName());
        }

    }
    /*@Override
    public List<City> findByNameEndsWith(String name) {

        var cities = (List<City>) repository.findByNameEndsWith(name);
        return cities;
    }*/



    @Override
    public List<Customer> findAllCustomerWithFiveFailedTransaction() {
        var customer = (List<Customer>) customerRepository.findAllCustomerWithFiveFailedTransaction();
        return customer;
    }

    @Override
    public List<Creditcardtransaction> findAllByTdateLikeAndVendor(LocalDate date, UUID vUuid) {
        var cct = (List<Creditcardtransaction>) transactionRepository.findAllByTdateLikeAndVendor(date, vUuid);
        return null;
    }
    // Aufgabe 1: Bei Eingabe der Id eines Verkäufers, der abzurechnende Betrag für diesen für den aktuellen Monat zurückgegeben wird.
    /*@Override
    public double findAllByVendorandCurrentMonth(UUID vUuid) {
        var cct = (List<Creditcardtransaction>) transactionRepository.findAllByVendorandCurrentMonth(vUuid);
        double sum = 0;
        for(int i = 0; i < cct.size(); i++){
            sum = sum + cct.get(i).getSum();
        }
        return sum*0.02;
    }

    @Override
    public double findAllTransactionsByMonth(int month, int year) {
        double sum = 0;
        var cct = (List<Creditcardtransaction>) transactionRepository.findAllTransactionsByMonth(month, year);
       for(int i = 0; i < cct.size(); i++){
            sum = sum + cct.get(i).getSum();
        }
        return sum * 0.02;
    }
     */




    /*@Override
    public List<Creditcardtransaction> findAllByTdateMonth(int date) {
        var cct = (List<Creditcardtransaction>) transactionRepository.findAllByTdateMonth(date);
        return cct;*/
    //}


}
