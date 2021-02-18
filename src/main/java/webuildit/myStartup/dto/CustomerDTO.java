package webuildit.myStartup.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import webuildit.myStartup.model.Creditcardtransaction;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
public class CustomerDTO {

    private UUID cUuid;
    private String cName;
    private String cAddress;
    private int netIncome;


    private List<CreditcardtransactionDTO> creditcardtransactions;

    public CustomerDTO(UUID cUuid, String cName, String cAddress, int netIncome, List<CreditcardtransactionDTO> creditcardtransactions){
        this.cUuid = cUuid;
        this.cName = cName;
        this.cAddress = cAddress;
        this.netIncome = netIncome;
        this.creditcardtransactions = creditcardtransactions;

    }

}
