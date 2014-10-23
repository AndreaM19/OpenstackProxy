package it.univpm.idstid.openstack.network.proxy.entity;

public class TestData {
	
	private String testID="1nj6n49ksl";
	private String testName="This is a test ID";
	private boolean testFlag=true;

	public TestData(){
	
	}
	
	public TestData(String testID, String testName, boolean testFlag){
		this.testID=testID;
		this.testName=testName;
		this.testFlag=testFlag;
	}
	
	//---------------------------------------------------------------
	//Getter and Setter
	//---------------------------------------------------------------

	public String getTestID() {
		return testID;
	}
	public void setTestID(String testID) {
		this.testID = testID;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public boolean getTestFlag() {
		return testFlag;
	}
	public void setTestFlag(boolean testFlag) {
		this.testFlag = testFlag;
	}

}
