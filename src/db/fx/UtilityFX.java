/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.fx;

import db.DBData;
import db.DBDataMap;
import db.fx.propertyeditor.DBDataItem;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;
import javafx.beans.Observable;
import javafx.beans.value.WritableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 *
 * @author user
 */
public class UtilityFX 
{
    public static <T extends DBData> void initTableColumn(T parameter, TableView<T> tableView)
    {        
        ArrayList<String> columnList = parameter.getFieldNameList();        
        for(String columnName : columnList)
        {            
            TableColumn<T, WritableValue>  column = new TableColumn(columnName);             
            column.setCellValueFactory(p -> {
                return p.getValue().getObservableValueIgnoreCase(columnName);
            });            
            tableView.getColumns().add(column);
        }
    }
    
    
    
    public static ArrayList<DBDataItem> getDataItem(DBData data)
    {
        HashMap<String, WritableValue> map = DBDataMap.getDataMap(data);
        ArrayList<DBDataItem> dataItemList = new ArrayList<>();
        
        for(String string : map.keySet())
        {
            
        }
        
        
        return null;
    }
    public static void bindColumn(TableColumn column, String name)
    {
        column.setCellValueFactory(new PropertyValueFactory<>(name));
    }
        
    public static <T> Callback<T, Observable[]> extractor(T t, Observable... observables) 
    {       
        return (T tt) -> observables;
    }
    
    public static <T> FilteredList<T> getFilteredData(ObservableList<T> source, Predicate<? super T> predicate)
    {
        return new FilteredList<>(source, predicate);
    }
    
    public static <T> SortedList<T> getSortedData(FilteredList<T> filteredList)
    {
        return new SortedList(filteredList);
    }
    
    public static <T> void bind(SortedList<T> sortedList, TableView table)
    {
        sortedList.comparatorProperty().bind(table.comparatorProperty());
    }
}
