package it.univpm.idstid.openstack.network.proxy.entity.other.objects;

import java.util.ArrayList;

public class Subnets extends ArrayList<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Subnets(){

	}

	//---------------------------------------------------------------
	// Getter and Setter
	//---------------------------------------------------------------

	public void setSubnets(String stringToSet){
		this.add(stringToSet);
	}

	public Subnets getSubnets(){
		return this;
	}

}
