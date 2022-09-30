package service.impl;

import model.Product;
import model.Store;
import org.junit.jupiter.api.Test;
import service.TestProductImplDB;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductsDBImplTest {

    @Test
    void getProducts() {
        TestProductImplDB productImplDB = new TestProductImplDB();

        StoreDBImpl storeDB = new StoreDBImpl();
        Store store = storeDB.getStoreProducts(productImplDB.getProductDB());

        List<Product> products = store.getProducts();
        assertEquals(products.get(0).getName(), "Rice");
    }
}