package ethanlo;
import java.util.Random;

import warehouseInventory.warehouse.ProductList;

import java.io.*;
import java.util.*;

public class SupplierCollection {
	
	ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
	public ArrayList<ProductList> suppliertInventory = new ArrayList<ProductList>();
	static SupplierCollection singleton;
	
	public void addSupplier(String name, String phoneNumber, String address) {
		Random rand = new Random();
		int id = rand.nextInt(1000);
		Supplier newSupplier = new Supplier(name, phoneNumber, address, id);
		supplierList.add(newSupplier);
	}
	
	public static SupplierCollection instance() {
		if(singleton == null)
			singleton = new SupplierCollection();
		return singleton;
	}
	
	public void printSupplierList() {
		int temp = suppliertInventory.size();

		System.out.println("Current Inventory : ");

		for (int i = 0; i < temp; i++)
		{
			ProductList supplierProduct = suppliertInventory.get(i);
			String productName = supplierProduct.getProductID();
			System.out.println((i+1) + productName);
		}
		
		System.out.println(supplierList);
	}
	
}