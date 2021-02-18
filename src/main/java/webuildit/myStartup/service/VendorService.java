package webuildit.myStartup.service;

import webuildit.myStartup.dto.VendorDTO;

import java.util.List;
import java.util.UUID;

public interface VendorService {
    Double compareIncomeBeetweenOneMonth(int month, int year);
    void getStatisticsAboutMonth(int month, int year);
    String getFeeForVendor(UUID uuid);
    VendorDTO addVendor(VendorDTO vendorDTO);
    VendorDTO getVendorByUUID(UUID vUuid);
    List<VendorDTO> getAllVendors();
    VendorDTO updateVendor(VendorDTO vendorDTO);
    void removeVendorById(UUID vUuid);

}
