package webuildit.myStartup.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import webuildit.myStartup.dto.VendorDTO;
import webuildit.myStartup.model.Vendor;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VendorMapper {

    VendorDTO vendorToVendorDTO(Vendor vendor);

    List<VendorDTO> vendorsToVendorsDTO(List<Vendor> vendor);

    @InheritInverseConfiguration
    Vendor vendorDTOToVendor(VendorDTO vendorDTO);

    List<Vendor> vendorDTOsToVendors(List<VendorDTO> vendorDTOToList);

}
