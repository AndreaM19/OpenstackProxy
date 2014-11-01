package it.univpm.idstid.openstack.network.proxy.entity.other.objects;

import java.util.ArrayList;

public class AllocationPools extends ArrayList<Pool> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AllocationPools(){

	}

	//---------------------------------------------------------------
	// Getter and Setter
	//---------------------------------------------------------------

	public AllocationPools getAllocationPools() {
		return this;
	}

	public void setAllocationPools(Pool pools) {
		this.add(pools);
	}
}
