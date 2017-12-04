/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.dataeditor;

import db.DBData;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 *
 * @author user
 */
public class Parameter extends DBData
{
    public StringProperty name;    
    public StringProperty technology;    
    public StringProperty origin;   
    public StringProperty reactorType;
    public DoubleProperty eCapacity;
    public DoubleProperty tCapacity;
    public DoubleProperty capacityFactor;
    public IntegerProperty life;
    public DoubleProperty footprint;
    public StringProperty coolant;    
    public StringProperty moderator;
    public StringProperty primaryCirculation;
    public DoubleProperty systemPressure;
    public DoubleProperty coreInletTemperature;
    public DoubleProperty coreExitTemperature;
    public StringProperty rcontrolMechanism;
    public DoubleProperty calandriaHeight;
    public DoubleProperty calandriaDiameter;
    public DoubleProperty calandriaWeight;
    public StringProperty rcsConfiguration;
    public StringProperty powconversionProcess;
    public StringProperty fuelType;
    public StringProperty assemblyArray;
    public DoubleProperty faLength;
    public IntegerProperty numFA;
    public DoubleProperty faEnrichment;
    public DoubleProperty faBurnup;
    public DoubleProperty faCycle;
    public StringProperty cogenerationCapability;
    public StringProperty approachESS;
    public IntegerProperty numTrains;
    public IntegerProperty daysRefuellingOutage;
    public StringProperty distinguishingFeatures;
    public IntegerProperty modules;
    public IntegerProperty constructionDuration;
    public DoubleProperty seismicDesign;
    public DoubleProperty cdf;
    public StringProperty designStatus;
    public StringProperty reference;
    
    public Parameter()
    {
        name = new SimpleStringProperty();    
        technology  = new SimpleStringProperty();    
        origin  = new SimpleStringProperty();   
        reactorType  = new SimpleStringProperty();        
        eCapacity = new SimpleDoubleProperty();
        tCapacity = new SimpleDoubleProperty();
        capacityFactor = new SimpleDoubleProperty();
        life = new SimpleIntegerProperty();
        footprint = new SimpleDoubleProperty();
        coolant  = new SimpleStringProperty();    
        moderator = new SimpleStringProperty();
        primaryCirculation = new SimpleStringProperty();
        systemPressure = new SimpleDoubleProperty();
        coreInletTemperature = new SimpleDoubleProperty();
        coreExitTemperature = new SimpleDoubleProperty();
        rcontrolMechanism = new SimpleStringProperty();
        calandriaHeight = new SimpleDoubleProperty();
        calandriaDiameter = new SimpleDoubleProperty();
        calandriaWeight = new SimpleDoubleProperty();
        rcsConfiguration = new SimpleStringProperty();
        powconversionProcess = new SimpleStringProperty();
        fuelType = new SimpleStringProperty();
        assemblyArray = new SimpleStringProperty();
        faLength = new SimpleDoubleProperty();
        numFA = new SimpleIntegerProperty();
        faEnrichment = new SimpleDoubleProperty();
        faBurnup = new SimpleDoubleProperty();
        faCycle = new SimpleDoubleProperty();
        cogenerationCapability = new SimpleStringProperty();
        approachESS = new SimpleStringProperty();
        numTrains = new SimpleIntegerProperty();
        daysRefuellingOutage = new SimpleIntegerProperty();
        distinguishingFeatures = new SimpleStringProperty();
        modules = new SimpleIntegerProperty();
        constructionDuration = new SimpleIntegerProperty();
        seismicDesign = new SimpleDoubleProperty();
        cdf = new SimpleDoubleProperty();
        designStatus = new SimpleStringProperty();
        reference = new SimpleStringProperty();
    }    
}
