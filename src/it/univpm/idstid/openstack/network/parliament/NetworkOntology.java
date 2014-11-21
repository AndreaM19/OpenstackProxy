package it.univpm.idstid.openstack.network.parliament;

import java.util.ArrayList;

import it.univpm.idstid.openstack.network.proxy.entity.Network;
import it.univpm.idstid.openstack.network.proxy.entity.NetworkData;
import it.univpm.idstid.openstack.network.proxy.entity.extended.ExtendedNetwork;
import it.univpm.idstid.openstack.network.proxy.entity.extended.ExtendedNetworkData;

public class NetworkOntology{	
	
	public static void insertNetwork(Network net, NetworkData d){
		
		NetworkData data;
				
		if (net!=null)  //call from the Rest Interface
			data = net.getNetwork();
		else  //call from createMultiple
			data=d;
			
		//Recover the network object (from the Rest Interface) attributes
		String name=data.getName();
		String n=data.getId();	
		String shared=data.isShared();	
		String status=data.getStatus();	
		String tenant=data.getTenant_id();
		
		//query- insert a network instance in the OWL knowledge base
		String queryString = 
				"PREFIX base: <http://www.semanticweb.org/spalazzi/ontologies/2014/1/FedCoT#> "+
				"PREFIX owl: <http://www.w3.org/2002/07/owl#/> "+
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#/> "+
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#/> "+
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#/> "+
				"INSERT DATA " +
				"{" +
				"      base:"+tenant+" rdf:type base:Tenant;" +
				"      }; "+  
				"INSERT DATA " +
				"{" +
				"      base:"+n+" rdf:type base:Network;" +
				"      base:hasName '"+name+"';" +
				"      base:isShared "+shared+";" +
				"      base:hasStatus '"+status+"';" +
				"      base:hasOwner base:"+tenant+";" +
				"      }";

		ParliamentModel.updateQuery(queryString);
		
	}
		
	public static void insertExtendedNetwork(ExtendedNetwork net, ExtendedNetworkData d){
		
		ExtendedNetworkData extData;
			
		if (net!=null) //call from the Rest Interface
			extData = net.getNetwork();		
		else //call from createMultiple
			extData=d;

		//Recover the network object (from the Rest Interface) attributes
		String name=extData.getName();
		String n=extData.getId();	
		String shared=extData.isShared();	
		String status=extData.getStatus();	
		String tenant=extData.getTenant_id();
		String type=extData.getNetwork_type();
		String physical=extData.getPhysical_network();
		
		//query- insert a network instance with the Extended attributes
		String queryString = 
				"PREFIX base: <http://www.semanticweb.org/spalazzi/ontologies/2014/1/FedCoT#> "+
				"PREFIX owl: <http://www.w3.org/2002/07/owl#/> "+
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#/> "+
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#/> "+
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#/> "+
				"INSERT DATA " +
				"{" +
				"      base:"+tenant+" rdf:type base:Tenant;" +
				"      }; "+  
				"INSERT DATA " +
				"{" +
				"      base:"+physical+" rdf:type base:PhysicalNetwork;" +
				"      }; "+   
				"INSERT DATA " +
				"{" +			
				"      base:"+n+" rdf:type base:Network;" +			
				"      base:hasName '"+name+"';" +
				"      base:isShared "+shared+";" +
				"      base:hasStatus '"+status+"';" +
				"      base:ofType '"+type+"';" +
				"      base:hasOwner base:"+tenant+";" +
				"      base:hasPhysicalResource base:"+physical+";" +
				"      }";

		ParliamentModel.updateQuery(queryString);
		
	}
	
	public static void insertMultipleNetworks(Network net){
		
		//Recover the arraylist with all the NetworkData from the Rest Interface MultipleCreate  
		ArrayList<NetworkData> multiData = net.getNetworks();
		
		//call insertNetwork for every network NetworkData object
		for (int i=0; multiData.size()<i; i++)
		{
			insertNetwork(null, multiData.get(i));
		}
		
	}
	
	public static void insertMultipleExtendedNetworks(ExtendedNetwork eNet){
		
		//Recover the arraylist with all the NetworkData from the Rest Interface MultipleCreate
		ArrayList<ExtendedNetworkData> multiExtData = eNet.getNetworks();
		
		//call insertNetwork for every network NetworkData object
		for (int i=0; multiExtData.size()<i; i++)
		{
			insertExtendedNetwork(null, multiExtData.get(i));
		}
	
	}
	
	public static void updateNetwork(Network net){
		
		NetworkData data = net.getNetwork();
		
		//Recover the network object (from the Rest Interface) attributes
		String name=data.getName();
		String n=data.getId();	
		String shared=data.isShared();	
		String status=data.getStatus();	
		String tenant=data.getTenant_id();
		
		//query- update a network instance
		String queryString = 
				"PREFIX base: <http://www.semanticweb.org/spalazzi/ontologies/2014/1/FedCoT#> "+
				"PREFIX owl: <http://www.w3.org/2002/07/owl#/> "+
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#/> "+
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#/> "+
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#/> "+
				"DELETE WHERE { base:"+n+" ?p ?o. }; "+
				"INSERT DATA " +
				"{" +
				"      base:"+n+" rdf:type base:Network;" +
				"      base:hasName '"+name+"';" +
				"      base:isShared "+shared+";" +
				"      base:hasStatus '"+status+"';" +
				"      base:hasOwner base:"+tenant+";" +
				"      }";

		ParliamentModel.updateQuery(queryString);
		
	}
	
	public static void updateExtendedNetwork(ExtendedNetwork eNet){
		
		ExtendedNetworkData extData = eNet.getNetwork();
		
		//Recover the network object (from the Rest Interface) attributes
		String name=extData.getName();
		String n=extData.getId();	
		String shared=extData.isShared();	
		String status=extData.getStatus();	
		String tenant=extData.getTenant_id();
		String type=extData.getNetwork_type();
		String physical=extData.getPhysical_network();
		
		//query- update a network instance with extended attributes
		String queryString = 
				"PREFIX base: <http://www.semanticweb.org/spalazzi/ontologies/2014/1/FedCoT#> "+
				"PREFIX owl: <http://www.w3.org/2002/07/owl#/> "+
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#/> "+
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#/> "+
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#/> "+
				"DELETE WHERE { base:"+n+" ?p ?o. }; "+
				"INSERT DATA " +
				"{" +
				"      base:"+physical+" rdf:type base:PhysicalNetwork;" +
				"      }; "+   
				"INSERT DATA " +
				"{" +			
				"      base:"+n+" rdf:type base:Network;" +			
				"      base:hasName '"+name+"';" +
				"      base:isShared "+shared+";" +
				"      base:hasStatus '"+status+"';" +
				"      base:ofType '"+type+"';" +
				"      base:hasOwner base:"+tenant+";" +
				"      base:hasPhysicalResource base:"+physical+";" +
				"      }";

		ParliamentModel.updateQuery(queryString);
		
	}
	
	public static void deleteNetwork(String n){
		
		//query- delete a network instance from the OWL knowledge base
		String queryString = 
				"PREFIX base: <http://www.semanticweb.org/spalazzi/ontologies/2014/1/FedCoT#> "+
				"PREFIX owl: <http://www.w3.org/2002/07/owl#/> "+
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#/> "+
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#/> "+
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#/> "+
				"DELETE WHERE { base:"+n+" ?p ?o. }";

		ParliamentModel.updateQuery(queryString);
		
	}
	
	public static String listNetwork(){
		
		//query the knowledge base to recover all the networks list
		String queryString = 
				"PREFIX base: <http://www.semanticweb.org/spalazzi/ontologies/2014/1/FedCoT#> "+
				"PREFIX owl: <http://www.w3.org/2002/07/owl#/> "+
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#/> "+
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#/> "+
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#/> "+
				"SELECT ?instance ?property ?object WHERE { "+
				"?instance rdf:type base:Network . "+
				"?instance ?property ?object ."+
				"}";

		String w=ParliamentModel.selectQuery(queryString);

		return w;
	
	}
	
	public static String showNetwork(String n){
		
		//query the knowledge base to recover all the attributes from a network instance
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
