package it.polito.ezshop.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Tools {

	@SuppressWarnings("unchecked")
	public ArrayList<Long> readLoyalty(String fName) {

		ArrayList<Long> cardsNo = null;
		try {
			FileInputStream fileIn = new FileInputStream(fName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			cardsNo = (ArrayList<Long>) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			System.out.println(i.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return cardsNo;
	}

	public boolean updateLoyalty(ArrayList<Long> cardsNo, String fName) {

		try {
			FileOutputStream fileOut = new FileOutputStream(fName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(cardsNo);
			out.close();
			fileOut.close();
			return true;
		} catch (IOException i) {
			System.out.println(i.getMessage());
			return false;
		}
	}

	public boolean checkCardLuhn(String creditCardNo) {
		try {
			Long.parseLong(creditCardNo);
		} catch (NumberFormatException e) {
			return false;
		}
		if( creditCardNo.length()!=16)
			return false;
		
		
		int nSum = 0;
		boolean isSecond = false;
		for (int i = creditCardNo.length() - 1; i >= 0; i--) {

			int d = creditCardNo.charAt(i) - '0';
			Integer.parseInt(creditCardNo.substring(i, i + 1));
			if (isSecond) {
				d *= 2;
				if(d > 9)
					d = (d % 10) +1;
			}
			
			nSum += d;

			isSecond = !isSecond;
		}
		
		return (nSum % 10 == 0);
	}

	public boolean checkDigit(String code) {
		int tmp = 0, tot = 0;
		int len = code.length();
		try {
			Long.parseLong(code);
		} catch (NumberFormatException e) {
			return false;
		}
		int lastCodeDigit = Character.getNumericValue(code.charAt(len - 1)); // last code digit, used as check number

		if (code.length() < 12 || code.length() > 14)
			return false;

		// multiplication and sum of all the digit except the last one
		if (len == 12 || len == 14) {
			for (int i = len - 2; i >= 0; i--) {
				tmp = 1;
				if (i % 2 == 0) { // if the position is even the number must be multiplied by 3, if odd by 1
					tmp = Character.getNumericValue(code.charAt(i));
					tot += tmp * 3;
				} else {
					tmp = Character.getNumericValue(code.charAt(i));
					tot += tmp;
				}
			}
		}

		if (len == 13) {
			for (int i = len - 2; i >= 0; i--) {
				tmp = 1;
				if (i % 2 != 0) { // if the position is odd the number must be multiplied by 3, if even by 1
					tmp = Character.getNumericValue(code.charAt(i));
					tot += tmp * 3;
				} else {
					tmp = Character.getNumericValue(code.charAt(i));
					tot += tmp;
				}
			}
		}

		// Subtract the sum from nearest equal or higher multiple of ten
		int i = 1, mul = 0, flag = 0;
		while (flag == 0) {
			mul = 10 * i;
			i++;
			if (tot <= mul)
				flag = 1;
		}

		// calculate the last digit, which is the check digit of the barCode
		int check = mul - tot;

		// if check digit is equal to the last digit of the code, that's valid otherwise
		// not
		if (check == lastCodeDigit)
			return true;
		else
			return false;
	}

	public boolean paymentCreditCards(String creditCard, double price, String fileName)
			throws IOException {
		boolean found = false;
		boolean updated = false;

		File originalFile = new File(fileName);
		BufferedReader br = new BufferedReader(new FileReader(originalFile));

		// Construct the new file that will later be renamed to the original
		// filename.
		File tempFile = new File("tmp.txt");
		PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

		String line = null;
		// Read from the original file and write to the new
		// unless content matches data to be removed.
		Long number = (long) 0;
		Double amount = (double) 0;
		while ((line = br.readLine()) != null) {
			if (line.charAt(0) != '#') {
				String[] buff = line.split(";");
				try{
					number = Long.parseLong(buff[0]);
					amount = Double.parseDouble(buff[1]);
					if (number == Long.parseLong(creditCard)) {
						found = true;
						if ((price >= 0 && price <= amount) || price<0) {
							amount = amount - price;
							updated = true;
							
							
						}

					}
				}
				catch(NumberFormatException e) {
					updated = false;
				}
				
			}

			pw.println(line);
			pw.flush();
		}
		pw.close();
		br.close();

		// Delete the original file
		if (!originalFile.delete()) {
			return false;
		}

		// Rename the new file to the filename the original file had.
		if (!tempFile.renameTo(originalFile))
			return false;
		
		return (found && updated);
	}

}
