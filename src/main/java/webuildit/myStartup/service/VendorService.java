package webuildit.myStartup.service;

import webuildit.myStartup.model.Classification;
import webuildit.myStartup.model.Creditcardtransaction;
import webuildit.myStartup.model.Customer;
import webuildit.myStartup.model.Vendor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface VendorService {
    List<Creditcardtransaction> getAllCreditcardtransactions(UUID tUuid);
    Double getTransactionFee();
   // Double getTransactionFeeForStartup(LocalDate start, LocalDate end);
    List<Vendor> findByClassification(Classification classification);
    Double getIncomeForClassification(Classification classification);
   // void ausgabe(UUID vUuid);
   Double compareIncome(LocalDate m1start, LocalDate m1end, LocalDate m2start, LocalDate m2end);
   Double getCostForVendorForCurrentMonth(UUID vUuid);
   void findDistinctByStatus(Boolean status);
   List<Customer> findAllCustomerWithFiveFailedTransaction();
   List<Creditcardtransaction> findAllByTdateLikeAndVendor(LocalDate date, UUID vUuid);
  // List<Creditcardtransaction> findAllByTdateMonth(int date);
  // Aufgabe 1: Bei Eingabe der Id eines Verkäufers, der abzurechnende Betrag für diesen für den aktuellen Monat zurückgegeben wird.
    double findAllByVendorandCurrentMonth(UUID vUuid);
    double findAllByVendorandCurrentMonth(int month);
}
