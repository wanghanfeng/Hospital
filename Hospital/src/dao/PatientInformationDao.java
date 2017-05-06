package dao;

import model.PatientInformation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by AceCream on 2017/4/12.
 */
public interface PatientInformationDao {

    List<PatientInformation> getAllPatient();

    int addPatientInformation(PatientInformation patientInformation);

    List<PatientInformation> getPatientsByInf(@Param("theName") String theName, @Param("theSex") String theSex, @Param("theUnit") String theUnit);
}
