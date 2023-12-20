package com.greensupermarket.service;

import com.greensupermarket.model.Category;
import com.greensupermarket.dao.CategoryDAO;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;

public class CategoryService {

    private final CategoryDAO categoryDao;

    //Constructor
    public CategoryService() {
        this.categoryDao = new CategoryDAO();
    }

    //Add a new Category
    public boolean addCategory(Category category, Part categoryImage, HttpServletRequest request) {

        try {
            if (categoryDao.getCategoryByName(category.getCategoryName()) == null) {
                String categoryName = category.getCategoryName();

                // Get the real path to the webapp directory
                String webappPath = request.getServletContext().getRealPath("/");

                // Construct the file path within the webapp directory
                File directory = new File(webappPath
                        + "resources" + File.separator + "images" + File.separator
                        + "category_images" + File.separator + categoryName + File.separator);

                Files.createDirectories(directory.toPath()); // Creates the necessary directories

                String originalFileName = categoryImage.getSubmittedFileName();
                String[] fileNameParts = originalFileName.split("\\.");

                // Check if the file has an extension
                if (fileNameParts.length < 2) {
                    throw new IllegalArgumentException("File has no extension");
                }

                String fileExtension = fileNameParts[fileNameParts.length - 1];
                String newFileName = categoryName + "." + fileExtension;
                Path targetPath = directory.toPath().resolve(newFileName);

                try (InputStream inputStream = categoryImage.getInputStream()) {
                    Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
                }

                // Construct the image URL using the servlet context path
                String contextPath = request.getContextPath();
                String imageURL = contextPath + "/resources/images/category_images/" + categoryName + "/" + newFileName;
                category.setCategoryImageURL(imageURL);

                return categoryDao.addCategory(category);
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
        return false;
    }

    // Update category
    public boolean updateCategory(Category category, Part newCategoryImage, HttpServletRequest request) {

        try {
            // Check if the category exists
            Category existingCategory = categoryDao.getCategoryByName(category.getCategoryName());
            if (existingCategory == null) {
                // Category not found
                return false;
            }

            // Get the real path to the webapp directory
            String webappPath = request.getServletContext().getRealPath("/");

            // Construct the file path for the old category image within the webapp directory
            String oldCategoryImageURL = existingCategory.getCategoryImageURL();
            String oldImagePath = webappPath + oldCategoryImageURL.replace(request.getContextPath(), "");

            // Delete the old category image
            File oldImageFile = new File(oldImagePath);
            if (oldImageFile.exists()) {
                Files.delete(oldImageFile.toPath());
            }

            // Construct the file path for the new category image within the webapp directory
            String categoryName = existingCategory.getCategoryName();
            File directory = new File(webappPath
                    + "resources" + File.separator + "images" + File.separator
                    + "category_images" + File.separator + categoryName + File.separator);

            Files.createDirectories(directory.toPath()); // Creates the necessary directories

            String originalFileName = newCategoryImage.getSubmittedFileName();
            String[] fileNameParts = originalFileName.split("\\.");

            // Check if the file has an extension
            if (fileNameParts.length < 2) {
                throw new IllegalArgumentException("File has no extension");
            }

            String fileExtension = fileNameParts[fileNameParts.length - 1];
            String newFileName = categoryName + "." + fileExtension;
            Path targetPath = directory.toPath().resolve(newFileName);

            try (InputStream inputStream = newCategoryImage.getInputStream()) {
                Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
            }

            // Construct the new image URL using the servlet context path
            String contextPath = request.getContextPath();
            String newImageURL = contextPath + "/resources/images/category_images/" + categoryName + "/" + newFileName;
            category.setCategoryImageURL(newImageURL);

            // Update the category with the new image URL
            return categoryDao.updateCategory(category);
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
        return false;
    }

    // Delete a category
    public boolean deleteCategory(String categoryName) {
        if (categoryDao.getCategoryByName(categoryName) != null) {
            return categoryDao.deleteCategory(categoryName);
        }
        return false; // Handle error
    }

    // Get category by name
    public Category getCategoryByName(String categoryName) {
        if (categoryDao.getCategoryByName(categoryName) != null) {
            return categoryDao.getCategoryByName(categoryName);
        }
        return null;
    }

    // Get all categories
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    } // Handle error

}
