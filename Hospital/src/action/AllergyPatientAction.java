package action;

import model.AllergyPatient;
import service.AllergyPatientService;
import service.impl.AllergyPatientServiceImpl;

import java.util.List;

/**
 * Created by lp on 2017/5/8.
 */
public class AllergyPatientAction {
    private AllergyPatientService allergyPatientService = new AllergyPatientServiceImpl();

    public int addAllergyPatient(AllergyPatient allergyPatient) {
        return allergyPatientService.addAllergyPatient(allergyPatient);
    }

    public List<AllergyPatient> getAllAllertgyPatient() {
        return allergyPatientService.getAllAllergyPatient();
    }

    public List<AllergyPatient> getAllergyPatientByInf(String theName ,String theUnit) {
        return allergyPatientService.getAllergyPatientByInf(theName , theUnit);
    }
}
