package com.greensupermarket.service;

import com.greensupermarket.model.SubCategory;
import com.greensupermarket.dao.SubCategoryDAO;
import com.greensupermarket.dao.CategoryDAO;
import java.util.List;

public class SubCategoryService {
    
    private final SubCategoryDAO subCategoryDao;
    private final CategoryDAO categoryDao;

    // Constructor
    public SubCategoryService() {
        this.subCategoryDao = new SubCategoryDAO();
        this.categoryDao = new CategoryDAO();
    }
    
    // Add new SubCategory
    public boolean addSubCategory(SubCategory subCategory){
        if(subCategoryDao.getSubCategoryByName(subCategory.getSubCategoryName()) == null && categoryDao.getCategoryByName(subCategory.getCategoryName()) != null){
            return subCategoryDao.addSubCategory(subCategory);
        }
        return false; // Handle error        
    }
    
    // Delete a SubCategory
    public boolean deleteSubCategory(String subCategoryName){
        if(subCategoryDao.getSubCategoryByName(subCategoryName) != null){
            return subCategoryDao.deleteSubCategory(subCategoryName);
        }
        return false;
    }
    
    // Update subcategory
    public boolean updateSubCategory(SubCategory subCategory){
        if(subCategoryDao.getSubCategoryByName(subCategory.getSubCategoryName()) != null){
            return subCategoryDao.updateSubCategory(subCategory);
        }
        return false;
    }
    
    // Get all subcategories
    public List<SubCategory> getAllSubCategories(){
        return subCategoryDao.getAllSubCategories();
    }
    
    

}
