package action;

import model.Prescription;
import model.Prevention;
import service.PrescriptionService;
import service.impl.PrescriptionServiceImpl;

import java.util.List;

/**
 * Created by AceCream on 2017/4/13.
 * 处方
 */
public class PrescriptionAction {

    PrescriptionService  prescriptionService = new PrescriptionServiceImpl();

    public int addPrescription(Prescription prescription) {
        int i = prescriptionService.addPrescription(prescription);
        return i;
    }

    public List<Prescription> getAllPrescription() {
        List<Prescription> list = prescriptionService.getAllPrescription();
        return list;
    }

    public List<Prescription> getPrescriptionByInf(String theName, String theUnit) {
        List<Prescription> list = prescriptionService.getPrescriptionByInf(theName,theUnit);
        return list;
    }

    public List<Prescription> getPrescriptionByTime(String startTime, String endTime) {
        return prescriptionService.getPrescriptionByTime(startTime , endTime);
    }
    public int updatePrescription(Prescription prescription) {
        return prescriptionService.updatePrescription(prescription);
    }

    public int deletePrescription(Prescription prescription) {
        return prescriptionService.deletePrescription(prescription);
    }
}
