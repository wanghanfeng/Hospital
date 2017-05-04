package service;

import model.Nurse;

import java.util.List;

/**
 * Created by AceCream on 2017/4/11.
 */
public interface NurseService {

    Nurse loadNurse(int theId);

    int addNurse(Nurse nurse);

    Nurse LoginNurse(int nurId, String nurName, String nurPassword);

    int updateNurse(Nurse nurse);

    List<Nurse> selectAllNurse();
}
