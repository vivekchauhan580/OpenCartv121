package Utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//dataprovider1
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"\\testData\\LoginData.xlsx";
		
		ExcelUtility xlutil= new ExcelUtility(path); //created an object for excel utility
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][]=new String[totalrows][totalcols]; //created for two dimension array which can store the rows and cols
		
		for(int i =1 ; i<=totalrows; i++)//1 read the data from the xl storing in 2d array
		{
			for(int j=0; j<totalcols; j++) //2 i is row j i col
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j); //1,0
				System.out.println("Data" + logindata[i-1][j]);
				
			}
		}
		return logindata; //return the 2d array
		
	}
	
	

}
