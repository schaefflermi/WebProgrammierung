package webuildit.myStartup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import webuildit.myStartup.service.MoneyServiceImpl;
import webuildit.myStartup.service.MoneyServiceInterface;

@Controller
public class MoneyController {
     MoneyServiceInterface moneyService;

    @Autowired
    public MoneyController(){
       // this.moneyService = moneyService;
    }
}
