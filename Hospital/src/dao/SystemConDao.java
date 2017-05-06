package dao;

import model.SystemCon;
import org.apache.ibatis.annotations.Param;

public interface SystemConDao {

    //登录验证
    SystemCon LoginSystemCon(@Param("id")int id,@Param("name")String name,@Param("password")String password);

    //验证管理员
    SystemCon loadSystemCon(int theId);

    //添加系统管理员
    int addSystemCon(SystemCon systemCon);

    //更改
    int updateSystemCon(SystemCon systemCon);
}
