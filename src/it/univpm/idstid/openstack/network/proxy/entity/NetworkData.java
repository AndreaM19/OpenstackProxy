package it.univpm.idstid.openstack.network.proxy.entity;

import it.univpm.idstid.openstack.network.proxy.entity.other.objects.Subnets;

public class NetworkData{
	private String id;
	private String name;
	private String shared;
	private String tenant_id;
	private boolean admin_state_up;
	private String status;
	private Subnets subnets;

	public NetworkData(){

	}

	public NetworkData(String id, String name, String shared,
			String tenant_id, boolean admin_state_up, String status,
			Subnets subnets) {
		this.id = id;
		this.name = name;
		this.shared = shared;
		this.tenant_id = tenant_id;
		this.admin_state_up = admin_state_up;
		this.status = status;
		this.subnets = subnets;
	}

	//---------------------------------------------------------------
	// Getter and Setter
	//---------------------------------------------------------------

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String isShared() {
		return shared;
	}

	public void setShared(String shared) {
		this.shared = shared;
	}

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}

	public boolean isAdmin_state_up() {
		return admin_state_up;
	}

	public void setAdmin_state_up(boolean admin_state_up) {
		this.admin_state_up = admin_state_up;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Subnets getSubnets() {
		return subnets;
	}

	public void setSubnets(Subnets subnets) {
		this.subnets = subnets;
	}	

}
