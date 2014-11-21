package it.univpm.idstid.openstack.network.parliament;

import java.util.ArrayList;

import it.univpm.idstid.openstack.network.proxy.entity.Subnet;
import it.univpm.idstid.openstack.network.proxy.entity.SubnetData;
import it.univpm.idstid.openstack.network.proxy.entity.other.objects.AllocationPools;
import it.univpm.idstid.openstack.network.proxy.entity.other.objects.Pool;

public class SubnetOntology{
	
	public static void insertSubnet(Subnet sub, SubnetData d){
		
		SubnetData data;
		
		if (sub!=null) //call from the Rest Interface
			data = sub.getSubnet();
		else  //call from createMultiple
			data=d;
		
		//Recover the subnet object (from the Rest Interface) attributes
		String name = data.getName();
		String n = data.getId();
		String network = data.getNetwork_id();
		String tenant = data.getTenant_id();
		AllocationPools alPools = data.getAllocation_pools();
		String gateway = data.getGateway_ip();
		String ipversion = data.getIp_version();
		
		//query- insert a subnet instance in the OWL knowledge base
		String queryString = 
				"PREFIX base: <http://www.semanticweb.org/spalazzi/ontologies/2014/1/FedCoT#> "+
				"PREFIX owl: <http://www.w3.org/2002/07/owl#/> "+
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#/> "+
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#/> "+
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#/> "+
				"PREFIX dul: <http://www.loa-cnr.it/ontologies/DUL.owl#/> ";
				
		for (int i=0; i<alPools.size(); i++)
		{
			Pool pool = alPools.get(i);

			queryString += 
					"INSERT DATA " +
					"{" +
					"      base:"+pool.getStart()+" rdf:type base:IPaddress;" +
					"      }; "+ 
					"INSERT DATA " +
					"{" +
					"      base:"+pool.getEnd()+" rdf:type base:IPaddress;" +
					"      }; ";
		}
				
		queryString += 
				"INSERT DATA " +
				"{" +
				"      base:"+tenant+" rdf:type base:Tenant;" +
				"      }; "+  
				"INSERT DATA " +
				"{" +
				"      base:"+gateway+" rdf:type base:IPaddress;" +
				"      }; "+ 
				"INSERT DATA " +
				"{" +
				"      base:"+n+" rdf:type base:SubNet;" +
				"      base:hasName '"+name+"';" +
				"      base:hasOwner base:"+tenant+";" +
				"      dul:isPartOf base:"+network+";";
		for (int i=0; i<alPools.size(); i++)
		{
			Pool pool = alPools.get(i);

			queryString += 
					"      base:hasStartIPAddress base:"+pool.getStart()+";" +
					"      base:hasEndIPAddress base:"+pool.getEnd()+";";
		}
		queryString += 
				"      base:hasGatewayIP base:"+gateway+";" +				
				"      base:hasVersion '"+ipversion+"';" +
				"      }";

		ParliamentModel.updateQuery(queryString);
		
	}
	 
	public static void insertMultipleSubnets(Subnet sub){
		
		//Recover the arraylist with all the SubnetData from the Rest Interface MultipleCreate  
		ArrayList<SubnetData> multiData = sub.getSubnets();
		
		//call insertSubnet for every subnet SubnetData object
		for (int i=0; multiData.size()<i; i++)
		{
			insertSubnet(null, multiData.get(i));
		}
		
	}
	
	public static void updateSubnet(Subnet sub){
		
		SubnetData data = sub.getSubnet();
		
		//Recover the subnet object (from the Rest Interface) attributes
		String name = data.getName();
		String n = data.getId();
		String network = data.getNetwork_id();
		String tenant = data.getTenant_id();
		AllocationPools alPools = data.getAllocation_pools();
		String gateway = data.getGateway_ip();
		String ipversion = data.getIp_version();
		
		//query- update a subnet instance
		String queryString = 
				"PREFIX base: <http://www.semanticweb.org/spalazzi/ontologies/2014/1/FedCoT#> "+
				"PREFIX owl: <http://www.w3.org/2002/07/owl#/> "+
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#/> "+
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#/> "+
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#/> "+
				"PREFIX dul: <http://www.loa-cnr.it/ontologies/DUL.owl#> "+
				"DELETE WHERE { base:"+n+" ?p ?o. }; ";
		for (int i=0; i<alPools.size(); i++)
		{
			Pool pool = alPools.get(i);

			queryString += 
					"INSERT DATA " +
					"{" +
					"      base:"+pool.getStart()+" rdf:type base:IPaddress;" +
					"      }; "+ 
					"INSERT DATA " +
					"{" +
					"      base:"+pool.getEnd()+" rdf:type base:IPaddress;" +
					"      }; ";
		}
		queryString +=
				"INSERT DATA " +
				"{" +
				"      base:"+gateway+" rdf:type base:IPaddress;" +
				"      }; "+ 
				"INSERT DATA " +
				"{" +
				"      base:"+n+" rdf:type base:SubNet;" +
				"      base:hasName '"+name+"';" +
				"      base:hasOwner base:"+tenant+";" +
				"      dul:isPartOf base:"+network+";";
		for (int i=0; i<alPools.size(); i++)
		{
			Pool pool = alPools.get(i);

			queryString += 
					"      base:hasStartIPAddress base:"+pool.getStart()+";" +
					"      base:hasEndIPAddress base:"+pool.getEnd()+";";
		}
		queryString +=
				"      base:hasGatewayIP base:"+gateway+";" +				
				"      base:hasVersion '"+ipversion+"';" +
				"      }";

		ParliamentModel.updateQuery(queryString);
		
	}
	
	public static void deleteSubnet(String n){
		
		//query- delete a subnet instance from the OWL knowledge base
		String queryString = 
				"PREFIX base: <http://www.semanticweb.org/spalazzi/ontologies/2014/1/FedCoT#> "+
				"PREFIX owl: <http://www.w3.org/2002/07/owl#/> "+
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#/> "+
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#/> "+
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#/> "+
				"DELETE WHERE { base:"+n+" ?p ?o. }";

		ParliamentModel.updateQuery(queryString);
		
	}
	
	public static String listSubnet(){
		
		//query the knowledge base to recover all the subnets list
		String queryString = 
				"PREFIX base: <http://www.semanticweb.org/spalazzi/ontologies/2014/1/FedCoT#> "+
				"PREFIX owl: <http://www.w3.org/2002/07/owl#/> "+
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#/> "+
				"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#/> "+
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#/> "+
				"SELECT ?instance ?property ?object WHERE { "+
				"?instance rdf:type base:SubNet . "+
				"?instance ?property ?object ."+
				"}";

		String w=ParliamentModel.selectQuery(queryString);

		return w;
	}
	
	public static String showSubnet(String n){
		
		//query the knowledge base to recover all the attributes from a subnet instance
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
