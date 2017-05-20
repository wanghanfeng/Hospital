package action;

import model.Prevention;
import service.PreventionService;
import service.impl.PreventionServiceImpl;

import java.util.List;

/**
 * Created by lp on 2017/5/8.
 */
public class PreventionAction {

    private PreventionService preventionService = new PreventionServiceImpl();

    public int addPrevention(Prevention prevention) {
        return preventionService.addPrevention(prevention);
    }

    public List<Prevention> getAllPrevention() {
        return preventionService.getAllPrevention();
    }

    public List<Prevention> getPreventionByInf(String thePlace) {
        return preventionService.getPreventionByInf(thePlace);
    }
}
