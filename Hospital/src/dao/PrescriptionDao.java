package dao;

import model.Prescription;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by AceCream on 2017/4/13.
 * 处方
 */
public interface PrescriptionDao {

    List<Prescription> getAllPrescription();

    int addPrescription(Prescription prescription);

    List<Prescription> getPrescriptionByInf(@Param("theName") String theName, @Param("theUnit") String theUnit);

}
