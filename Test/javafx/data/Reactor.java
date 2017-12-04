/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.data;

import db.DBData;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author user
 */
public class Reactor extends DBData {
    public StringProperty name;    
    public StringProperty technology;    
    public StringProperty origin;   
    public StringProperty reactorType;
    public DoubleProperty eCapacity;
    
    public Reactor()
    {
        name = new SimpleStringProperty();    
        technology  = new SimpleStringProperty("-");    
        origin  = new SimpleStringProperty("-");   
        reactorType  = new SimpleStringProperty("-");        
        eCapacity = new SimpleDoubleProperty();        
    }
}
