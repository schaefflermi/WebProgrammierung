package webuildit.myStartup.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import webuildit.myStartup.dto.CreditcardtransactionDTO;
import webuildit.myStartup.model.Creditcardtransaction;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CreditcardtransactionMapper {

    CreditcardtransactionDTO creditcardtransactionToCreditcardtransactionDTO(Creditcardtransaction cct);

    List<CreditcardtransactionDTO> creditcardtransactionsToCreditcardtransactionDTOs(List<Creditcardtransaction> ccts);

    @InheritInverseConfiguration
    Creditcardtransaction creditcardtransactionDTOToCreditcardtransaction(CreditcardtransactionDTO cctDTO);
}
