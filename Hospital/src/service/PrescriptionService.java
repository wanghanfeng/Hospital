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

}
