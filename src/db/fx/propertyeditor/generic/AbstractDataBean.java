/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.fx.propertyeditor.generic;

import javafx.beans.value.WritableValue;

/**
 *
 * @author user
 * @param <T>
 * @param <S>
 */
public interface AbstractDataBean <T, S extends WritableValue>
{
    public void setData(T value);
    public T getData();
    public S dataProperty();
   
    @Override
    public String toString();
    
}
