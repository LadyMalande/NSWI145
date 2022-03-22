package accountBalance;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.jws.WebService;

@WebService(targetNamespace = "http://accountBalance/", endpointInterface = "accountBalance.Account", portName = "AccountImplPort", serviceName = "AccountImplService")
public class AccountImpl implements Account {

	public double getAccountBalance(String accountName) {
		return readBalance(accountName);
		//createFile();
		//return 0.0;
		//return readBalanceFromFile(accountName);
	}
	
	private double readBalance(String accountName) {
		Map<String, Double> map = new HashMap<>();
	    map.put("miklosova", (double) 250);
	    map.put("dara", (double) 100);
	    return map.get(accountName);
	}
	
	private double readBalanceFromFile(String accountName){
		System.out.println(accountName);
        Map<String, Double> map = new HashMap<>();
        try {
            File myObj = new File("balances.txt");
            System.out.println(myObj.getPath());
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String str[] = line.split(";");
                for (int i = 1; i < str.length; i++) {
                    map.put(str[0], Double.parseDouble(str[1]));
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println(map.get(accountName));
        return map.get(accountName);
    }
	
	private void createFile() {
		try {


				String routePath = this.getClass().getResource(File.separator).getPath();
				System.out.println(routePath);

				/*for finding the path*/
				String newLine = System.getProperty("line.separator");
				
				/*file name is backup.txt and this is working.*/


		      File myObj = new File(routePath+File.separator+".."+File.separator+"balances.txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getAbsolutePath());
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}

}
