package dao;

import model.NurseRecords;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by AceCream on 2017/4/14.
 */
public interface NurseRecordsDao {

    int addNurseRecords(NurseRecords nurseRecords);

    List<NurseRecords> getAllNurseRecords();

    List<NurseRecords> getNurseRecordsByInf(@Param("theName") String theName,@Param("theSex") String theSex,@Param("theUnit") String theUnit);

    List<NurseRecords> getNurseRecordsByTime(@Param("startTime") String startTime, @Param("endTime") String endTime);

    int updatePrescription(NurseRecords nurseRecords);

    int deleteNurseRecords(NurseRecords nurseRecords);
}
