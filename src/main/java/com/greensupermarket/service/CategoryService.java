package com.greensupermarket.service;

import com.greensupermarket.model.Category;
import com.greensupermarket.dao.CategoryDAO;
import java.util.List;

public class CategoryService {
    
    private final CategoryDAO categoryDao;
    
    //Constructor
    public CategoryService(){
        this.categoryDao = new CategoryDAO();
    }
    
    //Add a new Category
    public boolean addCategory(Category category){
        if(categoryDao.getCategoryByName(category.getCategoryName()) == null){
            return categoryDao.addCategory(category);
        }
        return false; // Handle error
    }
    
    // Update category
    public boolean updateCategory(Category category){
        if(categoryDao.getCategoryByName(category.getCategoryName()) != null){
            return categoryDao.updateCategory(category);
        }
        return false;
    }
    
    
    // Delete a category
    public boolean deleteCategory(String categoryName){
        if(categoryDao.getCategoryByName(categoryName) != null){
            return categoryDao.deleteCategory(categoryName);
        }
        return false; // Handle error
    }
    
    // Get category by name
    public Category getCategoryByName(String categoryName){
        if(categoryDao.getCategoryByName(categoryName) != null){
            return categoryDao.getCategoryByName(categoryName);
        }
        return null;
    }
    
    // Get all categories
    public List<Category> getAllCategories(){
        return categoryDao.getAllCategories();
    } // Handle error
    
    
}
