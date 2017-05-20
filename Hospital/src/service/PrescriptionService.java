package service;

import model.Prescription;

import java.util.List;

/**
 * Created by AceCream on 2017/4/13.
 */
public interface PrescriptionService {

    int addPrescription(Prescription prescription);

    List<Prescription> getAllPrescription();

    List<Prescription> getPrescriptionByInf(String theName, String theUnit);

    List<Prescription> getPrescriptionByTime(String startTime, String endTime);

    int updatePrescription(Prescription prescription);

    int deletePrescription(Prescription prescription);

}
