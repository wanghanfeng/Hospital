package dao;

import model.ImportantPerson;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by AceCream on 2017/4/15.
 */
public interface ImportantPersonDao {

    List<ImportantPerson> getAllImportantPerson();

    int addImportantPerson(ImportantPerson importantPerson);

    List<ImportantPerson> getImportantPersonByInf(@Param("theName") String theName, @Param("theUnit") String theUnit);

}
