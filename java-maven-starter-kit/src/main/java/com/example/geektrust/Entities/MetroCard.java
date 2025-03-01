package com.example.geektrust.Entities;

public class MetroCard {

	private final String metroCardNumber;
	private final int balance;
	
	public MetroCard(String metroCardNumber, int balance) {
		this.metroCardNumber = metroCardNumber;
		this.balance = balance;
	}

	public String getMetroCardNumber() {
		return metroCardNumber;
	}

	public int getBalance() {
		return balance;
	}

	@Override
	public String toString() {
		return "MetroCard [metroCardNumber=" + metroCardNumber + ", balance=" + balance + "]";
	}
	
	
	
}
