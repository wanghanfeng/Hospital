package service;

import model.PatientInformation;

import java.util.List;

/**
 * Created by AceCream on 2017/4/12.
 */
public interface PatientInformationService {

    List<PatientInformation> getAllPatient();

    int addPatientInformation(PatientInformation patientInformation);

    //根据信息获取病人
    List<PatientInformation> getPatientsByInf(String theName, String theSex, String theUnit);

}
