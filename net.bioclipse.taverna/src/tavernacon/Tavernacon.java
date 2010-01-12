/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tavernacon;

import javax.xml.bind.JAXB;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.codec.*;
//import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.HttpEntity;
import org.apache.http.params.HttpParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;

//import org.apache.commons.httpclient.*;
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.URI;
//import org.apache.commons.httpclient.methods.PostMethod;

//import org.apache.commons.httpclient.*;
//import org.apache.commons.httpclient.methods.*;
//import org.apache.commons.httpclient.methods.RequestEntity;
//import org.apache.commons.httpclient.params.HttpMethodParams;
//import org.apache.commons.httpclient.methods.StringRequestEntity;




import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Map;
import jsftest.*;

/**
 *
 * @author Vijay
 */
public class Tavernacon {

    public static final String BASE_URL = "http://ws1.bmc.uu.se:8080/taverna-server-0.2.1/rest";
    public static final String WORKFLOW_MIME = "application/vnd.taverna.t2flow+xml";
    public static final String DATA_MIME = "application/vnd.taverna.data+xml";
    public static final String DATA_PATH = "/data/";
    public static final String JOB_PATH = "/jobs/";
    public static final String WORKFLOW_PATH = "/workflows/";
    private static String COOKIE = "";
    private static List mylist;
    public String workflow;

    //String sw = (String)Page1.workflowsList.getValue();
    //public long selectedWorkflow = Long.parseLong(sw.trim());
    private static Log log = new TestLog();

    public static void main(String[] args) throws Exception {
        Tavernacon tc = new Tavernacon();
        tc.testRunWorkflow();
    }

    public String testRunWorkflow() {

//        String swf = (String) WorkflowSubmissionPage.workflowsList.getValue();
//        long selectedWorkflow = Long.parseLong(swf.trim());

        long selectedWorkflow = 1;
        String inputName;
        inputName = "count";

//        if (swf.equals("1")) {
//            inputName = "count";
//        } else {
//            inputName = "chromosome_name";
//        }

        try {
            RESTClient tavernaRESTClient = new RESTClient();

            //uri for the server
            tavernaRESTClient.setBaseURL(BASE_URL);
            //Data preparation
            //     List<DataValue> innerList = new ArrayList<DataValue>();
            //     List<DataValue> outerList = new ArrayList<DataValue>();
            // innerList.add(new DataValue("80332"));
//        innerList.add(new DataValue("uniprot:wap_rat"));
            //innerList.add(new DataValue("p01013"));
            //      innerList.add(new DataValue("uniprot"));
            //      innerList.add(new DataValue("stealth.vijay@gmail.com"));
            //      innerList.add(new DataValue("blastp"));


            Map<String, DataValue> ds = new HashMap<String, DataValue>();
            //   outerList.add(new DataValue(innerList));
            //  ds.put("id", new DataValue(outerList));


            // ds.put("Sequence_or_ID", new DataValue("uniprot:wap_rat"));

            //input for the workflow (input_name, input_value)
            ds.put(inputName, new DataValue(1));

            System.out.println("Start");
            Long inputID = tavernaRESTClient.addData(new DataResource(ds));
            System.out.println("DataId :" + inputID);

            //workflow file to be run
            String workflowXML = IOUtils.toString(getClass().getResource("pipeline.t2flow").openStream());
            Long workflowID = tavernaRESTClient.addWorkflow(workflowXML);
            System.out.println("WorkflowId :" + workflowID);

            long startTime = System.currentTimeMillis();
            long duration = 0;
            long boo = 2;
            long jobID = tavernaRESTClient.addJob(selectedWorkflow, inputID);//put null in if no inputs

            //displays message to user giving job ID
//            jsftest.WorkflowSubmissionPage.jobID.setValue("Your job has the ID: "+jobID);
            System.out.println("JobId :" + jobID);


            while (!tavernaRESTClient.getJobStatus(jobID).equals("COMPLETE")) {
                duration = System.currentTimeMillis() - startTime;
                System.out.println("Waiting for job [" + jobID + "] to complete (" + (duration / 1000f / 60f) + ")");
                Thread.sleep(5000);
            }
            JobResource job = tavernaRESTClient.getJob(jobID);
            //System.out.println(job);

            Long outputID = job.getOutputs();
            DataResource outputData = tavernaRESTClient.getData(outputID);
            System.out.println("Point1" + outputData);
            List output = outputData.getDataMap().get("out").getList();
            Iterator i = output.iterator();
            while (i.hasNext()) {
                String str = i.next().toString();
                System.out.println(str);
            }
            //System.out.println("Point2"+output.);
            //System.out.println(output.depth());
            //System.out.println("Value = "+output.getValue());
            //Map resultMap = outputData.getDataMap();
            // System.out.println("MapA=" +resultMap.get("count"));
            //DataValue dv = (DataValue) resultMap.get("count");
            //System.out.println("dv_vijay=" +dv);
            //Collection List = resultMap.values();

            // Iterator i = List.iterator();
            // while(i.hasNext()){
            //     String str = i.next().toString();
            //     System.out.println(str);
            // }
            //System.out.println(outputData);

            //	tavernaRESTClient.deleteWorkflow(workflowID);
            //	tavernaRESTClient.deleteJob(jobID);
            //	tavernaRESTClient.deleteData(inputID);
            //	tavernaRESTClient.deleteData(outputID);
            System.out.println("Job took " + (duration / 1000f / 60f) + " minutes");

        } catch (Exception e) {
        }
        return null;
    }
    /*
    public static boolean doLogin(String strLogin, String strPassword) throws Exception
    {
    URL url = new URL(BASE_URL + "/session");
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    
    // "tune" the connection
    conn.setRequestMethod("POST");
    conn.setDoOutput(true);
    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    
    // post login details to the url
    OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
    out.write("session[username]=" + strLogin + "&session[password]=" + strPassword);
    out.close();
    
    if(conn.getResponseCode() == HttpURLConnection.HTTP_OK)
    {
    COOKIE = conn.getHeaderField("Set-Cookie").substring(0, conn.getHeaderField("Set-Cookie").indexOf(";"));
    return(true);
    }
    else
    {
    return(false);
    }
    }


    public static void doLogout() throws Exception
    {
    URL url = new URL(BASE_URL + "/session");
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    
    // "tune" the connection
    conn.setRequestMethod("POST");
    conn.setDoOutput(true);
    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    
    // post login details to the url
    OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
    out.write("_method=delete");
    out.close();
    
    
    // also, delete the cookie that is stored locally
    COOKIE = "";
    }
    /*
    public static String addData(DataResource ds){

    Long inputID = null;

    String strId = null;

    DefaultHttpClient httpclient = new DefaultHttpClient();
    //        HttpClient client = new HttpClient();
    PostMethod method = new PostMethod(BASE_URL + DATA_PATH);

    try {
    // Execute the method.
    int statusCode = httpclient.executeMethod(method);

    if (statusCode != HttpStatus.SC_OK) {
    System.err.println("Method failed: " + method.getStatusLine());
    }

    // Read the response body.
    byte[] responseBody = method.getResponseBody();

    // Deal with the response.
    // Use caution: ensure correct character encoding and is not binary data
    System.out.println(new String(responseBody));
    return responseBody.toString();
    } catch (IOException e) {
    System.err.println("Fatal transport error: " + e.getMessage());
    e.printStackTrace();
    } finally {
    method.releaseConnection();
    return strId;
    }

     */
    /*
    try {

    StringWriter writer = new StringWriter();
    JAXB.marshal(ds, writer);
    StringEntity entity = new StringEntity(writer.toString());
    entity.setContentType("application/xml");
    method.setEntity(entity);
    Long id = executeMethod(method, Long.class);
    HttpResponse response = httpclient.execute(method);
    HttpEntity hentity = response.getEntity();
    InputStream in = hentity.getContent();
    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    Long str1 = Long.parseLong(reader.readLine());
    String str = "0";
    StringBuilder sb = new StringBuilder();
    while((str = reader.readLine()) != null)
    {
    //sb.append(str + "\n");
    strId = strId + str;

    }

    return str1;

    } catch (IOException e) {
    log.error("Unable to execute " + method.getMethod()
    + " method for " + method.getURI(), e);

    }
    return inputID;

    }
     */

    /*
    public static String addJob(Long workflowID, Long dataID) {
    JobResource job = new JobResource();
    job.setWorkflow(workflowID);
    job.setInputs(dataID);
    return addJob(job);
    }
     */
    /*
    public static String addJob(JobResource job) {
    Long jobID = null;
    String strId ="";

    DefaultHttpClient httpclient = new DefaultHttpClient();
    HttpPost method = new HttpPost(BASE_URL + JOB_PATH);
    try {
    FileOutputStream fs = new FileOutputStream("/Vijay/ProjectH/workspace/LifeRayPluginsSDK/TMP/entity.txt");
    StringWriter writer = new StringWriter();
    JAXB.marshal(job, writer);
    StringEntity entity = new StringEntity(writer.toString());
    //RequestEntity rentity = new StringRequestEntity(writer.toString(), "application/xml", "charset=ISO-8859-1");
    //method.setRequestEntity(rentity);
    // int result = httpclient.executeMethod(method);
    //String res = method.getResponseBody().toString();

    entity.setContentType("application/xml");
    method.setEntity(entity);
    //  jobID = executeMethod(method, Long.class);
    HttpResponse response = httpclient.execute(method);
    HttpEntity hentity = response.getEntity();
    InputStream in = hentity.getContent();
    //  int nextChar;


    //HttpEntity hentity = response.getEntity();
    //HttpParams hparams = response.getParams();
    //Object lparam = hparams.getParameter("id");
    //String param = lparam.toString();
    //hentity.writeTo(fs);
    //Long len = hentity.getContentLength();
    //String slen = len.toString();
    //InputStream in = hentity.getContent();
    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    //String nextChar0 = reader.readLine();
    // int id1 =0;
    // String id ="";
    // while ( ( id1 = reader.read() ) != -1  ){
    //    fs.write(  (char) nextChar);

    //   id = id + String.valueOf(id1);
    // }
    //  String snchar = String.valueOf(nextChar0);
    String str="";
    while((str = reader.readLine()) != null)
    {
    //      sb.append(str + "\n");
    strId = strId+str;
    }
    // jobId = Long.parseLong(strId);

    in.close();

    return strId;

    } catch (IOException e) {
    //	log.error("Unable to execute " + method.getMethod()
    //			+ " method for " + method.getURI(), e);
    }
    return strId;
    }

     */
    /*
    public static Long addWorkflow(String workflowXML) {
    Long workflowId = null;
    String strId = "";
    DefaultHttpClient httpclient = new DefaultHttpClient();
    //PostMethod method = new PostMethod(BASE_URL + WORKFLOW_PATH);
    HttpPost method = new HttpPost(BASE_URL + WORKFLOW_PATH);
    try {
    StringEntity entity = new StringEntity(workflowXML);
    entity.setContentType(WORKFLOW_MIME);
    method.setEntity(entity);
    HttpResponse response = httpclient.execute(method);
    HttpEntity hentity = response.getEntity();
    InputStream in = hentity.getContent();
    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    //workflowID = executeMethod(method, Long.class);
    String str = null;
    while((str = reader.readLine()) != null)
    {
    //sb.append(str + "\n");
    strId = strId + str;

    }
    workflowId = Long.parseLong(strId);
    return workflowId;
    } catch (IOException e) {
    log.error("Unable to execute " + method.getMethod()
    + " method for " + method.getURI(), e);
    }
    return workflowId;
    }

     */
    /*
    public static String getWorkflow(int iID) throws Exception
    {
    URL url = new URL(BASE_URL + "/workflows/" + iID);
    URLConnection conn = url.openConnection();
    String strFilePath = "demoXML.xml";
    // if logged in, post the cookie to get access to 'private' data
    if(COOKIE.length() > 0)
    {
    conn.setRequestProperty("Cookie", COOKIE);
    }
    
    BufferedReader reader =
    new BufferedReader(new InputStreamReader(conn.getInputStream()));
    
    //    String obj = conn.getContent().toString();
    //  System.out.println(obj);
    //System.out.println("###############################################");
    //System.out.println("###############################################");

    String strLine = "";
    String strWorkflow = "";
    String strWorkflow1 = "";
    // String lessThan = "<";
    // String greaterThan = ">";
    while((strLine = reader.readLine()) != null)
    { //strLine = reader.readLine();
    strWorkflow = strLine.replaceAll("&lt;", "<");
    strWorkflow = strWorkflow.replaceAll("&gt;",">");
    strWorkflow = strWorkflow.replaceAll("&quot;","\"");
    strWorkflow = strWorkflow.replaceAll("&#xD;","\n");
    strWorkflow = strWorkflow.replaceAll("><",">\n<");
    strWorkflow1 = strWorkflow1 + strWorkflow;
    }

    //try
    //{
    byte buf[] = strWorkflow.getBytes();
    FileOutputStream fos = new FileOutputStream(strFilePath);
    for (int i=0; i < buf.length; i ++){
    fos.write(buf[i]);
    }
    fos.close();
    //}
    //catch(FileNotFoundException ex)
    //{
    // System.out.println("FileNotFoundException : " + ex);
    //}
    //catch(IOException ioe)
    //{
    // System.out.println("IOException : " + ioe);
    //}
    //strWorkflow.replaceAll("&gt;",">");
    //strWorkflow.replaceAll(" '/&'quot;", "/'");
    //strWorkflow.replaceAll("&lt;", "<");
    // DocumentBuilderFactory Factory = DocumentBuilderFactory.newInstance();
    //DocumentBuilder builder = Factory.newDocumentBuilder();
    //Document doc = builder.parse(strWorkflow);
    //String workflow = doc.toString();

    return(strWorkflow1);
    }
     */
}    

