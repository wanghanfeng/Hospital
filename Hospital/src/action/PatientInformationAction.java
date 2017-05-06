package action;

import model.PatientInformation;
import service.PatientInformationService;
import service.impl.PatientInformationServiceImpl;

import java.util.List;

/**
 * Created by AceCream on 2017/4/12.
 * 病人信息操作
 */
public class PatientInformationAction {

    PatientInformationService patientInformationService = new PatientInformationServiceImpl();

    public List<PatientInformation> getAllPatient() {
        List<PatientInformation> patientInformationList = patientInformationService.getAllPatient();
        return patientInformationList;
    }


    public int addPatientInformation(PatientInformation patientInformation) {
        int i = patientInformationService.addPatientInformation(patientInformation);
        return i;
    }

    public List<PatientInformation> getPatientsByInf(String theName, String theSex, String theUnit) {
        List<PatientInformation> list = patientInformationService.getPatientsByInf(theName,theSex,theUnit);
        return list;
    }
}
