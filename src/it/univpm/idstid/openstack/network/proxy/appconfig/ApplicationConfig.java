package it.univpm.idstid.openstack.network.proxy.appconfig;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/proxy")
public class ApplicationConfig extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		return getRestResourceClasses();
	}

	private Set<Class<?>> getRestResourceClasses() {
		Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
		resources.add(it.univpm.idstid.openstack.network.proxy.resources.NetworkRestInterface.class);
		resources.add(it.univpm.idstid.openstack.network.proxy.resources.SubnetRestInterface.class);
		resources.add(it.univpm.idstid.openstack.network.proxy.resources.PortRestInterface.class);
		resources.add(it.univpm.idstid.openstack.network.proxy.resources.QuotaRestInterface.class);
		return resources;
	}
}