package ethanlo;
import java.util.Random;

import warehouseInventory.warehouse.Product;

import java.io.*;
import java.util.*;

public class SupplierCollection {
	
	ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
	public ArrayList<Product> suppliertInventory = new ArrayList<Product>();
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
		/* for(Supplier Supplier: supplierList) {
			System.out.println(supplierList);
		} */
		
		System.out.println(supplierList);
	}
	
}