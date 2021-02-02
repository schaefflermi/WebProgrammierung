package webuildit.myStartup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import webuildit.myStartup.model.Creditcardtransaction;
import webuildit.myStartup.repository.TransactionRepository;
import webuildit.myStartup.service.VendorService;

import java.util.List;
import java.util.UUID;

@Controller
public class CCTControllerImpl implements CCTController {
     TransactionRepository transactionRepository;

    @Autowired
    public CCTControllerImpl(){

    }
    public  String getTransactionFee(){
       // List<Creditcardtransaction> cct1 = transactionRepository.findAll();

        return "cct1.get(0).getDescription()";
    }


}
