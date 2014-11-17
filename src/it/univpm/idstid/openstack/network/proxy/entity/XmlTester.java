package it.univpm.idstid.openstack.network.proxy.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="test")
public class XmlTester {

	private String key="1";
	private String value="value";                
	
	public XmlTester(){
		
	}

	public XmlTester(String key, String value) {
		this.key = key;
		this.value = value;
	}

	@XmlAttribute(name = "key")
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	@XmlAttribute(name = "value")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
    public String toString() {
        return String.format("test " + "[key=%s, value=%s]", key, value);
    }
}
