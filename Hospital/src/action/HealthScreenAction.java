package action;

import model.HealthScreen;
import service.HealthScreenService;
import service.impl.HealthScreenServiceImpl;

import java.util.List;

/**
 * Created by AceCream on 2017/4/15.
 */
public class HealthScreenAction {

    HealthScreenService healthScreenService = new HealthScreenServiceImpl();

    public List<HealthScreen> getAllhealthScreens() {
        List<HealthScreen> list = healthScreenService.getAllhealthScreens();
        return list;
    }


    public int addHealthScreen(HealthScreen healthScreen) {
        int i = healthScreenService.addHealthScreen(healthScreen);
        return i;
    }

    public List<HealthScreen> getHealthScreenByInf(String theDate) {
        theDate = theDate.trim();
        List<HealthScreen> list = healthScreenService.getHealthScreenByInf(theDate);
        return list;
    }
}
