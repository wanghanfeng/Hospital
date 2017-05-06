package dao;

import model.Nurse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by AceCream on 2017/4/11.
 */
public interface NurseDao {

    //查找护士
    Nurse loadNurse(int theId);

    //添加护士
    int addNurse(Nurse nurse);

    //登录
    Nurse LoginNurse(@Param("nurId") int nurId,@Param("nurName") String nurName,@Param("nurPassword") String nurPassword);

    //更改
    int updateNurse(Nurse nurse);

    //查询所有护士
    List<Nurse> selectAllNurse();
}
