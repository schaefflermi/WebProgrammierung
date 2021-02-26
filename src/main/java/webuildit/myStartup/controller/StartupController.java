package webuildit.myStartup.controller;

import java.util.List;

public interface StartupController {

    String compareIncomeBeetweenOneMonth(int month, int year);
    String getStatisticForOneMonth(int month, int year);
    String findSumOfAllTransactionsByDay(int month, int year);
    List<String> findTop3Desc(int month, int year);
    List<String> findAllTop3Asc(int month, int year);
}
