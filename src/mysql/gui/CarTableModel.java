/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql.gui;

/**
 *
 * @author Jur Yel
 */
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import mysql.core.Car;

public class CarTableModel extends AbstractTableModel{
    public static final int OBJECT_COL = -1;
    private static final int CAR_NAME_COL = 0;
    private static final int OWNER_COL = 1;
    private static final int BRAND_COL = 2;
    private static final int CAR_TYPE_COL = 3;
    private static final int PLATE_NUMBER_COL = 4;
    
    private String[] columnNames = {"Car Name","Car Owner","Car Brand","Car Type","Plate Number"};
    private ArrayList<Car> cars;
    
    public CarTableModel(ArrayList<Car> list){
        this.cars = list;
    }
    
    @Override
    public int getColumnCount(){
        return columnNames.length;
    }
    
    @Override
    public int getRowCount(){
        return cars.size();
    }
    
    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }
    
    @Override
    public Object getValueAt(int row,int col){
        Car car = cars.get(row);
        
        switch(col){
            case CAR_NAME_COL:
                return car.getCarName();
            case OWNER_COL:
                return car.getCarOwner();
            case BRAND_COL:
                return car.getBrand();
            case CAR_TYPE_COL:
                return car.getCarType();
            case PLATE_NUMBER_COL:
                return car.getPlateNumber();
            case OBJECT_COL:
                return car;
            default:
                return car.getCarID();
        }
    }
    
    @Override
    public Class getColumnClass(int c){
        return getValueAt(0,c).getClass();
    }
}
