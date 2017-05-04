package service;

import model.HealthScreen;

import java.util.List;

/**
 * Created by AceCream on 2017/4/15.
 */
public interface HealthScreenService {

    List<HealthScreen> getAllhealthScreens();

    int addHealthScreen(HealthScreen healthScreen);

    List<HealthScreen> getHealthScreenByInf(String theDate);
}
