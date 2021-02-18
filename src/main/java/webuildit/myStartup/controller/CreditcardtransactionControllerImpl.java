package webuildit.myStartup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import webuildit.myStartup.repository.TransactionRepository;

@Controller
public class CreditcardtransactionControllerImpl implements CreditcardtransactionController {
     TransactionRepository transactionRepository;

    @Autowired
    public CreditcardtransactionControllerImpl(){

    }
}
