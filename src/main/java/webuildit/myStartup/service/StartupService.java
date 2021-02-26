package webuildit.myStartup.service;

import webuildit.myStartup.dto.StartupDTO;

public interface StartupService {

    String compareIncomeBeetweenOneMonth(int month, int year);
    StartupDTO getStatistic(int month, int year);

}
