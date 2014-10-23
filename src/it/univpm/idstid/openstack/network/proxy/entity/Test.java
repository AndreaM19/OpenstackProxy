package it.univpm.idstid.openstack.network.proxy.entity;

import java.util.ArrayList;


public class Test {

	private TestData test;
	private ArrayList<TestData> tests;
	
	//Empty constructor
	public Test(){

	}
	
	//Constructor for a single Test entity
	public Test(TestData test) {
		this.test = test;
	}
	
	//Constructor for multiple Test entities
	public Test(ArrayList<TestData> tests) {
		this.tests = tests;
	}

	//---------------------------------------------------------------
	//Getter and Setter
	//---------------------------------------------------------------
	
	public TestData getTest() {
		return test;
	}

	public void setTest(TestData test) {
		this.test = test;
	}

	public ArrayList<TestData> getTests() {
		return tests;
	}

	public void setTests(ArrayList<TestData> tests) {
		this.tests = tests;
	}

}
