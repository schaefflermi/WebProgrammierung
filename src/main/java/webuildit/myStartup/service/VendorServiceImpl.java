package webuildit.myStartup.service;

import webuildit.myStartup.model.Creditcardtransaction;
import webuildit.myStartup.repository.TransactionRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class VendorServiceImpl implements VendorService {
    TransactionRepository transactionRepository;

    public List<Double> getTransactionFees(UUID tUuid){
        List<Double> result = new LinkedList<>();
        List<Creditcardtransaction> tmp = this.transactionRepository.findAllCreditcardtransactionBytUuid(tUuid);
        tmp.forEach(vendor -> result.add(vendor.getSum()));
        return result;
    }

}
