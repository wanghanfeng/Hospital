package dao;

import model.Doctor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by AceCream on 2017/4/11.
 */
public interface DoctorDao {

    //添加医生
    int addDoctor(Doctor doctor);

    //查找医生
    Doctor loadDoctor(@Param("theId") int theId);

    //登录
    Doctor LoginDoctor(@Param("docId") int docId, @Param("docName") String docName,@Param("docPassword") String docPassword);

    //更改
    int updateDoctor(Doctor doctor);

    //查询所有医生
    List<Doctor> selectAllDoctor();

}
