package it.univpm.idstid.openstack.network.parliament;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.BasicConfigurator;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.update.UpdateExecutionFactory;
import com.hp.hpl.jena.update.UpdateFactory;
import com.hp.hpl.jena.update.UpdateProcessor;
import com.hp.hpl.jena.update.UpdateRequest;


public class ParliamentModel {
	
	//Query to interrogate the knowledge base
	public static String selectQuery(String queryString)
	{
		BasicConfigurator.configure();
		
		//create an object Sparql Query from a parameter string
		Query query = QueryFactory.create(queryString);

		try
		{
			//it connects to the server with Parliament and execute the query
			QueryExecution exec = QueryExecutionFactory.sparqlService(
					"http://localhost:8080/parliament/sparql",
					query);

			ResultSet rs = exec.execSelect();
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			//Format the query result in RDF-XML 
			ResultSetFormatter.outputAsRDF(os, "RDF/XML-ABBREV", rs);

			String w="";
			try {
				w = new String(os.toByteArray(),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			//String tw = ResultSetFormatter.asText(rs);
			//Print the query result
			System.out.println(w);
			
			
			return w;
						
		}
		finally
		{
		}
	}

	//Query to update the knowledge base
	public static void updateQuery(String queryString)
	{
		BasicConfigurator.configure();

		try
		{
			UpdateRequest request = UpdateFactory.create(queryString);
			//it connects to the server with Parliament and execute the query
			UpdateProcessor processor = UpdateExecutionFactory
				    .createRemoteForm(request, "http://localhost:8080/parliament/sparql");
				processor.execute();
		}
		finally
		{

		}
	}

 
}



