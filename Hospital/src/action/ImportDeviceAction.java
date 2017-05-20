package action;

import model.ImportDevice;
import service.ImportDeviceService;
import service.impl.ImportDeviceServiceImpl;

import java.util.List;

/**
 * Created by lp on 2017/5/8.
 */
public class ImportDeviceAction {

    private ImportDeviceService importDeviceService = new ImportDeviceServiceImpl();

    public List<ImportDevice> getAllImportDevice() {
        return importDeviceService.getAllImportDevice();
    }

    public int addImportDevice(ImportDevice importDevice) {
        return importDeviceService.addImportDevice(importDevice);
    }

    public List<ImportDevice> getImportDeviceByInf(String theName) {
        return importDeviceService.getImportDeviceByInf(theName);
    }

    public List<ImportDevice> getImportDeviceByTime(String startTime , String endTime) {
        return importDeviceService.getImportDeviceByTime(startTime , endTime);
    }

    public int updateImportDevice(ImportDevice importDevice) {
        return importDeviceService.updateImportDevice(importDevice);
    }

    public int deleteImportDevice(ImportDevice importDevice) {
        return importDeviceService.deleteImportDevice(importDevice);
    }

}
