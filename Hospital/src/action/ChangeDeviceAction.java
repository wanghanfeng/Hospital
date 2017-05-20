package action;

import model.ChangeDevice;
import service.ChangeDeviceService;
import service.impl.ChangeDeviceServiceImpl;

import java.util.List;

/**
 * Created by lp on 2017/5/8.
 */
public class ChangeDeviceAction {
    private ChangeDeviceService changeDeviceService = new ChangeDeviceServiceImpl();

    //增加物资更换
    public int addChangeDevice(ChangeDevice changeDevice){
        return changeDeviceService.addChangeDevice(changeDevice);
    }

    //按字段查询物资更换
    public List<ChangeDevice> getChangeDeviceByInf(String theName) {
        return changeDeviceService.getChangeDeviceByInf(theName);
    }

    //查询所有物资更换
    public List<ChangeDevice> getAllChangeDevice() {
        return changeDeviceService.getAllChangeDevice();
    }
}
