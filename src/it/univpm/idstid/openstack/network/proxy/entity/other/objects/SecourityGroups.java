package it.univpm.idstid.openstack.network.proxy.entity.other.objects;

import java.util.ArrayList;

public class SecourityGroups extends ArrayList<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SecourityGroups(){

	}

	//---------------------------------------------------------------
	// Getter and Setter
	//---------------------------------------------------------------

	public void setSecourityGroups(String stringToSet){
		this.add(stringToSet);
	}

	public SecourityGroups getSecourityGroups(){
		return this;
	}

}
