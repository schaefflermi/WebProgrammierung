package webuildit.myStartup.service;

import org.springframework.data.repository.query.Param;
import webuildit.myStartup.dto.VendorDTO;

import java.util.List;
import java.util.UUID;

public interface VendorService {
    String compareIncomeBeetweenOneMonth(int month, int year);
    void getStatisticsAboutMonth(int month, int year);
    String getFeeForVendor(UUID uuid);
    VendorDTO addVendor(VendorDTO vendorDTO);
    VendorDTO getVendorByUUID(UUID vUuid);
    List<VendorDTO> getAllVendors();
    VendorDTO updateVendor(VendorDTO vendorDTO);
    void removeVendorById(UUID vUuid);
    String findSumOfAllTransactionsByDay(int month, int year);
    List<String> findTop3Desc(int month, int year);
    List<String> findAllTop3Asc(int month, int year);

}
