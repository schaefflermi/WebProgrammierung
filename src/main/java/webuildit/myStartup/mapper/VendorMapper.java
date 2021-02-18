package webuildit.myStartup.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import webuildit.myStartup.dto.VendorDTO;
import webuildit.myStartup.model.Vendor;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VendorMapper {
    @Mapping(target = "")
    VendorDTO awardToAwardDTO(Vendor vendor);

    List<VendorDTO> awardsToAwardsDTO(List<Vendor> vendor);

    @InheritInverseConfiguration
    Vendor awardDTOToAward(VendorDTO awardDTO);

    List<Vendor> awardDTOsToAwards(List<VendorDTO> awardDTOToList);

}
