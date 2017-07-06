/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple;

import db.dao.SQLStatement;

/**
 *
 * @author user
 */
public class TestParameter {
    public static void main(String... args)
    {        
        SQLStatement parameter = new SQLStatement();
        parameter.selectFrom("SMRDATABASE")
                .where("name", "=", "USA")
                .or("technology", "=", "United States of America");
        System.out.println(parameter);
           
    }
}

        