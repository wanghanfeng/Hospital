package action;

import model.NurseRecords;
import service.NurseRecordsService;
import service.impl.NurseRecordsServiceImpl;

import java.util.List;

/**
 * Created by AceCream on 2017/4/14.
 */
public class NurseRecordsAction {

    NurseRecordsService nurseRecordsService = new NurseRecordsServiceImpl();

    public int addNurseRecords(NurseRecords nurseRecords) {
        int i = nurseRecordsService.addNurseRecords(nurseRecords);
        return i;
    }


    public List<NurseRecords> getAllNurseRecords() {
        List<NurseRecords> list = nurseRecordsService.getAllNurseRecords();
        return list;
    }

    public List<NurseRecords> getNurseRecordsByInf(String theName, String theSex, String theUnit) {
        List<NurseRecords> list = nurseRecordsService.getNurseRecordsByInf(theName,theSex,theUnit);
        return list;
    }
}
