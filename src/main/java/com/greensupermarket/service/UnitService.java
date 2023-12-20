package com.greensupermarket.service;

import com.greensupermarket.model.Unit;
import com.greensupermarket.dao.UnitDAO;
import java.util.List;

public class UnitService {
    
    private final UnitDAO unitDao;
    
    //Constructor
    public UnitService(){
        this.unitDao = new UnitDAO();
    }
    
    //Add a new Unit
    public boolean addUnit(Unit unit){
        if(unitDao.getUnitByName(unit.getUnitName()) == null && unitDao.getUnitByUnitAbbreviation(unit.getUnitAbbreviation()) == null){
            return unitDao.addUnit(unit);
        }
        return false; // Handle error
    }
    
     //Delete a new Unit
    public boolean deleteUnit(String unitName){
        if(unitDao.getUnitByName(unitName) != null){
            return unitDao.deleteUnit(unitName);
        }
        return false; // Handle error
    }   
    
    //Get all units
    public List<Unit> getAllUnits(){
        return unitDao.getAllUnits();
    }
}