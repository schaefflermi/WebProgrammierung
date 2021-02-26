package webuildit.myStartup.service;

import webuildit.myStartup.dto.CreditcardtransactionDTO;

import java.util.List;
import java.util.UUID;

public interface CreditcardtransactionService {

    CreditcardtransactionDTO addCreditcardtransaction(CreditcardtransactionDTO creditcardtransactionDTO);
    CreditcardtransactionDTO getCreditcardtransactionByUUID(UUID tUuid);
    List<CreditcardtransactionDTO> getAllCreditcardtransaction();
    CreditcardtransactionDTO updateCreditcardtransaction(CreditcardtransactionDTO creditcardtransactionDTO);
    void removeCreditcardtransactionById(UUID tUuid);

}
