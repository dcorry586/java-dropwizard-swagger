package org.example.api;

import org.example.cli.Product;
import org.example.cli.ProductRequest;
import org.example.client.*;
import org.example.core.ProductValidator;
import org.example.db.ProductDao;

import java.sql.SQLException;
import java.util.*;

public class ProductService {
    private ProductDao productDao = new ProductDao();
    private ProductValidator productValidator = new ProductValidator();

    public List<Product> getAllProducts() throws FailedToGetProductsException {
        List<Product> productList = null;
        try {
            productList = productDao.getAllProducts();
        } catch (SQLException e) {
            throw new FailedToGetProductsException();
        }
        try {
            Product product = productList.get(1000);
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("productList -> " + productList);
        return productList;
    }

    public Product getProductById(int id) throws FailedToGetProductsException, ProductDoesNotExistException {
        try {
            Product product = productDao.getProductById(id);

            if (product == null) {
                throw new ProductDoesNotExistException();
            }
            return product;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetProductsException();
        }
    }

    public int createProduct(ProductRequest product) throws FailedToCreateProductException, InvalidProductException {
        try {
            String validation = productValidator.isValidProduct(product);

            if (validation != null) {
                throw new InvalidProductException(validation);
            }

            int id = productDao.createProduct(product);

            if (id == -1) {
                throw new FailedToCreateProductException();
            }
            return id;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToCreateProductException();
        }
    }

    public void updateProduct(int id, ProductRequest product) throws InvalidProductException, ProductDoesNotExistException, FailedToUpdateProductException {
        try {
            String validation = productValidator.isValidProduct(product);

            if (validation != null) {
                throw new InvalidProductException(validation);
            }

            Product productToUpdate = productDao.getProductById(id);

            if (productToUpdate == null) {
                throw new ProductDoesNotExistException();
            }

            productDao.updateProduct(id, product);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToUpdateProductException(e.getMessage());
        }
    }

public void deleteProduct(int id) throws ProductDoesNotExistException, FailedToDeleteProductException {
       try {
           Product productToDelete = productDao.getProductById(id);

           if (productToDelete == null) {
               throw new ProductDoesNotExistException();
           }
      productDao.deleteProduct(id);

       } catch (SQLException e) {
           throw new FailedToDeleteProductException();
       }
}

}
