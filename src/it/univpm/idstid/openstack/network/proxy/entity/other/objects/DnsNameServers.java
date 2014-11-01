package it.univpm.idstid.openstack.network.proxy.entity.other.objects;

import java.util.ArrayList;

public class DnsNameServers extends ArrayList<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DnsNameServers(){

	}

	//---------------------------------------------------------------
	// Getter and Setter
	//---------------------------------------------------------------

	public DnsNameServers getDnsNameServers() {
		return this;
	}

	public void setDnsNameServers(String dnsNameServers) {
		this.add(dnsNameServers);
	}

}
