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
     VendorService moneyService;
     TransactionRepository transactionRepository;

    @Autowired
    public CCTControllerImpl(){

    }
  // @Override
   // public String getTransaction(UUID tUuid){
     //   List<Creditcardtransaction> tmp = this.transactionRepository.findAllCreditcardtransactionBytUuid(tUuid);
       // for(int i =0; i > tmp.size();i++) {
       //     return tmp.get(i).getDescription();
        //}

        //return tmp.get(0).getDescription();

  //  }

}
