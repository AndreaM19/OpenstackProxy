package it.univpm.idstid.openstack.network.proxy.entity;

public class Port {

	private String portID="-";
	private String portName="-";
	private String ipAdd="-";
	private String macAddress="-";
	private String networkID="-";
	private boolean adminStateUp=false;
	private String status="-";
	private String fixedIPs="-";
	private String deviceID="-";
	private String deviceOwner="-";
	private String tenantUuID="-";
	private String securityGroups="-";

	public Port(){

	}

	public Port(String portID, String portName, String ipAdd,
			String macAddress, String networkID, boolean adminStateUp,
			String status, String fixedIPs, String deviceID,
			String deviceOwner, String tenantUuID, String securityGroups) {
		this.portID = portID;
		this.portName = portName;
		this.ipAdd = ipAdd;
		this.macAddress = macAddress;
		this.networkID = networkID;
		this.adminStateUp = adminStateUp;
		this.status = status;
		this.fixedIPs = fixedIPs;
		this.deviceID = deviceID;
		this.deviceOwner = deviceOwner;
		this.tenantUuID = tenantUuID;
		this.securityGroups = securityGroups;
	}

	//---------------------------------------------------------------
	//Getter and Setter
	//---------------------------------------------------------------

	public String getPortID() {
		return portID;
	}
	public void setPortID(String portID) {
		this.portID = portID;
	}
	public String getPortName() {
		return portName;
	}
	public void setPortName(String portName) {
		this.portName = portName;
	}
	public String getIpAdd() {
		return ipAdd;
	}
	public void setIpAdd(String ipAdd) {
		this.ipAdd = ipAdd;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public String getNetworkID() {
		return networkID;
	}
	public void setNetworkID(String networkID) {
		this.networkID = networkID;
	}
	public boolean isAdminStateUp() {
		return adminStateUp;
	}
	public void setAdminStateUp(boolean adminStateUp) {
		this.adminStateUp = adminStateUp;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFixedIPs() {
		return fixedIPs;
	}
	public void setFixedIPs(String fixedIPs) {
		this.fixedIPs = fixedIPs;
	}
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	public String getDeviceOwner() {
		return deviceOwner;
	}
	public void setDeviceOwner(String deviceOwner) {
		this.deviceOwner = deviceOwner;
	}
	public String getTenantUuID() {
		return tenantUuID;
	}
	public void setTenantUuID(String tenantUuID) {
		this.tenantUuID = tenantUuID;
	}
	public String getSecurityGroups() {
		return securityGroups;
	}
	public void setSecurityGroups(String securityGroups) {
		this.securityGroups = securityGroups;
	}

}
