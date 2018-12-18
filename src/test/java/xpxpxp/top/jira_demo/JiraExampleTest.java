package xpxpxp.top.jira_demo;

import java.io.IOException;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import junit.framework.Assert;
import xpxpxp.top.jira_demo.JiraExample;

public class JiraExampleTest 
{
	
	static final String _dummyID = "JENKINS-50687";
	private static String _user = "xpxpxp";
	private static String _pass = "Sk3nWoJbYdiBcy";
	
	private static URI jiraServerUri = URI.create("https://issues.jenkins-ci.org");
	
	
	static JiraExample jiraTestExample = new JiraExample(_user, _pass, jiraServerUri);
	
	@Test
    public void testGetIssueType()
    {
		try {
			Object res = jiraTestExample.getIssueType();
			Assert.assertNotNull(res);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@Test
	public void testGetIssue() {
		try {
			Object res = jiraTestExample.getIssueById(_dummyID);
			Assert.assertNotNull(res);	
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetIssueTransition() {
		try {
			Object res = jiraTestExample.getTransitionId(_dummyID);
			Assert.assertNotNull(res);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchJQL() {
		try {
			String jql = "project = JENKINS AND status in (Open, \"In Progress\", Reopened) AND component = pipeline";
			int max = 500;
			int start = 0;
			Set<String> fields = new HashSet<>();
			fields.add("labels");
			fields.add("status");
			fields.add("component");
			
			
			Object res = jiraTestExample.searchByJQL(jql, max, start, fields);
			Assert.assertNotNull(res);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}