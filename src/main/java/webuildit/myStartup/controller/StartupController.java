package webuildit.myStartup.controller;

import webuildit.myStartup.dto.StartupDTO;

public interface StartupController {

    StartupDTO getStatisticForOneMonth(int month, int year);
}
