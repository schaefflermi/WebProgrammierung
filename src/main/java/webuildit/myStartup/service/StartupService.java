package webuildit.myStartup.service;

import webuildit.myStartup.dto.StartupDTO;
import webuildit.myStartup.model.Startup;

import java.util.List;

public interface StartupService {

    String compareIncomeBeetweenOneMonth(int month, int year);
    StartupDTO getStatic(int month, int year);
    String findSumOfAllTransactionsByDay(int month, int year);
    List<String> findTop3Desc(int month, int year);
    List<String> findAllTop3Asc(int month, int year);

}
