package webuildit.myStartup.service;

import webuildit.myStartup.model.Classification;
import webuildit.myStartup.model.Creditcardtransaction;
import webuildit.myStartup.model.Vendor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface VendorService {
    Double compareIncomeBeetweenOneMonth(int month, int year);
    void getStatisticsAboutMonth(int month, int year);

}
