package com.inventory.main;

import java.util.Scanner;
import com.inventory.model.Category;
import com.inventory.service.CategoryService;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import com.inventory.model.Category;
import com.inventory.model.Product;
import com.inventory.service.CategoryService;
import com.inventory.service.ProductService;
import com.inventory.model.Supplier;
import com.inventory.service.SupplierService;
import java.sql.SQLException;
import java.util.List;
import com.inventory.model.Customer;
import com.inventory.service.CustomerService;
import com.inventory.model.Purchase;
import com.inventory.model.PurchaseItem;
import com.inventory.model.Sale;
import com.inventory.model.SaleItem;
import com.inventory.model.Supplier;
import com.inventory.model.Customer;
import com.inventory.service.InventoryService;
import com.inventory.service.ProductService;
import com.inventory.service.PurchaseService;
import com.inventory.service.SaleService;
import com.inventory.service.SupplierService;
import com.inventory.service.CustomerService;
import com.inventory.service.ReportsService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventorySalesApplicationMain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n===== INVENTORY & SALES MANAGEMENT SYSTEM =====");
            System.out.println("1. Category Management");
            System.out.println("2. Product Management");
            System.out.println("3. Supplier Management");
            System.out.println("4. Customer Management");
            System.out.println("5. Purchase Management");
            System.out.println("6. Sales Management");
            System.out.println("7. Inventory");
            System.out.println("8. Reports");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
	            case 1:
	                categoryMenu(scanner);
	                break;
	            case 2:
	                productMenu(scanner);
	                break;
	            case 3:
	                supplierMenu(scanner);
	                break;
	            case 4:
	                customerMenu(scanner);
	                break;
	            case 5:
	                purchaseMenu(scanner);
	                break;
	            case 6:
	                saleMenu(scanner);
	                break;
	            case 7:
	                inventoryMenu(scanner);
	                break;
	            case 8:
	                reportsMenu(scanner);
	                break;
                case 9:
                    System.out.println("Thank you for using the system.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 9);

        scanner.close();
    }
    
    private static void categoryMenu(Scanner scanner) {

        CategoryService categoryService = new CategoryService();

        int choice;

        do {
            System.out.println("\n===== CATEGORY MANAGEMENT =====");
            System.out.println("1. Add Category");
            System.out.println("2. View All Categories");
            System.out.println("3. Find Category By ID");
            System.out.println("4. Find Category By Name");
            System.out.println("5. Update Category");
            System.out.println("6. Delete Category");
            System.out.println("7. Back");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (choice) {

                    case 1:
                        System.out.print("Enter category name: ");
                        String categoryName = scanner.nextLine();

                        System.out.print("Enter description: ");
                        String description = scanner.nextLine();

                        Category category =
                                new Category(categoryName, description);

                        categoryService.addCategory(category);

                        System.out.println("Category added successfully.");
                        break;

                    case 2:
                        List<Category> categories =
                                categoryService.findAllCategories();

                        for (Category c : categories) {
                            System.out.println(c);
                        }
                        break;

                    case 3:
                        System.out.print("Enter category ID: ");
                        int categoryId = scanner.nextInt();

                        Category foundCategory =
                                categoryService.findById(categoryId);

                        System.out.println(foundCategory);
                        break;

                    case 4:
                        System.out.print("Enter category name: ");
                        String searchName = scanner.nextLine();

                        Category categoryByName =
                                categoryService.findByName(searchName);

                        System.out.println(categoryByName);
                        break;

                    case 5:
                        System.out.print("Enter category ID: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter new category name: ");
                        String newName = scanner.nextLine();

                        System.out.print("Enter new description: ");
                        String newDescription = scanner.nextLine();

                        Category updateCategory =
                                new Category(
                                        updateId,
                                        newName,
                                        newDescription
                                );

                        categoryService.updateCategory(updateCategory);

                        System.out.println("Category updated successfully.");
                        break;

                    case 6:
                        System.out.print("Enter category ID: ");
                        int deleteId = scanner.nextInt();
                        categoryService.deleteCategory(deleteId);
                        System.out.println("Category deleted successfully.");
                        break;
                    case 7:
                        System.out.println("Returning to main menu...");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }

        } while (choice != 7);
    }
    
    private static void productMenu(Scanner scanner) {

        ProductService productService = new ProductService();
        CategoryService categoryService = new CategoryService();

        int choice;

        do {
            System.out.println("\n===== PRODUCT MANAGEMENT =====");
            System.out.println("1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Find Product By ID");
            System.out.println("4. Find Product By Name");
            System.out.println("5. Update Product");
            System.out.println("6. Delete Product");
            System.out.println("7. Back");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (choice) {

                    case 1:
                        System.out.print("Enter product name: ");
                        String productName = scanner.nextLine();
                        System.out.print("Enter category ID: ");
                        int categoryId = scanner.nextInt();
                        Category category = categoryService.findById(categoryId);

                        if (category == null) {
                            System.out.println("Category not found.");
                            break;
                        }

                        System.out.print("Enter purchase price: ");
                        double purchasePrice = scanner.nextDouble();

                        System.out.print("Enter selling price: ");
                        double sellingPrice = scanner.nextDouble();

                        System.out.print("Enter stock quantity: ");
                        int stockQuantity = scanner.nextInt();

                        System.out.print("Enter minimum stock level: ");
                        int minimumStockLevel = scanner.nextInt();

                        Product product = new Product(
                                productName,
                                category,
                                purchasePrice,
                                sellingPrice,
                                stockQuantity,
                                minimumStockLevel
                        );

                        productService.addProduct(product);

                        System.out.println("Product added successfully.");
                        break;

                    case 2:
                        List<Product> products =
                                productService.findAllProducts();

                        for (Product p : products) {
                            System.out.println(p);
                        }
                        break;

                    case 3:
                        System.out.print("Enter product ID: ");
                        int productId = scanner.nextInt();

                        Product foundProduct =
                                productService.findById(productId);

                        System.out.println(foundProduct);
                        break;

                    case 4:
                        System.out.print("Enter product name: ");
                        String searchName = scanner.nextLine();

                        Product productByName =
                                productService.findByName(searchName);

                        System.out.println(productByName);
                        break;

                    case 5:
                        System.out.print("Enter product ID: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter product name: ");
                        String updateName = scanner.nextLine();

                        System.out.print("Enter category ID: ");
                        int updateCategoryId = scanner.nextInt();

                        Category updateCategory =
                                categoryService.findById(updateCategoryId);

                        if (updateCategory == null) {
                            System.out.println("Category not found.");
                            break;
                        }

                        System.out.print("Enter purchase price: ");
                        double updatePurchasePrice = scanner.nextDouble();

                        System.out.print("Enter selling price: ");
                        double updateSellingPrice = scanner.nextDouble();

                        System.out.print("Enter stock quantity: ");
                        int updateStockQuantity = scanner.nextInt();

                        System.out.print("Enter minimum stock level: ");
                        int updateMinimumStockLevel = scanner.nextInt();

                        Product updateProduct = new Product(
                                updateId,
                                updateName,
                                updateCategory,
                                updatePurchasePrice,
                                updateSellingPrice,
                                updateStockQuantity,
                                updateMinimumStockLevel
                        );

                        productService.updateProduct(updateProduct);

                        System.out.println("Product updated successfully.");
                        break;

                    case 6:
                        System.out.print("Enter product ID: ");
                        int deleteId = scanner.nextInt();

                        productService.deleteProduct(deleteId);

                        System.out.println("Product deleted successfully.");
                        break;

                    case 7:
                        System.out.println("Returning to main menu...");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }

        } while (choice != 7);
    }
    
    private static void supplierMenu(Scanner scanner) {

        SupplierService supplierService = new SupplierService();

        int choice;

        do {
            System.out.println("\n===== SUPPLIER MANAGEMENT =====");
            System.out.println("1. Add Supplier");
            System.out.println("2. View All Suppliers");
            System.out.println("3. Find Supplier By ID");
            System.out.println("4. Find Supplier By Name");
            System.out.println("5. Update Supplier");
            System.out.println("6. Delete Supplier");
            System.out.println("7. Back");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (choice) {

                    case 1:
                        System.out.print("Enter supplier name: ");
                        String supplierName = scanner.nextLine();

                        System.out.print("Enter company name: ");
                        String companyName = scanner.nextLine();

                        System.out.print("Enter phone: ");
                        String phone = scanner.nextLine();

                        System.out.print("Enter email: ");
                        String email = scanner.nextLine();

                        System.out.print("Enter address: ");
                        String address = scanner.nextLine();

                        Supplier supplier = new Supplier(
                                supplierName,
                                companyName,
                                phone,
                                email,
                                address
                        );

                        supplierService.addSupplier(supplier);

                        System.out.println("Supplier added successfully.");
                        break;

                    case 2:
                        List<Supplier> suppliers =
                                supplierService.findAllSuppliers();

                        for (Supplier s : suppliers) {
                            System.out.println(s);
                        }
                        break;

                    case 3:
                        System.out.print("Enter supplier ID: ");
                        int supplierId = scanner.nextInt();

                        Supplier foundSupplier =
                                supplierService.findById(supplierId);

                        System.out.println(foundSupplier);
                        break;

                    case 4:
                        System.out.print("Enter supplier name: ");
                        String searchName = scanner.nextLine();

                        Supplier supplierByName =
                                supplierService.findByName(searchName);

                        System.out.println(supplierByName);
                        break;

                    case 5:
                        System.out.print("Enter supplier ID: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter supplier name: ");
                        String updateName = scanner.nextLine();

                        System.out.print("Enter company name: ");
                        String updateCompanyName = scanner.nextLine();

                        System.out.print("Enter phone: ");
                        String updatePhone = scanner.nextLine();

                        System.out.print("Enter email: ");
                        String updateEmail = scanner.nextLine();

                        System.out.print("Enter address: ");
                        String updateAddress = scanner.nextLine();

                        Supplier updateSupplier = new Supplier(
                                updateId,
                                updateName,
                                updateCompanyName,
                                updatePhone,
                                updateEmail,
                                updateAddress
                        );

                        supplierService.updateSupplier(updateSupplier);

                        System.out.println("Supplier updated successfully.");
                        break;

                    case 6:
                        System.out.print("Enter supplier ID: ");
                        int deleteId = scanner.nextInt();

                        supplierService.deleteSupplier(deleteId);

                        System.out.println("Supplier deleted successfully.");
                        break;

                    case 7:
                        System.out.println("Returning to main menu...");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }

        } while (choice != 7);
    }
    
    private static void customerMenu(Scanner scanner) {

        CustomerService customerService = new CustomerService();

        int choice;

        do {
            System.out.println("\n===== CUSTOMER MANAGEMENT =====");
            System.out.println("1. Add Customer");
            System.out.println("2. View All Customers");
            System.out.println("3. Find Customer By ID");
            System.out.println("4. Find Customer By Name");
            System.out.println("5. Update Customer");
            System.out.println("6. Delete Customer");
            System.out.println("7. Back");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (choice) {

                    case 1:
                        System.out.print("Enter customer name: ");
                        String customerName = scanner.nextLine();

                        System.out.print("Enter phone: ");
                        String phone = scanner.nextLine();

                        System.out.print("Enter email: ");
                        String email = scanner.nextLine();

                        System.out.print("Enter address: ");
                        String address = scanner.nextLine();

                        Customer customer = new Customer(
                                customerName,
                                phone,
                                email,
                                address
                        );

                        customerService.addCustomer(customer);

                        System.out.println("Customer added successfully.");
                        break;

                    case 2:
                        List<Customer> customers =
                                customerService.findAllCustomers();

                        for (Customer c : customers) {
                            System.out.println(c);
                        }
                        break;

                    case 3:
                        System.out.print("Enter customer ID: ");
                        int customerId = scanner.nextInt();

                        Customer foundCustomer =
                                customerService.findById(customerId);

                        System.out.println(foundCustomer);
                        break;

                    case 4:
                        System.out.print("Enter customer name: ");
                        String searchName = scanner.nextLine();

                        Customer customerByName =
                                customerService.findByName(searchName);

                        System.out.println(customerByName);
                        break;

                    case 5:
                        System.out.print("Enter customer ID: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter customer name: ");
                        String updateName = scanner.nextLine();

                        System.out.print("Enter phone: ");
                        String updatePhone = scanner.nextLine();

                        System.out.print("Enter email: ");
                        String updateEmail = scanner.nextLine();

                        System.out.print("Enter address: ");
                        String updateAddress = scanner.nextLine();

                        Customer updateCustomer = new Customer(
                                updateId,
                                updateName,
                                updatePhone,
                                updateEmail,
                                updateAddress
                        );

                        customerService.updateCustomer(updateCustomer);

                        System.out.println("Customer updated successfully.");
                        break;

                    case 6:
                        System.out.print("Enter customer ID: ");
                        int deleteId = scanner.nextInt();

                        customerService.deleteCustomer(deleteId);

                        System.out.println("Customer deleted successfully.");
                        break;

                    case 7:
                        System.out.println("Returning to main menu...");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }

        } while (choice != 7);
    }
    
    private static void purchaseMenu(Scanner scanner) {

        PurchaseService purchaseService = new PurchaseService();
        SupplierService supplierService = new SupplierService();
        ProductService productService = new ProductService();

        int choice;

        do {
            System.out.println("\n===== PURCHASE MANAGEMENT =====");
            System.out.println("1. Create Purchase");
            System.out.println("2. Back");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (choice) {

                    case 1:
                        System.out.print("Enter supplier ID: ");
                        int supplierId = scanner.nextInt();
                        scanner.nextLine();

                        Supplier supplier =
                                supplierService.findById(supplierId);

                        if (supplier == null) {
                            System.out.println("Supplier not found.");
                            break;
                        }

                        System.out.print("Enter purchase date (YYYY-MM-DD): ");
                        String purchaseDate = scanner.nextLine();

                        List<PurchaseItem> purchaseItems =
                                new ArrayList<>();

                        String addMore;

                        do {
                            System.out.print("Enter product ID: ");
                            int productId = scanner.nextInt();

                            Product product =
                                    productService.findById(productId);

                            if (product == null) {
                                System.out.println("Product not found.");
                                break;
                            }

                            System.out.print("Enter quantity: ");
                            int quantity = scanner.nextInt();

                            System.out.print("Enter unit price: ");
                            double unitPrice = scanner.nextDouble();
                            scanner.nextLine();

                            PurchaseItem purchaseItem =
                                    new PurchaseItem(
                                            0,
                                            product,
                                            quantity,
                                            unitPrice,
                                            0
                                    );

                            purchaseItems.add(purchaseItem);

                            System.out.print("Add another product? (yes/no): ");
                            addMore = scanner.nextLine();

                        } while (addMore.equalsIgnoreCase("yes"));

                        if (purchaseItems.isEmpty()) {
                            System.out.println("No purchase items added.");
                            break;
                        }

                        Purchase purchase = new Purchase(
                                0,
                                supplier,
                                purchaseDate,
                                0
                        );

                        purchase.setPurchaseItems(purchaseItems);

                        purchaseService.addPurchase(purchase);

                        System.out.println("Purchase added successfully.");
                        System.out.println("Total Amount: " +
                                purchase.getTotalAmount());

                        break;

                    case 2:
                        System.out.println("Returning to main menu...");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }

        } while (choice != 2);
    }
    
    private static void saleMenu(Scanner scanner) {

        SaleService saleService = new SaleService();
        CustomerService customerService = new CustomerService();
        ProductService productService = new ProductService();

        int choice;

        do {
            System.out.println("\n===== SALES MANAGEMENT =====");
            System.out.println("1. Create Sale");
            System.out.println("2. Back");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (choice) {

                    case 1:
                        System.out.print("Enter customer ID: ");
                        int customerId = scanner.nextInt();
                        scanner.nextLine();

                        Customer customer =
                                customerService.findById(customerId);

                        if (customer == null) {
                            System.out.println("Customer not found.");
                            break;
                        }

                        System.out.print("Enter sale date (YYYY-MM-DD): ");
                        String saleDate = scanner.nextLine();

                        List<SaleItem> saleItems =
                                new ArrayList<>();

                        String addMore;

                        do {
                            System.out.print("Enter product ID: ");
                            int productId = scanner.nextInt();

                            Product product =
                                    productService.findById(productId);

                            if (product == null) {
                                System.out.println("Product not found.");
                                break;
                            }

                            System.out.println(
                                    "Available stock: "
                                    + product.getStockQuantity()
                            );

                            System.out.print("Enter quantity: ");
                            int quantity = scanner.nextInt();

                            System.out.print("Enter unit price: ");
                            double unitPrice = scanner.nextDouble();
                            scanner.nextLine();

                            SaleItem saleItem =
                                    new SaleItem(
                                            0,
                                            product,
                                            quantity,
                                            unitPrice,
                                            0
                                    );

                            saleItems.add(saleItem);

                            System.out.print("Add another product? (yes/no): ");
                            addMore = scanner.nextLine();

                        } while (addMore.equalsIgnoreCase("yes"));

                        if (saleItems.isEmpty()) {
                            System.out.println("No sale items added.");
                            break;
                        }

                        Sale sale = new Sale(
                                0,
                                customer,
                                saleDate,
                                0
                        );

                        sale.setSaleItems(saleItems);

                        saleService.addSale(sale);

                        System.out.println("Sale added successfully.");
                        System.out.println("Total Amount: " +
                                sale.getTotalAmount());

                        break;

                    case 2:
                        System.out.println("Returning to main menu...");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }

        } while (choice != 2);
    }
    
    private static void inventoryMenu(Scanner scanner) {

        InventoryService inventoryService =
                new InventoryService();

        int choice;

        do {
            System.out.println("\n===== INVENTORY =====");
            System.out.println("1. View All Inventory");
            System.out.println("2. View Low Stock Products");
            System.out.println("3. Check Product Stock");
            System.out.println("4. Back");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            try {

                switch (choice) {

                    case 1:
                        List<Product> products =
                                inventoryService.findAllInventory();

                        for (Product product : products) {
                            System.out.println(
                                    product.getProductId()
                                    + " | "
                                    + product.getProductName()
                                    + " | Stock: "
                                    + product.getStockQuantity()
                                    + " | Minimum Level: "
                                    + product.getMinimumStockLevel()
                            );
                        }

                        break;

                    case 2:
                        List<Product> lowStockProducts =
                                inventoryService.findLowStockProducts();

                        if (lowStockProducts.isEmpty()) {
                            System.out.println("No low-stock products.");
                        } else {

                            for (Product product : lowStockProducts) {
                                System.out.println(
                                        product.getProductId()
                                        + " | "
                                        + product.getProductName()
                                        + " | Stock: "
                                        + product.getStockQuantity()
                                        + " | Minimum Level: "
                                        + product.getMinimumStockLevel()
                                );
                            }
                        }

                        break;

                    case 3:
                        System.out.print("Enter product ID: ");
                        int productId = scanner.nextInt();

                        int stock =
                                inventoryService.getStockQuantity(productId);

                        if (stock == -1) {
                            System.out.println("Product not found.");
                        } else {
                            System.out.println(
                                    "Current stock: " + stock
                            );
                        }

                        break;

                    case 4:
                        System.out.println("Returning to main menu...");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }

        } while (choice != 4);
    }
    
    private static void reportsMenu(Scanner scanner) {

        ReportsService reportsService =
                new ReportsService();

        int choice;

        do {
            System.out.println("\n===== REPORTS =====");
            System.out.println("1. Daily Sales");
            System.out.println("2. Monthly Sales");
            System.out.println("3. Total Sales");
            System.out.println("4. Total Purchases");
            System.out.println("5. Best Selling Products");
            System.out.println("6. Back");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (choice) {

                    case 1:
                        System.out.print("Enter date (YYYY-MM-DD): ");
                        String date = scanner.nextLine();

                        double dailySales =
                                reportsService.getDailySales(date);

                        System.out.println(
                                "Daily Sales: ₹" + dailySales
                        );

                        break;

                    case 2:
                        System.out.print("Enter year: ");
                        int year = scanner.nextInt();

                        System.out.print("Enter month: ");
                        int month = scanner.nextInt();

                        double monthlySales =
                                reportsService.getMonthlySales(
                                        year,
                                        month
                                );

                        System.out.println(
                                "Monthly Sales: ₹" + monthlySales
                        );

                        break;

                    case 3:
                        double totalSales =
                                reportsService.getTotalSales();

                        System.out.println(
                                "Total Sales: ₹" + totalSales
                        );

                        break;

                    case 4:
                        double totalPurchases =
                                reportsService.getTotalPurchases();

                        System.out.println(
                                "Total Purchases: ₹" + totalPurchases
                        );

                        break;

                    case 5:
                        reportsService.getBestSellingProducts();
                        break;

                    case 6:
                        System.out.println("Returning to main menu...");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }

        } while (choice != 6);
    }
}