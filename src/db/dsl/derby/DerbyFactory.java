/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dsl.derby;

/**
 *
 * @author user
 */
public class DerbyFactory {
    public static DerbyQuery create()
    {
        return new DerbyQuery("");
    }
    
    public static DerbyField field(String string)
    {
        return new DerbyField(string);
    }
    
    public static DerbyField field(Number number)
    {
        return new DerbyField(number);
    }
}
