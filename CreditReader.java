package dataStructure;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CreditReader {

	public static void main(String[] args) {
		List<String[]> ccList = new ArrayList<String[]>();
		String fileName = "\\\\SIMONSERVER\\c$\\Users\\Administrator\\Desktop\\documents\\AMU\\Eclipse Directory\\Files\\CreditFile.CSV";
		String data;
		double balance = 0;
		DecimalFormat df = new DecimalFormat("#.##");
		
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while((data = br.readLine()) != null) {
				String[] rowData = data.split(",");
				ccList.add(rowData);
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		for(String[] ccInfo : ccList ) {
			System.out.print("[ ");
			for(String payFee : ccInfo) {
				if (payFee.equalsIgnoreCase("CREDIT") || payFee.equalsIgnoreCase("FEE")) {
					balance += Double.valueOf(ccInfo[3]);
				}
				else if(payFee.equalsIgnoreCase("DEBIT")) {
					balance -= Double.valueOf(ccInfo[3]);
				}
				System.out.print(payFee+" ");
			}
			System.out.println(" ] $" + df.format(balance));
		}//end For each loop
		
		if(balance > 0) {
			balance *= 1.10;
			System.out.println("WARNING: A 10% charge has been added to you account\nRemaining Balance: $" + df.format(balance));
		}
		else if(balance == 0 ) {
			System.out.println("Thank you for your payment. Remaining Balance: $" + df.format(balance));
		}
		else if(balance < 0) {
			System.out.println("Thank you for your payment. Remaining Balance: (OVERPAYMENT) $" + df.format(balance) );
		}
		
		
	}//end main

}//end class
