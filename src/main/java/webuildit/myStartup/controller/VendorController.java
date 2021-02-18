package webuildit.myStartup.controller;

import webuildit.myStartup.dto.VendorDTO;

import java.util.List;
import java.util.UUID;

public interface VendorController {

    VendorDTO addVendor(VendorDTO vendorDTO);
    VendorDTO getVendorByUUID(UUID vUuid);
    List<VendorDTO> getAllVendors();
    VendorDTO updateVendor(UUID vUuid, VendorDTO vendorDTO);
    void removeVendorById(UUID vUuid);
}
