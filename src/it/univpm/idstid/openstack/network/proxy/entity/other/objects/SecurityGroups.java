package it.univpm.idstid.openstack.network.proxy.entity.other.objects;

import java.util.ArrayList;

public class SecurityGroups extends ArrayList<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SecurityGroups(){

	}

	//---------------------------------------------------------------
	// Getter and Setter
	//---------------------------------------------------------------

	public void setSecurityGroups(String stringToSet){
		this.add(stringToSet);
	}

	public SecurityGroups getSecurityGroups(){
		return this;
	}

}
