package service;

import model.NurseRecords;

import java.util.List;

/**
 * Created by AceCream on 2017/4/14.
 */
public interface NurseRecordsService {

    int addNurseRecords(NurseRecords nurseRecords);


    List<NurseRecords> getAllNurseRecords();

    List<NurseRecords> getNurseRecordsByInf(String theName, String theSex, String theUnit);

}
