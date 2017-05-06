package service;

import model.ImportantPerson;

import java.util.List;

/**
 * Created by AceCream on 2017/4/15.
 */
public interface ImportantPersonService {

    List<ImportantPerson> getAllImportantPerson();

    int addImportantPerson(ImportantPerson importantPerson);

    List<ImportantPerson> getImportantPersonByInf(String theName, String theUnit);

}
