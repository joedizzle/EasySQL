/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsl.derby;

import static db.dsl.derby.DerbyFactory.create;
import static db.dsl.derby.DerbyFactory.field;

/**
 *
 * @author user
 */
public class Test {
    public static void main(String... args)
    {
        String query = create()
                .selectAll()
                .from("SMR")
                .whereNot(field("ECAPACITY").equal(field(100)))
                .or(field("ORIGIN").equal(field("China")))
                .getSQLStatement();
        System.out.println(query);        
    }
}
