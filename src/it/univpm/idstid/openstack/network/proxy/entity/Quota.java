package it.univpm.idstid.openstack.network.proxy.entity;

import java.util.ArrayList;

public class Quota {
	
	private QuotaData quota=new QuotaData();
	private ArrayList<QuotaData> quotas=new ArrayList<QuotaData>();
	
	//Empty constructor
	public Quota(){

	}
	
	//Constructor for a single Subnet entity
	public Quota(QuotaData quota) {
		this.quota = quota;
	}
	
	//Constructor for multiple Subnet entities
	public Quota(ArrayList<QuotaData> quotas) {
		this.quotas = quotas;
	}

			
	//---------------------------------------------------------------
	//Getter and Setter
	//---------------------------------------------------------------
	
	public QuotaData getQuota() {
		return quota;
	}

	public void setQuota(QuotaData quota) {
		this.quota = quota;
	}

	public ArrayList<QuotaData> getQuotas() {
		return quotas;
	}

	public void setQuotas(ArrayList<QuotaData> quotas) {
		this.quotas = quotas;
	}

}
