package service;

import model.SystemCon;

public interface SystemConService {

    //管理员登录
    SystemCon LoginSystemCon(int id,String name,String password);


    //查找管理员
    SystemCon loadSystemCon(int theId);

    //添加管理员
    int addSystemCon(SystemCon systemCon);

    //更改
    int updateSystemCon(SystemCon systemCon);
}
