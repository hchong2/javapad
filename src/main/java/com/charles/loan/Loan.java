package com.charles.loan;

public class Loan {
	
	public static void main(String [] args){
		
		double principle = 12500.00;
		double interest = 0.085;
		double period = 120;
		
		System.out.println(payment(principle, interest, period));
		
	}
	
	public static double payment(double principle, double interest, double period){
		double payment = 0.0;
		
		double numerator = principle*(interest/12);
		double denominator = (1 - Math.pow((1 + (interest/12)), period*-1));
		
		return numerator/denominator;
	}
}
