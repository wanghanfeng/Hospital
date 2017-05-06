package dao;

import model.Drugs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by AceCream on 2017/4/15.
 */
public interface DrugsDao {

    List<Drugs> getAllDrugs();


    Drugs loadDrug(@Param("drugcode") String drugcode);

    int addDrugs(Drugs drugs);

    List<Drugs> getDrugsByInf(@Param("theName")String theName);

    int updateDrugs(Drugs drugs);

    Drugs getDrugByName(@Param("drug")String drug);

}
