/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.bioclipse.taverna.tavernacon;

import org.apache.commons.logging.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXB;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.client.methods.HttpRequestBase;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;

/**
 *
 * @author Vijay
 */
public class RESTClient implements RESTService {

	public static final String WORKFLOW_MIME = "application/vnd.taverna.t2flow+xml";
	public static final String DATA_MIME = "application/vnd.taverna.data+xml";

	public static final String DATA_PATH = "/data/";
	public static final String JOB_PATH = "/jobs/";
	public static final String WORKFLOW_PATH = "/workflows/";

	//	public static final String BASE_URL = "http://rpc103.cs.man.ac.uk:8081/taverna-server-0.2.1/rest";
	public static final String BASE_URL = "http://ws1.bmc.uu.se:8080/taverna-server-0.2.1//rest";
	
    
	// private final Log log = LogFactory.getLog(TavernaRESTClient.class);

	private final Log log = new TestLog();
	private String baseURL;
//	private Credentials credentials;

	/**
	 * Returns the base url of the Taverna Server REST interface.
	 *
	 * @return the base url of the Taverna Server REST interface
	 */
	public String getBaseURL() {
		return baseURL;
	}

	/**
	 * Sets the base url of the Taverna Server REST interface, e.g.
	 * "http://www.example.com/t2server/rest".
	 *
	 * @param baseURL
	 *            base url of the Taverna Server REST interface
	 */
	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	/**
	 * Sets the user credentials to use when calling Taverna Server.
	 *
	 * @param credentials
	 *            the user credentials to use when calling Taverna Server
	 */
	/*
    public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
    */

	public Long addData(DataResource data) {
		Long workflowID = null;
		HttpPost method = new HttpPost(baseURL + DATA_PATH);
		try {
			StringWriter writer = new StringWriter();
			JAXB.marshal(data, writer);
			StringEntity entity = new StringEntity(writer.toString());
			entity.setContentType("application/xml");
			method.setEntity(entity);
			workflowID = executeMethod(method, Long.class);
		} catch (Exception e) {
		    e.printStackTrace();
			log.error("Unable to execute " + method.getMethod()
					+ " method for " + method.getURI(), e);
		}
		return workflowID;
	}

   public Long addJob(Long workflowID, Long dataID) {
		JobResource job = new JobResource();
		job.setWorkflow(workflowID);
		job.setInputs(dataID);
		return addJob(job);
	}

	public Long addJob(JobResource job) {
		Long jobID = null;
		HttpPost method = new HttpPost(baseURL + JOB_PATH);
		try {
			StringWriter writer = new StringWriter();
			JAXB.marshal(job, writer);
			StringEntity entity = new StringEntity(writer.toString());
			entity.setContentType("application/xml");
			method.setEntity(entity);
			jobID = executeMethod(method, Long.class);
		} catch (IOException e) {
			log.error("Unable to execute " + method.getMethod()
					+ " method for " + method.getURI(), e);
		}
		return jobID;
	}

	public Long addWorkflow(String workflowXML) {
		Long workflowID = null;
		HttpPost method = new HttpPost(baseURL + WORKFLOW_PATH);
		try {
			StringEntity entity = new StringEntity(workflowXML);
			entity.setContentType(WORKFLOW_MIME);
			method.setEntity(entity);
			workflowID = executeMethod(method, Long.class);
		} catch (IOException e) {
			log.error("Unable to execute " + method.getMethod()
					+ " method for " + method.getURI(), e);
		}
		return workflowID;
	}

	public void deleteData(Long id) {
		HttpDelete method = new HttpDelete(baseURL + DATA_PATH + id);
		method.setHeader("Content-Type", "text/plain");
		try {
			executeMethod(method, String.class);
		} catch (IOException e) {
			log.error("Unable to execute " + method.getMethod()
					+ " method for " + method.getURI(), e);
		}
	}

	public void deleteJob(Long id) {
		HttpDelete method = new HttpDelete(baseURL + JOB_PATH + id);
		method.setHeader("Content-Type", "text/plain");
		try {
			executeMethod(method, String.class);
		} catch (IOException e) {
			log.error("Unable to execute " + method.getMethod()
					+ " method for " + method.getURI(), e);
		}
	}

	public void deleteWorkflow(Long id) {
		HttpDelete method = new HttpDelete(baseURL + WORKFLOW_PATH + id);
		method.setHeader("Content-Type", "text/plain");
		try {
			executeMethod(method, String.class);
		} catch (IOException e) {
			log.error("Unable to execute " + method.getMethod()
					+ " method for " + method.getURI(), e);
		}
	}

	public DataResource getData(Long id) {
		DataResource data = null;
		HttpGet method = new HttpGet(baseURL + DATA_PATH + id);
		try {
			data = executeMethod(method, DataResource.class);
		} catch (IOException e) {
			log.error("Unable to execute " + method.getMethod()
					+ " method for " + method.getURI(), e);
		}
		return data;
	}

	public JobResource getJob(Long id) {
		JobResource job = null;
		HttpGet method = new HttpGet(baseURL + JOB_PATH + id);
		try {
			job = executeMethod(method, JobResource.class);
		} catch (IOException e) {
			log.error("Unable to execute " + method.getMethod()
					+ " method for " + method.getURI(), e);
		}
		return job;
	}

	public Jobs getJobs() {
		Jobs jobs = null;
		HttpGet method = new HttpGet(baseURL + JOB_PATH);
		try {
			jobs = executeMethod(method, Jobs.class);
		} catch (IOException e) {
			log.error("Unable to execute " + method.getMethod()
					+ " method for " + method.getURI(), e);
		}
		return jobs;
	}

	public String getJobStatus(Long id) {
		String jobStatus = null;
		HttpGet method = new HttpGet(baseURL + JOB_PATH + id + "/status");
		try {
			jobStatus = executeMethod(method, String.class);
		} catch (IOException e) {
			log.error("Unable to execute " + method.getMethod()
					+ " method for " + method.getURI(), e);
		}
		return jobStatus;
	}

	public Boolean cancelJob(Long id) {
		Boolean cancelJob = false;
		HttpGet method = new HttpGet(baseURL + JOB_PATH + id + "/cancel");
		try {
			cancelJob = executeMethod(method, Boolean.class);
		} catch (IOException e) {
			log.error("Unable to execute " + method.getMethod()
					+ " method for " + method.getURI(), e);
		}
		return cancelJob;
	}

	public Boolean pauseJob(Long id) {
		Boolean pauseJob = false;
		HttpGet method = new HttpGet(baseURL + JOB_PATH + id + "/pause");
		try {
			pauseJob = executeMethod(method, Boolean.class);
		} catch (IOException e) {
			log.error("Unable to execute " + method.getMethod()
					+ " method for " + method.getURI(), e);
		}
		return pauseJob;
	}

	public Boolean resumeJob(Long id) {
		Boolean resumeJob = false;
		HttpGet method = new HttpGet(baseURL + JOB_PATH + id + "/resume");
		try {
			resumeJob = executeMethod(method, Boolean.class);
		} catch (IOException e) {
			log.error("Unable to execute " + method.getMethod()
					+ " method for " + method.getURI(), e);
		}
		return resumeJob;
	}

	public WorkflowResource getWorkflow(Long id) {
		WorkflowResource workflow = null;
		HttpGet method = new HttpGet(baseURL + WORKFLOW_PATH + id);
		try {
			workflow = executeMethod(method, WorkflowResource.class);
		} catch (IOException e) {
			log.error("Unable to execute " + method.getMethod()
					+ " method for " + method.getURI(), e);
		}
		return workflow;
	}

	public Workflows getWorkflows() {
		Workflows workflows = null;
		HttpGet method = new HttpGet(baseURL + WORKFLOW_PATH);
		try {
			workflows = executeMethod(method, Workflows.class);
		} catch (IOException e) {
			log.error("Unable to execute " + method.getMethod()
					+ " method for " + method.getURI(), e);
		}
		return workflows;
	}

    private <T> T executeMethod(HttpRequestBase method, Class<T> type)
			throws IOException {
		T result = null;
		DefaultHttpClient httpclient = new DefaultHttpClient();
        /*
		if (credentials != null) {
			log.debug("Setting credentials " + credentials);
			httpclient.getCredentialsProvider().setCredentials(
					new AuthScope("localhost", 8080), credentials);
		}
        */

		if (log.isDebugEnabled()) {
			httpclient.addRequestInterceptor(new DebugInterceptor());
			httpclient.addResponseInterceptor(new DebugInterceptor());
		}

		// Execute the request
		log.debug("Executing " + method.getMethod() + " method for "
				+ method.getURI());
		HttpResponse response = httpclient.execute(method);

		// Get hold of the response entity
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			Header contentType = response.getFirstHeader("Content-Type");
			InputStream instream = entity.getContent();

			try {
				if ("text/plain".equals(contentType.getValue())) {
					if (type.equals(Long.class)) {
						result = (T) Long.valueOf(IOUtils.toString(instream));
					} else if (type.equals(Boolean.class)) {
						result = (T) Boolean
								.valueOf(IOUtils.toString(instream));
					} else {
						result = (T) IOUtils.toString(instream);
					}
				} else {
					result = JAXB.unmarshal(instream, type);
				}
			} catch (RuntimeException ex) {
				method.abort();
				throw ex;
			} finally {
				// Closing the input stream will trigger connection release
				instream.close();
			}
		}

		return result;
	}

    class DebugInterceptor implements HttpRequestInterceptor,
			HttpResponseInterceptor {
		public void process(HttpRequest request, HttpContext context)
				throws HttpException, IOException {
			StringBuilder sb = new StringBuilder();
			sb.append("Outbound Message");
			//sb.append(lineSeparator);
			process(request, sb);
			log.debug(sb.toString());
		}

        public void process(HttpResponse response, HttpContext context)
				throws HttpException, IOException {
			StringBuilder sb = new StringBuilder();
			sb.append("Inbound Message");
			sb.append(":::::::::::::::");
			sb.append("Status: " + response.getStatusLine());
			//sb.append(lineSeparator);
			process(response, sb);
			log.debug(sb.toString());
		}

		public void process(HttpMessage message, StringBuilder sb) {
			sb.append("Headers: {");
			boolean first = true;
			for (Header header : message.getAllHeaders()) {
				if (!first) {
					sb.append(", ");
				} else {
					first = false;
				}
				sb.append(header);
			}
			sb.append("}");
			//sb.append(lineSeparator);
		}
	}

}
