package com.greensupermarket.controller;

import com.greensupermarket.service.CategoryService;
import com.greensupermarket.service.SubCategoryService;
import com.greensupermarket.service.ProductService;

import com.greensupermarket.model.SubCategory;
import com.greensupermarket.model.Category;
import com.greensupermarket.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FrontendController", urlPatterns = {"/controller"})
public class FrontendController extends HttpServlet {

    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;
    private final ProductService productService;

    private final Category category;
    private final SubCategory subCategory;

    //Constructor
    public FrontendController() {
        this.categoryService = new CategoryService();
        this.subCategoryService = new SubCategoryService();
        this.productService = new ProductService();

        this.category = new Category();
        this.subCategory = new SubCategory();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("session", true);

        if (request.getParameter("action") == null) {
            index(session, request, response);
            return;
        }

        String action = request.getParameter("action");
        switch (action) {
            case "index":
                index(session, request, response);
                return;
            case "categories":
                categories(session, request, response);
                return;
            case "productdetails":
                productdetails(session, request, response);
                return;

        }
    }

    private void index(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session.setAttribute("categories", categoryService.getAllCategories());
        response.sendRedirect("index.jsp");
        return;
    }

    private void categories(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session.removeAttribute("products");
        session.removeAttribute("subcategoryname");
        session.removeAttribute("subcategories");

        session.setAttribute("categories", categoryService.getAllCategories());

        if ((request.getParameter("categoryname") == null && request.getParameter("subcategoryname") == null) || "all".equals(request.getParameter("categoryname"))) {
            session.setAttribute("categoryname", "All Products");
            session.setAttribute("products", productService.getAllProducts());
            response.sendRedirect("products.jsp");
            return;
        }

        if (request.getParameter("subcategoryname") != null) {
            session.setAttribute("subcategoryname", request.getParameter("subcategoryname"));
            String categoryName = subCategoryService.getCategoryNameBySubCategoryName(request.getParameter("subcategoryname"));
            session.setAttribute("categoryname", categoryName);
            session.setAttribute("subcategories", subCategoryService.getSubCategoriesByCategoryName(categoryName));
            session.setAttribute("products", productService.getProductsBySubCategoryName(request.getParameter("subcategoryname")));
            response.sendRedirect("products.jsp");
            return;
        }

        if (request.getParameter("categoryname") != null) {
            List<SubCategory> subCategories = subCategoryService.getSubCategoriesByCategoryName(request.getParameter("categoryname"));
            List<Product> allProducts = new ArrayList<>();

            for (SubCategory subCategory : subCategories) {
                String subcategoryname = subCategory.getSubCategoryName();
                List<Product> productsForSubCategory = productService.getProductsBySubCategoryName(subcategoryname);
                allProducts.addAll(productsForSubCategory);
            }
            session.setAttribute("categoryname", request.getParameter("categoryname"));
            session.setAttribute("subcategories", subCategoryService.getSubCategoriesByCategoryName(request.getParameter("categoryname")));
            session.setAttribute("products", allProducts);
            response.sendRedirect("products.jsp");
            return;
        }
    }

    private void productdetails(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session.setAttribute("product", productService.getProductByID(Integer.parseInt(request.getParameter("productid"))));
        response.sendRedirect("productdetails.jsp");
        return;
    }
}
