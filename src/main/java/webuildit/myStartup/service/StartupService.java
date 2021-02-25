package webuildit.myStartup.service;

import webuildit.myStartup.model.Startup;

public interface StartupService {

    String compareIncomeBeetweenOneMonth(int month, int year);
    Startup getStatic(int month, int year);

}
