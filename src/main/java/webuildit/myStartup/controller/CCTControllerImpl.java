package webuildit.myStartup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import webuildit.myStartup.model.CreditCardTransaction;
import webuildit.myStartup.repository.TransactionRepository;
import webuildit.myStartup.service.MoneyServiceImpl;
import webuildit.myStartup.service.MoneyServiceInterface;

import java.util.List;
import java.util.UUID;

@Controller
public class CCTControllerImpl implements CCTController {
     MoneyServiceInterface moneyService;
     TransactionRepository transactionRepository;

    @Autowired
    public CCTControllerImpl(){
       // this.moneyService = moneyService;
    }

    @Override
    public Double getTransactionFees(long vId) {
        transactionRepository.findAllById(vId);
     // System.out.println(l1.get(0));

        return null;
    }
}
