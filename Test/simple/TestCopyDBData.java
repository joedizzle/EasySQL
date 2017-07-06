/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple;

import db.DBData;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author user
 */
public class TestCopyDBData {
    public static void main(String... args)
    {
        SimpleParameter parameter1 = new SimpleParameter();
        parameter1.name.setValue("Joe");
        System.out.println(parameter1.name);
        
        SimpleParameter parameter2 = parameter1.copy();
        System.out.println(parameter2.name);
    }
    
    public static  class SimpleParameter extends DBData<SimpleParameter> {
        public StringProperty name;    
        public StringProperty technology;    
        public StringProperty origin;

        public SimpleParameter()
        {
            name = new SimpleStringProperty();    
            technology  = new SimpleStringProperty();    
            origin  = new SimpleStringProperty();
        }
}
}
