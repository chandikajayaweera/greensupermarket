package com.greensupermarket.service;

import com.greensupermarket.dao.BrandDAO;
import com.greensupermarket.model.Brand;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class BrandService {

    private final BrandDAO brandDao;

    //Constructor
    public BrandService() {
        this.brandDao = new BrandDAO();
    }

    // Add a new brand
    public boolean addBrand(Brand brand, Part brandImage) {
        if (brandDao.getBrandByName(brand.getBrandName()) == null) {
            try {
                // Create the directory if it doesn't exist
                File directory = new File("src/main/webapp/resources/images/brand_images/" + brand.getBrandName() + "/");
                if (!directory.exists()) {
                    directory.mkdirs(); // Creates the necessary directories
                }

                // Get the file extension from the content type
                String fileExtension = brandImage.getContentType().split("/")[1];

                // Generate a unique file name for the brand image
                String newFileName = brand.getBrandName() + "." + fileExtension;

                // Save the file to the specified directory
                Path targetPath = directory.toPath().resolve(newFileName);
                try (InputStream inputStream = brandImage.getInputStream()) {
                    Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
                }

                // Set the URL to the path where the image is saved
                brand.setBrandLogoURL("/resources/images/brand_images/" + brand.getBrandName() + "/" + newFileName);

                // Add the brand to the database
                return brandDao.addBrand(brand);
            } catch (Exception e) {
                e.printStackTrace(); // Log the exception for debugging purposes
            }
        }
        return false;
    }

    // Update a brand
    public boolean updateBrand(Brand brand) {
        if (brandDao.getBrandByName(brand.getBrandName()) != null) {
            return brandDao.updateBrand(brand);
        }
        return false;
    }

    // Delete a brand
    public boolean deleteBrand(String brandName) {
        if (brandDao.getBrandByName(brandName) != null) {
            return brandDao.deleteBrand(brandName);
        }
        return false;
    }

    // Get all units
    public List<Brand> getAllBrands() {
        return brandDao.getAllBrands();
    }
}
