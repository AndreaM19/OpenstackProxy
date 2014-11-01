package it.univpm.idstid.openstack.network.proxy.entity.other.objects;

import java.util.ArrayList;

public class FixedIps extends ArrayList<Ip> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FixedIps(){

	}

	//---------------------------------------------------------------
	// Getter and Setter
	//---------------------------------------------------------------

	public FixedIps getFixedIps() {
		return this;
	}

	public void setFixedIps(Ip fixedIps) {
		this.add(fixedIps);
	}


}
