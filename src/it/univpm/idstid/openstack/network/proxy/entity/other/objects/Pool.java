package it.univpm.idstid.openstack.network.proxy.entity.other.objects;

public class Pool {
	
	private String end;
	private String start;

	public Pool(){

	}

	public Pool(String end, String start) {
		this.end = end;
		this.start = start;
	}

	//---------------------------------------------------------------
	// Getter and Setter
	//---------------------------------------------------------------

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

}
