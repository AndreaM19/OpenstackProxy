package it.univpm.idstid.openstack.network.proxy.entity;

public class Network {
	
	private String networkID="-";
	private String networkName="-";
	private boolean shared=false;
	private String tenantUuID="-";
	private String ipAdd="-";
	private String allocationPools="-";
	private boolean adminStateUp=false;
	private String status="-";
	private String subnets="-";
	
	//---------------------------------------------------------------
	//Getter and Setter
	//---------------------------------------------------------------
	public void setNetworkID(String networkID) {
		this.networkID = networkID;
	}

	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}

	public void setTenantUuID(String tenantUuID) {
		this.tenantUuID = tenantUuID;
	}

	public void setIpAdd(String ipAdd) {
		this.ipAdd = ipAdd;
	}

	public void setAllocationPools(String allocationPools) {
		this.allocationPools = allocationPools;
	}

	public void setAdminStateUp(boolean adminStateUp) {
		this.adminStateUp = adminStateUp;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setSubnets(String subnets) {
		this.subnets = subnets;
	}

	public String getNetworkID() {
		return networkID;
	}

	public String getNetworkName() {
		return networkName;
	}

	public boolean isShared() {
		return shared;
	}

	public String getTenantUuID() {
		return tenantUuID;
	}

	public String getIpAdd() {
		return ipAdd;
	}

	public String getAllocationPools() {
		return allocationPools;
	}

	public boolean isAdminStateUp() {
		return adminStateUp;
	}

	public String getStatus() {
		return status;
	}

	public String getSubnets() {
		return subnets;
	}

}
