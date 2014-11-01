package it.univpm.idstid.openstack.network.proxy.entity.other.objects;

import java.util.ArrayList;

public class HostRoutes extends ArrayList<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HostRoutes(){

	}

	//---------------------------------------------------------------
	// Getter and Setter
	//---------------------------------------------------------------

	public HostRoutes getHostRoutes() {
		return this;
	}

	public void setHostRoutes(String hostRoutes) {
		this.add(hostRoutes);
	}

}
