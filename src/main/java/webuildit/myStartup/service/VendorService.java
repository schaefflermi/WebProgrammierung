package webuildit.myStartup.service;

import webuildit.myStartup.model.Creditcardtransaction;

import java.util.List;
import java.util.UUID;

public interface VendorService {
    List<Creditcardtransaction> getAllCreditcardtransactions(UUID tUuid);
    Double getTransactionFee();
}
