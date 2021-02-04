package webuildit.myStartup.service;

import webuildit.myStartup.model.Classification;
import webuildit.myStartup.model.Creditcardtransaction;
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
}
