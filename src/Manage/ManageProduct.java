package Manage;

import File.FilePATH;
import Function.Create;
import Function.IOFile;
import Function.Input;
import Entiti.Product;
import Model.Validate;

import java.util.List;

public class ManageProduct {
    IOFile<Product> ioFile = new IOFile<>();
    Create create = new Create();
    Input input = new Input();

    public ManageProduct() {
    }

    public void displayProduct() {
        List<Product> products = ioFile.readDataFromFile(FilePATH.PRODUCT_PATH);
        System.out.println("------------------- Sản Phẩm -------------------");
        System.out.printf("%-5s%-15s%-15s%-15s%s",
                "ID", "Tên", "Số Lượng", "Đơn Giá", "\n");
        for (Product product : products) {
            System.out.printf("%-5s%-15s%-15s%-15s%s",
                    product.getId(), product.getName(), product.getQuantity(), product.getPrice(), "\n");
        }
    }

    public void addProduct() {
        List<Product> products = ioFile.readDataFromFile(FilePATH.PRODUCT_PATH);
        Product product = create.products();
        products.add(product);
        ioFile.writeDataToFile(products, FilePATH.PRODUCT_PATH);
    }

    public int findIndexByName(String nameProduct) {
        List<Product> products = ioFile.readDataFromFile(FilePATH.PRODUCT_PATH);
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().contains(nameProduct))
                return i;
        }
        return -1;
    }

    public void editProduct() {
        List<Product> products = ioFile.readDataFromFile(FilePATH.PRODUCT_PATH);
        String nameProduct = input.inputString("Tên Sản Phẩm", Validate.NAME_PRODUCT_PATTERN);
        int index = findIndexByName(nameProduct);
        if (index != -1) {
            Product newProduct = create.products();
            newProduct.setId(products.get(index).getId());
            Product.setCountIProduct(Product.getCountIProduct() - 1);
            products.set(index, newProduct);
        } else System.err.println("Không Tìm Thấy Tên!");
        ioFile.writeDataToFile(products, FilePATH.PRODUCT_PATH);
    }

    public void deleteProduct() {
        List<Product> products = ioFile.readDataFromFile(FilePATH.PRODUCT_PATH);
        String nameProduct = input.inputString("Tên Sản Phẩm", Validate.NAME_PRODUCT_PATTERN);
        int index = findIndexByName(nameProduct);
        if (index != -1) {
            products.remove(index);
        } else System.err.println("Không Tìm Thấy Tên!");
        ioFile.writeDataToFile(products, FilePATH.PRODUCT_PATH);
    }


    public Product findProductByName(String nameProduct) {
        List<Product> products = ioFile.readDataFromFile(FilePATH.PRODUCT_PATH);
        int index = findIndexByName(nameProduct);
        if (index != -1) return products.get(index);
        return null;
    }
}