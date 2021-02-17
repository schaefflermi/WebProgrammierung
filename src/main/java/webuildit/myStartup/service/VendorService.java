package webuildit.myStartup.service;

import java.util.UUID;

public interface VendorService {
    Double compareIncomeBeetweenOneMonth(int month, int year);
    void getStatisticsAboutMonth(int month, int year);
    String getFeeForVendor(UUID uuid);

}
