/*---Invoice Class
Author: Israel Musa--*/
import java.io.*;
import java.util.*;


public class Invoice{
  private String productName;
  private String clientID;
  private String waitList;
  private int quantity;
  private double unitPrice;

  //class constructors
  public Invoice(String clientID, String productName, int quantity, double unitPrice){
    setClientID(clientID);
    setProductName(productName);
    setQuantity(quantity);
    setUnitPrice(unitPrice);
  }
    //set productName
    public void setProductName(String product)
    {
      productName = product;
    }
    public String getProductName()
    {
      return productName;
    }

    //set accountBalance
    //AccountBalance is the unit prince * the amount in ordered cart
    public double accountBalance()
    {
      return getQuantity() * getUnitPrice();
    }

    //quantity
    public void setQuantity( int count )
    {
      quantity = ( count < 0 ) ? 0 : count;
    }
    public int getQuantity()
    {
      return quantity;
    }

    //pricePerItem
    public void setUnitPrice( double price )
    {
      unitPrice = ( price < 0.0 ) ? 0.0 : price;
    }
    public double getUnitPrice()
    {
      return unitPrice;
    }


    //set clientID
    public void setClientID(String client)
    {
      clientID = client;
    }
    public String getClientID()
    {
      return clientID;
    }

    //set waitList
    public void setWaitList(String list)
    {
      waitList = list;
    }
    public String getWaitList()
    {
      return waitList;
    }
}
