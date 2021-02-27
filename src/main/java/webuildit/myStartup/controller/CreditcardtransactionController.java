package webuildit.myStartup.controller;

import webuildit.myStartup.dto.CreditcardtransactionDTO;

import java.util.List;
import java.util.UUID;

/***
 * @Author Johannes Wiest
 */
public interface CreditcardtransactionController {

    CreditcardtransactionDTO addCreditcardtransaction(CreditcardtransactionDTO creditcardtransactionDTO);
    CreditcardtransactionDTO getCreditcardtransactionByUUID(UUID tUuid);
    List<CreditcardtransactionDTO> getAllCreditcardtransaction();
    CreditcardtransactionDTO updateCreditcardtransaction(UUID tUuid, CreditcardtransactionDTO creditcardtransactionDTO);
    void removeCreditcardtransactionById(UUID tUuid);

}
