package webuildit.myStartup.service;

import org.springframework.stereotype.Service;
import webuildit.myStartup.dto.CreditcardtransactionDTO;
import webuildit.myStartup.dto.VendorDTO;

import java.util.List;
import java.util.UUID;

public interface CreditcardtransactionService {

    CreditcardtransactionDTO addCreditcardtransaction(CreditcardtransactionDTO creditcardtransactionDTO);
    CreditcardtransactionDTO getCreditcardtransactionByUUID(UUID tUuid);
    List<CreditcardtransactionDTO> getAllCreditcardtransaction();
    CreditcardtransactionDTO updateCreditcardtransaction(CreditcardtransactionDTO creditcardtransactionDTO);
    void removeCreditcardtransactionById(UUID tUuid);

}
