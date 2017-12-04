package simple;

import db.DBConnection;
import db.DBData;
import static db.dsl.derby.DerbyFactory.create;
import static db.dsl.derby.DerbyFactory.field;
import db.dsl.derby.DerbyQuery;
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
        DBConnection connection = new DBConnection("jdbc:derby:C:\\Users\\user\\Desktop\\Reactor Database\\smr\\data;create=true", "tom", "secret");
             
        //DBQuery query = database.getQuery("SMRDATABASE").readTable();
        //ArrayList<Parameter> parameters = query.getAllRows(Parameter.class);
        //System.out.println(parameters);
        //DBTableManager manager = database.getTableManager();
        //System.out.println(manager.allTablesString());
        
        DerbyQuery statement = create()
                .selectAll()
                .from("SMRDATABASE")
                .where(field("name").equal(field("mPower")))
                .or(field("name").equal(field("SMART")))
                .or(field("name").equal(field("NuScale")));        
        
        ArrayList<NameSMR> allData = connection.executeAndFetch(statement.getSQLStatement(), NameSMR.class);
        connection.disconnect();
        System.out.println(allData);                   
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
