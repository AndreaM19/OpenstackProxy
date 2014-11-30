package it.univpm.idstid.openstack.network.parliament;

import java.util.ArrayList;

//import it.univpm.idstid.openstack.network.proxy.entity.Port;
//import it.univpm.idstid.openstack.network.proxy.entity.PortData;
import it.univpm.idstid.openstack.network.proxy.entity.extended.ExtendedPort;
import it.univpm.idstid.openstack.network.proxy.entity.extended.ExtendedPortData;
import it.univpm.idstid.openstack.network.proxy.entity.other.objects.FixedIps;
import it.univpm.idstid.openstack.network.proxy.entity.other.objects.Ip;
import it.univpm.idstid.openstack.network.proxy.entity.other.objects.SecurityGroups;


public class PortOntology{

//	public static void insertPort(Port prt, PortData d){
//		
//		PortData data;
//		
//		if (prt!=null) //call from the Rest Interface
//			data = prt.getPort();
//		else  //call from createMultiple
//			data=d;
//		
//		//Recover the port object (from the Rest Interface) attributes
//		String name = data.getName();
//		String n = data.getId();
//		FixedIps fixed = data.getFixed_ips();
//		String mac = data.getMac_address();
//		String network = data.getNetwork_id();
//		SecourityGroups security = data.getSecurity_groups();
//		String status = data.getStatus();
//		String tenant = data.getTenant_id();
//					
//		//query- insert a port instance in the OWL knowledge base
//		String queryString = 
//				"PREFIX base: <http://www.semanticweb.org/spalazzi/ontologies/2014/1/FedCoT#> "+
//				"PREFIX owl: <http://www.w3.org/2002/07/owl#/> "+
//				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#/> "+
//				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#/> "+
//				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#/> ";
//		for (int i=0; i<fixed.size(); i++)
//		{
//			Ip ip = fixed.get(i);
//
//				queryString += 
//					"INSERT DATA " +
//					"{" +
//					"      base:"+ip.getIp_address()+" rdf:type base:FixedIP;" +
//					"      }; ";
//		}
//		for (int j=0; j<security.size(); j++)
//		{
//			queryString += 
//				"INSERT DATA " +
//				"{" +
//				"      base:"+security.get(j)+" rdf:type base:SecurityGroup;" +
//				"      }; ";
//		}
//		queryString += 
//				"INSERT DATA " +
//				"{" +
//				"      base:"+tenant+" rdf:type base:Tenant;" +
//				"      }; "+  
//				"INSERT DATA " +
//				"{" +
//				"      base:"+n+" rdf:type base:Port;" +
//				"      base:hasName '"+name+"';";
//		for (int i=0; i<fixed.size(); i++)
//		{
//			Ip ip = fixed.get(i);
//
//			queryString += 
//			"      base:hasFixedIP base:"+ip.getIp_address()+";";
//		}
//		for (int j=0; j<security.size(); j++)
//		{
//			queryString += 
//			"      base:hasSecurityGroup base:"+security.get(j)+";";
//		}
//		queryString += 				
//				"      base:hasMACAddress '"+mac+"';" +
//				"      base:isPartOfNetwork base:"+network+";" +
//				"      base:hasStatus '"+status+"';" +
//				"      base:hasOwner base:"+tenant+";" +
//				"      }";
//
//		ParliamentModel.updateQuery(queryString);
//		
//	}
	
  	public static void insertExtendedPort(ExtendedPort ePrt, ExtendedPortData d){ 		
  		
		ExtendedPortData extData;
		
		if (ePrt!=null) //call from the Rest Interface
			extData = ePrt.getPort();
		else  //call from createMultiple
			extData=d;
		
		//Recover the port object (from the Rest Interface) attributes (extended attributes only if they are present)
						
		String name = extData.getName();
		String n = extData.getId();
		FixedIps fixed = extData.getFixed_ips();
		String mac = extData.getMac_address();
		String network = extData.getNetwork_id();
		SecurityGroups security = extData.getSecurity_groups();
		String status = extData.getStatus();
		String tenant = extData.getTenant_id();
		String host = extData.getHost_id();
  		
		//query- insert a port instance with the Extended attributes
  		String queryString = 
				"PREFIX base: <http://www.semanticweb.org/spalazzi/ontologies/2014/1/FedCoT#> "+
				"PREFIX owl: <http://www.w3.org/2002/07/owl#/> "+
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#/> "+
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#/> "+
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#/> ";
		for (int i=0; i<fixed.size(); i++)
		{
			Ip ip = fixed.get(i);

				queryString += 
					"INSERT DATA " +
					"{" +
					"      base:"+ip.getIp_address()+" rdf:type base:FixedIP;" +
					"      }; ";
		}
		for (int j=0; j<security.size(); j++)
		{
			queryString += 
				"INSERT DATA " +
				"{" +
				"      base:"+security.get(j)+" rdf:type base:SecurityGroup;" +
				"      }; ";
		}
		queryString += 
				"INSERT DATA " +
				"{" +
				"      base:"+tenant+" rdf:type base:Tenant;" +
				"      }; ";
		if (host!=null){
			queryString += 
				"INSERT DATA " +
				"{" +
				"      base:"+host+" rdf:type base:HNode;" +
				"      }; ";
		}
		queryString += 
				"INSERT DATA " +
				"{" +
				"      base:"+n+" rdf:type base:Port;" +
				"      base:hasName '"+name+"';";
		for (int i=0; i<fixed.size(); i++)
		{
			Ip ip = fixed.get(i);

			queryString += 
			"      base:hasFixedIP base:"+ip.getIp_address()+";";
		}
		for (int j=0; j<security.size(); j++)
		{
			queryString += 
			"      base:hasSecurityGroup base:"+security.get(j)+";";
		}
		queryString += 				
				"      base:hasMACAddress '"+mac+"';" +
				"      base:isPartOfNetwork base:"+network+";" +
				"      base:hasStatus '"+status+"';" +
				"      base:hasOwner base:"+tenant+";";
		if (host!=null){
			queryString += 	
				"      base:hasHNode base:"+host+";";
		}
		queryString += 	
				"      }";

		ParliamentModel.updateQuery(queryString);

		
	}
  	
  
//	public static void insertMultiplePorts(Port prt){
//		
//		//Recover the arraylist with all the PortData from the Rest Interface MultipleCreate  
//		ArrayList<PortData> multiData = prt.getPorts();
//		
//		//call insertPort for every port PortData object
//		for (int i=0; multiData.size()<i; i++)
//		{
//			insertPort(null, multiData.get(i));
//		}
//		
//	}
	
	public static void insertMultipleExtendedPorts(ExtendedPort ePrt){
		
		//Recover the arraylist with all the PortData from the Rest Interface MultipleCreate
		ArrayList<ExtendedPortData> multiExtData = ePrt.getPorts();
		
		//call insertPort for every port PortData object
		for (int i=0; multiExtData.size()<i; i++)
		{
			insertExtendedPort(null, multiExtData.get(i));
		}
		
	}
	
//	public static void updatePort(Port prt){
//		
//		PortData data = prt.getPort();
//		
//		//Recover the port object (from the Rest Interface) attributes
//		String name = data.getName();
//		String n = data.getId();
//		FixedIps fixed = data.getFixed_ips();
//		String mac = data.getMac_address();
//		String network = data.getNetwork_id();
//		SecourityGroups security = data.getSecurity_groups();
//		String status = data.getStatus();
//		String tenant = data.getTenant_id();
//		
//		//query- update a port instance
//		String queryString = 
//				"PREFIX base: <http://www.semanticweb.org/spalazzi/ontologies/2014/1/FedCoT#> "+
//				"PREFIX owl: <http://www.w3.org/2002/07/owl#/> "+
//				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#/> "+
//				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#/> "+
//				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#/> "+
//				"DELETE WHERE { base:"+n+" ?p ?o. }; ";
//		for (int i=0; i<fixed.size(); i++)
//		{
//			Ip ip = fixed.get(i);
//
//				queryString += 
//					"INSERT DATA " +
//					"{" +
//					"      base:"+ip.getIp_address()+" rdf:type base:FixedIP;" +
//					"      }; ";
//		}
//		for (int j=0; j<security.size(); j++)
//		{
//			queryString += 
//				"INSERT DATA " +
//				"{" +
//				"      base:"+security.get(j)+" rdf:type base:SecurityGroup;" +
//				"      }; ";
//		}
//		queryString += 
//				"INSERT DATA " +
//				"{" +
//				"      base:"+n+" rdf:type base:Port;" +
//				"      base:hasName '"+name+"';";
//		for (int i=0; i<fixed.size(); i++)
//		{
//			Ip ip = fixed.get(i);
//
//			queryString += 
//			"      base:hasFixedIP base:"+ip.getIp_address()+";";
//		}
//		for (int j=0; j<security.size(); j++)
//		{
//			queryString += 
//			"      base:hasSecurityGroup base:"+security.get(j)+";";
//		}
//		queryString += 				
//				"      base:hasMACAddress '"+mac+"';" +
//				"      base:isPartOfNetwork base:"+network+";" +
//				"      base:hasStatus '"+status+"';" +
//				"      base:hasOwner base:"+tenant+";" +
//				"      }";
//
//		ParliamentModel.updateQuery(queryString);
//		
//	}
	
	
  	public static void updateExtendedPort(ExtendedPort ePrt){
  		
		ExtendedPortData extData = ePrt.getPort();
		
		//Recover the port object (from the Rest Interface) attributes (extended attributes only if they are present)
		String name = extData.getName();
		String n = extData.getId();
		FixedIps fixed = extData.getFixed_ips();
		String mac = extData.getMac_address();
		String network = extData.getNetwork_id();
		SecurityGroups security = extData.getSecurity_groups();
		String status = extData.getStatus();
		String tenant = extData.getTenant_id();
		String host = extData.getHost_id();
  		
		//query- update a port instance with extended attributes
  		String queryString = 
				"PREFIX base: <http://www.semanticweb.org/spalazzi/ontologies/2014/1/FedCoT#> "+
				"PREFIX owl: <http://www.w3.org/2002/07/owl#/> "+
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#/> "+
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#/> "+
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#/> "+
  				"DELETE WHERE { base:"+n+" ?p ?o. };";
		for (int i=0; i<fixed.size(); i++)
		{
			Ip ip = fixed.get(i);

				queryString += 
					"INSERT DATA " +
					"{" +
					"      base:"+ip.getIp_address()+" rdf:type base:FixedIP;" +
					"      }; ";
		}
		for (int j=0; j<security.size(); j++)
		{
			queryString += 
				"INSERT DATA " +
				"{" +
				"      base:"+security.get(j)+" rdf:type base:SecurityGroup;" +
				"      }; ";
		}
		queryString += 
				"INSERT DATA " +
				"{" +
				"      base:"+tenant+" rdf:type base:Tenant;" +
				"      }; ";
		if (host!=null){
			queryString += 
				"INSERT DATA " +
				"{" +
				"      base:"+host+" rdf:type base:HNode;" +
				"      }; ";
		}
		queryString += 
				"INSERT DATA " +
				"{" +
				"      base:"+n+" rdf:type base:Port;" +
				"      base:hasName '"+name+"';";
		for (int i=0; i<fixed.size(); i++)
		{
			Ip ip = fixed.get(i);

			queryString += 
			"      base:hasFixedIP base:"+ip.getIp_address()+";";
		}
		for (int j=0; j<security.size(); j++)
		{
			queryString += 
			"      base:hasSecurityGroup base:"+security.get(j)+";";
		}
		queryString += 				
				"      base:hasMACAddress '"+mac+"';" +
				"      base:isPartOfNetwork base:"+network+";" +
				"      base:hasStatus '"+status+"';" +
				"      base:hasOwner base:"+tenant+";";
		if (host!=null){
			queryString += 	
				"      base:hasHNode base:"+host+";";
		}
		queryString += 	
				"      }";

		ParliamentModel.updateQuery(queryString);

		
	}
	
	public static void deletePort(String n){
		
		//query- delete a port instance from the OWL knowledge base
		String queryString = 
				"PREFIX base: <http://www.semanticweb.org/spalazzi/ontologies/2014/1/FedCoT#> "+
				"PREFIX owl: <http://www.w3.org/2002/07/owl#/> "+
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#/> "+
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#/> "+
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#/> "+
				"DELETE WHERE { base:"+n+" ?p ?o. }";

		ParliamentModel.updateQuery(queryString);
		
	}
	
	public static String listPort(){
		
		//query the knowledge base to recover all the ports list
		String queryString = 
				"PREFIX base: <http://www.semanticweb.org/spalazzi/ontologies/2014/1/FedCoT#> "+
				"PREFIX owl: <http://www.w3.org/2002/07/owl#/> "+
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#/> "+
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#/> "+
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#/> "+
				"SELECT ?instance ?property ?object WHERE { "+
				"?instance rdf:type base:Port . "+
				"?instance ?property ?object ."+
				"}";

		String w=ParliamentModel.selectQuery(queryString);

		return w;
	}
	
	public static String showPort(String n){
		
		//query the knowledge base to recover all the attributes from a port instance
		String queryString = 
				"PREFIX base: <http://www.semanticweb.org/spalazzi/ontologies/2014/1/FedCoT#> "+
				"PREFIX owl: <http://www.w3.org/2002/07/owl#/> "+
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#/> "+
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#/> "+
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#/> "+
				"SELECT DISTINCT ?property ?object WHERE { "+
				"base:"+n+" ?property ?object . "+
				"}";				

		String w=ParliamentModel.selectQuery(queryString);

		return w;
	}

}
