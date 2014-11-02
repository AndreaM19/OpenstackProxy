package it.univpm.idstid.openstack.network.proxy.entity;

import it.univpm.idstid.openstack.network.proxy.entity.other.objects.FixedIps;
import it.univpm.idstid.openstack.network.proxy.entity.other.objects.SecourityGroups;

public class PortData {

	private String id;
	private String name;
	private String mac_address;
	private String network_id;
	private boolean admin_state_up;
	private String status;
	private FixedIps fixed_ips;
	private String device_id;
	private String device_owner;
	private String tenant_id;
	private SecourityGroups security_groups;

	public PortData(){

	}

	public PortData(String id, String name, String mac_address,
			String network_id, boolean admin_state_up, String status,
			FixedIps fixed_ips, String device_id, String device_owner,
			String tenant_id, SecourityGroups security_groups) {
		this.id = id;
		this.name = name;
		this.mac_address = mac_address;
		this.network_id = network_id;
		this.admin_state_up = admin_state_up;
		this.status = status;
		this.fixed_ips = fixed_ips;
		this.device_id = device_id;
		this.device_owner = device_owner;
		this.tenant_id = tenant_id;
		this.security_groups = security_groups;
	}

	//---------------------------------------------------------------
	//Getter and Setter
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

	public String getMac_address() {
		return mac_address;
	}

	public void setMac_address(String mac_address) {
		this.mac_address = mac_address;
	}

	public String getNetwork_id() {
		return network_id;
	}

	public void setNetwork_id(String network_id) {
		this.network_id = network_id;
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

	public FixedIps getFixed_ips() {
		return fixed_ips;
	}

	public void setFixed_ips(FixedIps fixed_ips) {
		this.fixed_ips = fixed_ips;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public String getDevice_owner() {
		return device_owner;
	}

	public void setDevice_owner(String device_owner) {
		this.device_owner = device_owner;
	}

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}

	public SecourityGroups getSecurity_groups() {
		return security_groups;
	}

	public void setSecurity_groups(SecourityGroups security_groups) {
		this.security_groups = security_groups;
	}

}
