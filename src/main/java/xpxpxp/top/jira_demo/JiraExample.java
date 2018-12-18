package xpxpxp.top.jira_demo;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.MetadataRestClient;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueType;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.api.domain.Transition;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;


/**
 * Hello world!
 *
 */
public class JiraExample 
{
	private static Logger logger = LoggerFactory.getLogger(JiraExample.class);
	
	private static String _user = "xpxpxp";
	private static String _pass = "Sk3nWoJbYdiBcy";
	
	private static URI jiraServerUri = URI.create("https://issues.jenkins-ci.org");
	private static AsynchronousJiraRestClientFactory afactory = new AsynchronousJiraRestClientFactory();
	private static JiraRestClient client = afactory.createWithBasicHttpAuthentication(jiraServerUri, _user, _pass);
	
	public JiraExample(String u, String p, URI uri) {
		
		afactory = new AsynchronousJiraRestClientFactory();
		
		client = afactory.createWithBasicHttpAuthentication(uri, u, p);
	}
	
	public static void main( String[] args ) 
    {
    	System.setProperty("java.net.useSystemProxies", "true");
        try {
			getIssueType();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static List<IssueType> getIssueType() throws IOException {
    	
    	try {
    		MetadataRestClient mrClient = client.getMetadataClient();
    		Promise<Iterable<IssueType>> issueTypes = mrClient.getIssueTypes();
    		List<IssueType> lstIssueTypes = new ArrayList<>();
    		lstIssueTypes = (List<IssueType>) issueTypes.claim();
    		lstIssueTypes.forEach( it -> {
    			logger.info("issueType: " + it.getName() + " - id:" + it.getId());
    			//System.out.println("issueType: " + it.getName() + " - id:" + it.getId());
    		});
    		return lstIssueTypes;
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		//client.close();
    	}
    	return null;
    	
    }
    
    public static List<Transition> getTransitionId(String issueID) throws IOException {
    	
    	try {
    		IssueRestClient iClient = client.getIssueClient();
    		Issue issue =iClient.getIssue(issueID).claim();
    		List<Transition> trans= (List<Transition>) iClient.getTransitions(issue).claim();
    		trans.forEach( t ->{
    			logger.info( "Get Issue Transition: " + t.getId() + "\t" + t.getName());
    		});
    		return trans;
    		
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		//client.close();
    	}
    	return null;
    	
    }
    
    public static Issue getIssueById(String id) throws IOException {
    	
    	try {
    		IssueRestClient iClient = client.getIssueClient();
    		Promise<Issue> pi = iClient.getIssue(id);
    		Issue issue = pi.claim();
    		logger.info("Get issue : " + issue.getKey());
    		logger.info("Get issue : " + issue.getSummary());
    		logger.info("Get issue : " + issue.getSummary());
    		logger.info("Get issue : " + issue.getIssueType().getName());
    		return issue;
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		//client.close();
    	}
    	return null;
    	
    }
    
    public static SearchResult searchByJQL(String jql, int max, int start, Set<String> fields) {
    	try {
    		
    		SearchRestClient sClient = client.getSearchClient();
//    		searchJql(String jql, Integer maxResults, Integer startAt, String fields)
    		Promise<SearchResult> pRes = sClient.searchJql(jql, max, start, fields);
    		SearchResult res = pRes.claim();
    		logger.info("Done in JQL:" +jql +"\t" + res.toString());
    		logger.info("Total: " + res.getTotal());
    		logger.info("StartAt: " + res.getStartIndex());
    		logger.info("Max Result: " + res.getMaxResults());
    		logger.info("Issues with fields :" );
    		res.getIssues().forEach( i -> {
    			logger.info(i.getKey() +"\t" +i.getId()+"\t"+i.getSelf());
    		});
    		
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		//client.close();
    	}
    	return null;
    	
    	
    }
    
    
    
}
