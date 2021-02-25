package webuildit.myStartup.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import webuildit.myStartup.model.Classification;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
public class VendorDTO {

    private UUID vUuid;
    private String vName;
    private String vAddress;
  //  @NotBlank
    @Enumerated(EnumType.STRING)
    private Classification classification;



    public VendorDTO(UUID vUuid, String vName, String vAddress, Classification classification){
        this.vUuid = vUuid;
        this.vName = vName;
        this.vAddress = vAddress;
        this.classification = classification;

    }
}
