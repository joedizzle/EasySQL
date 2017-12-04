/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple;

import db.DBData;
import static db.dsl.derby.DerbyFactory.create;
import static db.dsl.derby.DerbyFactory.field;
import db.dsl.derby.DerbyQuery;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author user
 */
public class TestCopyDBData {
    public static void main(String... args)
    {
        SimpleParameter parameter = new SimpleParameter();
        DerbyQuery query = create()
                .update("SMRDATABASE")
                .set(parameter)
                .where(field("name").equal(field("josto")));
        System.out.println(query.getSQLStatement());
               
    }
    
    public static  class SimpleParameter extends DBData<SimpleParameter> {
        public StringProperty name;    
        public StringProperty technology;    
        public StringProperty origin;

        public SimpleParameter()
        {
            name = new SimpleStringProperty("Josto");    
            technology  = new SimpleStringProperty("Kubafu");    
            origin  = new SimpleStringProperty("Lol");
        }
}
}
