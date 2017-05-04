package action;

import model.ImportantPerson;
import service.ImportantPersonService;
import service.impl.ImportantPersonServiceImpl;

import java.util.List;

/**
 * Created by AceCream on 2017/4/15.
 */
public class ImportantPersonAction {

    ImportantPersonService importantPersonService = new ImportantPersonServiceImpl();

    public List<ImportantPerson> getAllImportantPerson() {
        List<ImportantPerson> list = importantPersonService.getAllImportantPerson();
        return list;
    }


    public int addImportantPerson(ImportantPerson importantPerson) {
        int i = importantPersonService.addImportantPerson(importantPerson);
        return i;
    }

    public List<ImportantPerson> getImportantPersonByInf(String theName, String theUnit) {
        List<ImportantPerson> list = importantPersonService.getImportantPersonByInf(theName,theUnit);
        return list;
    }
}
