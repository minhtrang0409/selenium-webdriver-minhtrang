package TestNG;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_08_Depend {

  @Test
  public void Product_01_Create_Product() {
	  System.out.println("Test case Create");
	  Assert.assertTrue(true);
  }
  @Test(dependsOnMethods = "Product_01_Create_Product")
  public void Product_02_Read_Product() {
	  System.out.println("Test case Read");
	  Assert.assertTrue(false);
  }
  @Test(dependsOnMethods = "Product_02_Read_Product")
  public void Product_03_Update_Product() {
	  System.out.println("Test case Update");
  }

  @Test(dependsOnMethods = "Product_03_Update_Product")
  public void Product_04_Delete_Product() {
	  System.out.println("Delete");
	  
  }



}
