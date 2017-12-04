/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.fx;

import db.DBData;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 *
 * @author user
 * 
 * TableView[S] where is S is the type of the objects contained within the TableView items list
 * TableColumn[S, T] where S is like above definition but T is the type of the content in all cells in this TableColumn
 * 
 * TableColumn::setCellValueFactory(Callback<CellDataFeatures[S,T], ObservableValue[T]> value)
 * 
 * Cell Value Factory : it is like a "toString()" of only part of the row item for that related cell
 * Cell Factory : it is a renderer of the cell from the cell item
 *                Default behavior is 
 *                setText(cell.item.toString()) if the cell item is not a Node, 
 *                setGraphic((Node)cell.item) otherwise. 
 * 
 *                set this property if the cell is supposed to support editing 
 *                      OR if you want more graphics (controls) other than default Label
 * 
 * Any "ObjectProperty" e.g. FloatProperty extends WritableValue, ReadOnlyProperty and ObservableValue properties
 * 
 * 
 * Binding here correlates synchronization of change of display of a value of different nodes based on change of a shared observable
 * 
 */
public class UtilityFX 
{
    //Binding of columns based on their names
    public static void bindColumn(TableColumn column, String name)
    {
        column.setCellValueFactory(new PropertyValueFactory<>(name));
    }
    
    //Binding and add column to tableview
    public static<S extends DBData> void bindColumn(TableView<S> tableView, TableColumn<S, ObservableValue> column, String name)
    {        
        column.setCellValueFactory(p -> p.getValue().getObservableValueIgnoreCase(name));                
        tableView.getColumns().add(column);
    }
        
    //Bind the following column names (javafx will use the reflection to get the actual column objects)
    public static void bindColumn(TableView view, String... names)
    {
        ObservableList<TableColumn> columns = view.getColumns();
        if(columns.size() != names.length) return;
        
        for(int i = 0; i<columns.size(); i++)       
            columns.get(i).setCellValueFactory(new PropertyValueFactory<>(names[i]));        
    }
    
    public static<S extends DBData> void exportTable(TableView<S> tableView, String directoryName, String fileName)
    {
        Path path = Paths.get(directoryName, fileName);
        exportTable(tableView, path);
    }
    
    public static<S extends DBData> void exportTable(TableView<S> tableView, Path path)
    {
        
    }
    
    //Create columns of table based on parameter
    public static <S extends DBData> void createColumns(S parameter, TableView<S> tableView)
    {        
        ArrayList<String> columnNames = parameter.getFieldNameList();  
        columnNames.forEach(columnName -> {
            TableColumn<S, ObservableValue>  column = new TableColumn(columnName);    
            bindColumn(tableView, column, columnName);
        });       
    }
      
    //Binding list view
    public static <T> Callback<T, Observable[]> bindListView(T t, Observable... observables) 
    {       
        return (T tt) -> observables;
    }  
    
    //Convert ArrayList<T> to ObservableArrayList
    public static <S extends DBData> ObservableList<S> getObservableArrayList(ArrayList<S> arrayList)
    {
        return FXCollections.observableArrayList(arrayList);
    }
}
