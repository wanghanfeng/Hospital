package action;

import model.Nurse;
import service.NurseService;
import service.impl.NurseServiceImpl;

/**
 * Created by AceCream on 2017/4/11.
 */
public class NurseAction{

    NurseService nurseService = new NurseServiceImpl();

    public Nurse loadNurse(int theId) {
        Nurse nurse = nurseService.loadNurse(theId);
        return nurse;
    }

    public int addNurse(Nurse nurse) {
        int i = nurseService.addNurse(nurse);
        return i;
    }

    public Nurse LoginNurse(int nurId, String nurName, String nurPassword) {
        Nurse nurse = nurseService.LoginNurse(nurId,nurName,nurPassword);
        return nurse;
    }

    public int updateNurse(Nurse nurse) {
        int i = nurseService.updateNurse(nurse);
        return i;
    }
}
