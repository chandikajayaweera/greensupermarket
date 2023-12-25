package com.greensupermarket.service;

import com.greensupermarket.model.Product;
import com.greensupermarket.dao.ProductDAO;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;

public class ProductService {

    private final ProductDAO productDao;

    //Constructor
    public ProductService() {
        this.productDao = new ProductDAO();
    }

    // Add a new product
    public boolean addProduct(Product product, Part productImage, HttpServletRequest request) {
        try {
            if (productDao.getProductByName(product.getProductName()) == null && productDao.getProductBySKU(product.getProductSKU()) == null) {
                String productName = product.getProductName();

                String webappPath = request.getServletContext().getRealPath("/");

                File directory = new File(webappPath
                        + "resources" + File.separator + "images" + File.separator
                        + "product_images" + File.separator + productName + File.separator);

                Files.createDirectories(directory.toPath());
                String originalFileName = productImage.getSubmittedFileName();
                String[] fileNameParts = originalFileName.split("\\.");

                if (fileNameParts.length < 2) {
                    throw new IllegalArgumentException("File has no extension");
                }

                String fileExtension = fileNameParts[fileNameParts.length - 1];
                String newFileName = productName + "." + fileExtension;
                Path targetPath = directory.toPath().resolve(newFileName);

                try (InputStream inputStream = productImage.getInputStream()) {
                    Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
                }

                String contextPath = request.getContextPath();
                String imageURL = contextPath + "/resources/images/product_images/" + productName + "/" + newFileName;
                product.setProductImageURL(imageURL);

                return productDao.addProduct(product);
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();

        }
        return false;
    }

    // Update a product
    public boolean updateProduct(Product product, Part newProductImage, HttpServletRequest request) {
        try{
            Product existingProduct = productDao.getProductById(product.getProductID());
            
            if (existingProduct == null){
                return false;
            }
        
            String webappPath = request.getServletContext().getRealPath("/");
            String oldProductImageURL = existingProduct.getProductImageURL();
            String oldImagePath = webappPath + oldProductImageURL.replace(request.getContextPath(), "");
            
            File oldImageFile = new File(oldImagePath);
            if (oldImageFile.exists()) {
                Files.delete(oldImageFile.toPath());
            }
            
            String productName = existingProduct.getProductName();
            File directory = new File(webappPath
                    + "resources" + File.separator + "images" + File.separator
                    + "product_images" + File.separator + productName + File.separator);
            
            Files.createDirectories(directory.toPath());
            
            String originalFileName = newProductImage.getSubmittedFileName();
            String[] fileNameParts = originalFileName.split("\\.");
            
            if (fileNameParts.length < 2) {
                throw new IllegalArgumentException("File has no extension");
            }
            
            String fileExtension = fileNameParts[fileNameParts.length - 1];
            String newFileName = productName + "." + fileExtension;
            Path targetPath = directory.toPath().resolve(newFileName);
            
            try (InputStream inputStream = newProductImage.getInputStream()) {
                Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
            }
            
            String contextPath = request.getContextPath();
            String newImageURL = contextPath + "/resources/images/product_images/" + productName + "/" + newFileName;
            product.setProductImageURL(newImageURL);
            
            return productDao.updateProduct(product);
        }
            
        catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //Update product stock
    public boolean updateProductStock(Product product){
        if(productDao.getProductById(product.getProductID()) != null){
            return productDao.updateProductStock(product);
        }
        return false;
    }
    
    //Update product unit price
    public boolean updateProductUnitPrice(Product product){
        if(productDao.getProductById(product.getProductID()) != null){
            return productDao.updateProductUnitPrice(product);
        }
        return false;
    }

    // Delete a product
    public boolean deleteProduct(int productID) {
        if (productDao.getProductById(productID) != null) {
            return productDao.deleteProduct(productID);
        }
        return false;
    }
    
    //Get product by ID
    public Product getProductByID(int productID){
        if(productDao.getProductById(productID) != null){
            return productDao.getProductById(productID);
        }
        return null;
    }
    
    // Get product by name
    public Product getProductByName(String productName){
        if(productDao.getProductByName(productName) != null){
            return productDao.getProductByName(productName);
        }
        return null;
    }
    
    // Get product by SKU
    public Product getProductBySKU(String productSKU){
        if(productDao.getProductBySKU(productSKU) == null){
            return productDao.getProductBySKU(productSKU);
        }
        return null;
    }
    
    // Get all products
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }
    
    // Get products by subcategory name
    public List<Product> getProductsBySubCategoryName(String subCategoryName){
        return productDao.getProductsBySubCategoryName(subCategoryName);
    }
}
