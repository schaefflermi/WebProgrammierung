package webuildit.myStartup.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import webuildit.myStartup.model.Classification;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
public class VendorDTO {

    private UUID vUuid;
    private String vName;
    private String vAddress;
    private Classification classifictation;

    private List<CreditcardtransactionDTO> creditcardtransactions;

    public VendorDTO(UUID vUuid, String vName, String vAddress, Classification classification, List<CreditcardtransactionDTO> creditcardtransactions){
        this.vUuid = vUuid;
        this.vName = vName;
        this.vAddress = vAddress;
        this.classifictation = classification;
        this.creditcardtransactions = creditcardtransactions;

    }
}
