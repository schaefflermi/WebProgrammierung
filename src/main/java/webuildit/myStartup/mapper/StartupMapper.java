package webuildit.myStartup.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import webuildit.myStartup.dto.StartupDTO;
import webuildit.myStartup.model.Startup;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StartupMapper {

    StartupDTO startupToStartupDTO(Startup startup);

    List<StartupDTO> startupsToStartupDTO(List<Startup> startups);

    @InheritInverseConfiguration
    Startup startupDTOToStartup(StartupDTO startupDTO);

    List<Startup> startupDTOsToStartups(List<StartupDTO> startupDTOToList);



}
