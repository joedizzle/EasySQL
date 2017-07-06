package simple;

import db.DBAccess;
import db.DBData;
import db.DBQuery;
import db.dao.SQLStatement;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Test {
    public static void main(String... args)
    {
        DBAccess database = new DBAccess("jdbc:derby:C:\\Users\\user\\Desktop\\Reactor Database\\smr\\data;create=true", "tom", "secret");
        System.out.println("Is connection successfull   : " +database.isLoginSuccessful());
              
        //DBQuery query = database.getQuery("SMRDATABASE").readTable();
        //ArrayList<Parameter> parameters = query.getAllRows(Parameter.class);
        //System.out.println(parameters);
        //DBTableManager manager = database.getTableManager();
        //System.out.println(manager.allTablesString());
        
        SQLStatement parameter = new SQLStatement();
        parameter.selectFrom("SMRDATABASE")
                .where("name", "=", "mPower")
                .or("name", "=", "SMART")
                .or("name", "=", "NuScale");
        
        DBQuery query = database.getQuery().readTable(parameter);
        ArrayList<NameSMR> allData = query.getAllRows(NameSMR.class);
        System.out.println(allData);
                   
        database.disconnect();
        System.out.println("Is disconnection successfull: " +database.isDisconnectSuccessful());
    }
    
    public static class NameSMR extends DBData
    {
        public StringProperty NAME = new SimpleStringProperty();
        
        @Override
        public String toString()
        {
            return NAME.get();
        }
    }
}
