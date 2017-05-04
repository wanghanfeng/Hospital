package service;

import model.Doctor;

import java.util.List;

/**
 * Created by AceCream on 2017/4/11.
 */
public interface DoctorService {

    //添加医生
    int addDoctor(Doctor doctor);

    //查找医生
    Doctor loadDoctor(int theId);

    //医生登录
    Doctor LoginDoctor(int docId, String docName, String docPassword);

    //更改
    int updateDoctor(Doctor doctor);

    //查询所有医生
    List<Doctor> selectAllDoctor();

}
