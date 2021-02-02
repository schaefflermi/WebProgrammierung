package webuildit.myStartup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webuildit.myStartup.model.Creditcardtransaction;
import webuildit.myStartup.repository.TransactionRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
@Service
public class VendorServiceImpl implements VendorService {
    TransactionRepository transactionRepository;
    @Autowired
    public VendorServiceImpl(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;

    }

    public List<Double> getTransactionFees(UUID tUuid){
        List<Double> result = new LinkedList<>();
        List<Creditcardtransaction> tmp = this.transactionRepository.findAllCreditcardtransactionBytUuid(tUuid);
        tmp.forEach(vendor -> result.add(vendor.getSum()));
        return result;
    }
    @Override
    public List<Creditcardtransaction> getAllCreditcardtransactions(UUID tUuid){
        return this.transactionRepository.findAll();

    }

    /*public List<Creditcardtransaction> getCreditcardtransactionsByUuid(UUID tUuid){
        return this.transactionRepository.findAllByTuuid(tUuid);

    }*/
    @Override
    public Double getTransactionFee(){
        double sum = 0;
        double fee = 0;
        List<Creditcardtransaction> cct = this.transactionRepository.findAll();
        for (int i = 0; i < cct.size(); i++){
            sum = sum + cct.get(i).getSum();
        }
        fee = sum *0.02;
        return fee;
    }


}
