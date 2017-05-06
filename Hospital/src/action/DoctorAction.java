package action;

import model.Doctor;
import service.DoctorService;
import service.impl.DoctorServiceImpl;

import javax.print.Doc;

/**
 * Created by AceCream on 2017/4/11.
 */
public class DoctorAction {

    DoctorService doctorService = new DoctorServiceImpl();


    //医生查重
    public Doctor loadDoctor(int theId) {
        Doctor doctor = doctorService.loadDoctor(theId);
        return doctor;
    }

    //医生添加
    public int addDoctor(Doctor doctor) {
        int i = doctorService.addDoctor(doctor);
        return i;
    }

    //登录
    public Doctor LoginDoctor(int docId, String docName, String docPassword) {
        Doctor doctor = doctorService.LoginDoctor(docId,docName,docPassword);
        return doctor;
    }

    public int updateDoctor(Doctor doctor) {
        int i = doctorService.updateDoctor(doctor);
        return i;
    }

}
