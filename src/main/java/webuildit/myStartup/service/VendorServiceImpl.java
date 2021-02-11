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
    public Double compareIncome(LocalDate m1start, LocalDate m1end, LocalDate m2start, LocalDate m2end){
        double sum1 = 0;
        double sum2 = 0;
        double income1 = 0;
        double income2 = 0;
        double differenz = 0;
        LocalDate currentDate = Calendar.getInstance().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        List<Creditcardtransaction> cctInMonthOne = this.transactionRepository.findByTdateGreaterThanAndTdateLessThan(m1start, m1end);
        List<Creditcardtransaction> cctInMonth2 = this.transactionRepository.findByTdateGreaterThanAndTdateLessThan(m2start, m2end);
        // check, if current Monat:
        System.out.println("current Month "+currentDate.getMonth());
        System.out.println("m1 Month: "+m2start.getMonth());
        System.out.println("currentYear: "+currentDate.getYear());
        System.out.println("m1Year: "+m2start.getYear());
        if(currentDate.getMonth().equals(m2start.getMonth()) && currentDate.getYear() == m2start.getYear()){
            // berechne Income Monat 1 bis aktueller Tag
            // das hier testen!!!!!!!
              System.out.println("Tag: "+currentDate.getDayOfMonth());
            for (int i = 0; i <  currentDate.getDayOfMonth(); i++){
                // check if status is true, also Transaktion durchgeführt
                System.out.println("status: :(");
                System.out.println("Innerhalb for-Schleife");

                if(cctInMonthOne.get(i).isStatus()){
                    System.out.println("status true");
                    System.out.println("Test: "+cctInMonthOne.get(i).getSum());
                    sum1 = sum1 + cctInMonthOne.get(i).getSum();
                    System.out.println(sum1);
                }
            }
            System.out.println("sum1: "+sum1);
            income1 = sum1 *0.02;
            System.out.println("this month income1: "+income1);

            // berechne Income Monat 2 bis aktueller Tag
            for (int i = 0; i < currentDate.getDayOfMonth(); i++){
                if(cctInMonth2.get(i).isStatus()){
                    sum2 = sum2 + cctInMonth2.get(i).getSum();
                }
            }
            income2 = sum2 *0.02;
            System.out.println("this month income2: "+income2);

            // Different Monat 1 zu Monat 2
            differenz = income2 - income1;
        }else{
            // berechne Income Monat 1
            for (int i = 0; i < cctInMonthOne.size(); i++){
                if(cctInMonthOne.get(i).isStatus()){
                    sum1 = sum1 + cctInMonthOne.get(i).getSum();
                }
            }
            income1 = sum1 *0.02;
            System.out.println("random Month income1 "+income1);

            // berechne Income Monat 2
            for (int i = 0; i < cctInMonth2.size(); i++){
                if(cctInMonth2.get(i).isStatus()){
                    sum2 = sum2 + cctInMonth2.get(i).getSum();
                }
            }
            income2 = sum2 *0.02;
            System.out.println("randoom Month income2 "+income2);

            // Different Monat 1 zu Monat 2
             differenz = income2 - income1;
        }
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



    // Methode um für den aktuellen Monat anfallenden Kosten eines Verkäufers zu berechnen
    @Override
    public Double getCostForVendorForCurrentMonth(UUID vUuid){
        List<Creditcardtransaction> allVendors = this.transactionRepository.findAll();;
        List<Creditcardtransaction> oneVendor = new ArrayList<>();
        double sum = 0;
        int month = YearMonth.now().getMonthValue();
        int year = Year.now().getValue();

        for(int i=0; i< allVendors.size(); i++){
            // Checkt alle Transaktionen eines Verkäufers
            if(allVendors.get(i).getVendor().getVUuid().equals(vUuid)){
                // Checkt auf den aktuellen Monat, Jahr und Erfolg der Transaktion
                if(allVendors.get(i).getTdate().getMonthValue() == month && allVendors.get(i).getTdate().getYear() == year && allVendors.get(i).isStatus()){
                    oneVendor.add(allVendors.get(i));
                }
            }
        }

        //Berechnet die Summe der Transaktionen
        for (int j = 0; j < oneVendor.size(); j++){
            sum = sum + oneVendor.get(j).getSum();

        }
        //Errechnet die Transaktionsgebühren
        return sum * 0.02;
    }
    //Funktionsfähig
    //Gibt alle Kunden aus welche im Monat 5 erfolglose Transaktionen hatten
    //Es fehlt noch die implementierung des Parameters für den Monat
    @Override
    public void findDistinctByStatus(Boolean status){
        List<Creditcardtransaction> cct = transactionRepository.findDistinctByStatus(status);

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
    }*/

    @Override
    public double findAllByVendorandCurrentMonth(int month) {
        double sum = 0;
        var cct = (List<Creditcardtransaction>) transactionRepository.findAllByVendorandCurrentMonth(month);
       for(int i = 0; i < cct.size(); i++){
            sum = sum + cct.get(i).getSum();
        }
        return sum * 0.02;
    }



    /*@Override
    public List<Creditcardtransaction> findAllByTdateMonth(int date) {
        var cct = (List<Creditcardtransaction>) transactionRepository.findAllByTdateMonth(date);
        return cct;*/
    //}


}
