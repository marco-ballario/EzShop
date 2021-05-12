package it.polito.ezshop.model;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class Tools {
	
	@SuppressWarnings("unchecked")
	public ArrayList<Long> readLoyalty() {

		ArrayList<Long> cardsNo = null;
		try {
			String fName = "./src/main/java/it/polito/ezshop/cards.in/";
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

	public boolean updateLoyalty(ArrayList<Long> cardsNo) {
		String fName = "./src/main/java/it/polito/ezshop/cards.in/";
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

	@SuppressWarnings("unchecked")
	public HashMap<Long, Double> readCreditCard() {
		HashMap<Long, Double> cards = null;
		try {
			String fName = "./src/main/java/it/polito/ezshop/creditCards.db/";
			FileInputStream fileIn = new FileInputStream(fName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			cards = (HashMap<Long, Double>) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			System.out.println(i.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return cards;
	}

	public boolean updateCreditCards(HashMap<Long, Double> creditCards) {
		String fName = "./src/main/java/it/polito/ezshop/creditCards.db/";
		try {
			FileOutputStream fileOut = new FileOutputStream(fName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(creditCards);
			out.close();
			fileOut.close();
			return true;
		} catch (IOException i) {
			System.out.println(i.getMessage());
			return false;
		}
	}

	public boolean checkCardLuhn(String creditCardNo) {
		int nDigits = creditCardNo.length();

		int nSum = 0;
		boolean isSecond = false;
		for (int i = nDigits - 1; i >= 0; i--) {

			int d = creditCardNo.charAt(i) - '0';

			if (isSecond == true)
				d = d * 2;

			// We add two digits to handle
			// cases that make two digits
			// after doubling
			nSum += d / 10;
			nSum += d % 10;

			isSecond = !isSecond;
		}
		return (nSum % 10 == 0);
	}

	public boolean checkDigit(String code) {
		int tmp = 0, tot = 0;
		int len = code.length();
		int lastCodeDigit = Character.getNumericValue(code.charAt(len - 1)); // last code digit, used as check
																				// number
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



}
