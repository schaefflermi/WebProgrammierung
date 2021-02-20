package webuildit.myStartup.controller;

import webuildit.myStartup.dto.VendorDTO;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface VendorController {

    VendorDTO addVendor(VendorDTO vendorDTO);
    VendorDTO getVendorByUUID(UUID vUuid);
    List<VendorDTO> getAllVendors();
    VendorDTO updateVendor(UUID vUuid, VendorDTO vendorDTO);
    void removeVendorById(UUID vUuid);
    String getFeeForVendor(UUID vUuid);
    String findSumOfAllTransactionsByDay(int month, int year);
    List<String> findTop3Desc(int month, int year);
    List<String> findAllTop3Asc(int month, int year);
    String compareIncomeBeetweenOneMonth(int month, int year);
    String getStatisticForOneMonth(int month, int year);
}
