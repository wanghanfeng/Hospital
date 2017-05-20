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

    public List<NurseRecords> getNurseRecordsByTime(String startTime, String endTime) {
        return nurseRecordsService.getNurseRecordsByTime(startTime , endTime);
    }

    public int updatePrescription(NurseRecords nurseRecords) {
        return nurseRecordsService.updatePrescription(nurseRecords);
    }

    public int deleteNurseRecords(NurseRecords nurseRecords) {
        return nurseRecordsService.deleteNurseRecords(nurseRecords);
    }
}
