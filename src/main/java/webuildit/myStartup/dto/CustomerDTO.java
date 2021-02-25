package webuildit.myStartup.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import webuildit.myStartup.model.Creditcardtransaction;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
public class CustomerDTO {

    private UUID cUuid;
    private String cName;
    private String cAddress;
    @Min(0)
    private int netIncome;


    public CustomerDTO(UUID cUuid, String cName, String cAddress, int netIncome){
        this.cUuid = cUuid;
        this.cName = cName;
        this.cAddress = cAddress;
        this.netIncome = netIncome;
    }

}
