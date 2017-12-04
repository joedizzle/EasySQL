/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx;

import static db.dsl.derby.DerbyFactory.create;
import static db.dsl.derby.DerbyFactory.field;
import db.dsl.derby.DerbyQuery;

/**
 *
 * @author user
 */
public class SMRQueryFactory 
{
    public static String getAll()
    {
        DerbyQuery query = create()
                        .selectAll()
                        .from("SMRDATABASE");
        return query.getSQLStatement();
    }
        
    public static DerbyQuery getType(String type)
    {
        DerbyQuery query = create().selectAll().from("SMRDATABASE");        
        switch(type)
        {
            case "All":
            {
                query = query.whereTrue();
                break;
            }
            case "LWR":
            {
                query = query.whereIn("reactorType ", "PWR", "Integral PWR", "BWR", "Pool-type");
                break;
            }
            case "Non-LWR":
            {
                query = query.whereNotIn("reactorType ", "PWR", "Integral PWR", "BWR", "Pool-type");
                break;
            }
            case "PWR": 
            {
                query = query.whereIn("reactorType ", "PWR", "Integral PWR");
                break;
            }
            case "BWR":
            {
                query = query.whereIn("reactorType ", "BWR");
                break;
            }
            case "HWR":
            {
                query = query.whereIn("reactorType ", "Heavy water");
                break;
            }
            case "GCR":
            {
                query = query.whereIn("reactorType ", "HTGR", "Modular HTGR", "Modular Helium Reactor", "Modular HTR");
                break;
            }
            case "SFR":
            {
                query = query.whereIn("reactorType ", "SFR");
                break;
            }
            case "MSR":
            {
                query = query.whereIn("reactorType ", "MSR", "LMFR");
                break;
            }
        }
        return query;
    }
    
    public static DerbyQuery getCountry(String country)
    {
        DerbyQuery query = create().selectAll().from("SMRDATABASE");        
        switch(country)
        {
            case "All":
            {
                query = query.whereTrue();
                break;
            }
            case "US" :
            {                         
                query = query.whereIn("origin", "USA", "United States of America", "Unitied States");
                break;
            }
            case "Canada":
            {                
                query = query.where(field("origin").equal(field("Canada")));
                break;
            }
            case "China":
            {
                query = query.where(field("origin").equal(field("China")));
                break;
            }            
            case "France":
            {
                query = query.where(field("origin").equal(field("France")));
                break;
            }            
            case "India":
            {
                query = query.where(field("origin").equal(field("India")));
                break;
            }
            case "Rep. of Korea":
            {
                query = query.where(field("origin").equal(field("Republic of Korea")));
                break;
            }
            case "Russia":
            {
                query = query.where(field("origin").equal(field("Russian Federation")));
                break;
            }
            case "EU":
            {                
                query = query.whereIn("origin", "United Kingdom", "Denmark", "France");
                break;
            }
            case "Other":
            {
                query = query.whereNotIn("origin", "Russian Federation", "Republic of Korea", "USA", "United States of America", "Canada", 
                        "China", "France", "India", "United Kingdom", "Denmark", "France", "United States");
                break;
            }
        }
        return query;
    }    
}
