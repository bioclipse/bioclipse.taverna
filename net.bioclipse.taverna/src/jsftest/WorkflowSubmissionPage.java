/*
 * Page1.java
 *
 * Created on 22-Sep-2009, 15:18:34
 * Copyright me
 */
package jsftest;

import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlOutputLabel;
import com.icesoft.faces.component.ext.HtmlOutputText;
import com.icesoft.faces.component.ext.HtmlSelectOneListbox;
import com.liferay.portal.kernel.log.Log;
import com.sun.rave.web.ui.appbase.AbstractPageBean;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.FacesException;

import net.bioclipse.taverna.tavernacon.DataResource;
import net.bioclipse.taverna.tavernacon.DataValue;
import net.bioclipse.taverna.tavernacon.JobResource;
import net.bioclipse.taverna.tavernacon.RESTClient;
import net.bioclipse.taverna.tavernacon.TestLog;

import org.apache.commons.io.IOUtils;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 */
public class WorkflowSubmissionPage extends AbstractPageBean {

    static String in;
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">
    private int __placeholder;

    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    public static HtmlInputText inputText1 = new HtmlInputText();

    public HtmlInputText getInputText1() {
        return inputText1;
    }

    public void setInputText1(HtmlInputText hit) {
        this.inputText1 = hit;
    }
    public static HtmlSelectOneListbox workflowsList = new HtmlSelectOneListbox();

    public HtmlSelectOneListbox getworkflowsList() {
        return workflowsList;
    }

    public void setworkflowsList(HtmlSelectOneListbox hsol) {
        this.workflowsList = hsol;
    }
    public static HtmlOutputLabel inputLabel = new HtmlOutputLabel();

    public HtmlOutputLabel getinputLabel() {
        return inputLabel;
    }

    public void setinputLabel(HtmlOutputLabel hil) {
        this.inputLabel = hil;
    }
    public static HtmlOutputText description = new HtmlOutputText();

    public HtmlOutputText getdescription() {
        return description;
    }

    public void setdescription(HtmlOutputText hot) {
        this.description = hot;
    }
    public static HtmlOutputText jobID = new HtmlOutputText();

    public HtmlOutputText getjobID() {
        return jobID;
    }

    public void setjobID(HtmlOutputText hot) {
        this.jobID = hot;
    }
    public static String jobIDString = new String("");

    public String getjobIDString() {
        return jobIDString;
    }

    public void setjobIDString(String str) {
        this.jobIDString = str;
    }


    // </editor-fold>
    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public WorkflowSubmissionPage() {
    }

    /**
     * <p>Callback method that is called whenever a page is navigated to,
     * either directly via a URL, or indirectly via page navigation.
     * Customize this method to acquire resources that will be needed
     * for event handlers and lifecycle methods, whether or not this
     * page is performing post back processing.</p>
     *
     * <p>Note that, if the current request is a postback, the property
     * values of the components do <strong>not</strong> represent any
     * values submitted with this request.  Instead, they represent the
     * property values that were saved for this view when it was rendered.</p>
     */
    public void init() {
        // Perform initializations inherited from our superclass
        super.init();
        // Perform application initialization that must complete
        // *before* managed components are initialized
        // TODO - add your own initialiation code here

        // <editor-fold defaultstate="collapsed" desc="Managed Component Initialization">
        // Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {
            _init();
        } catch (Exception e) {
            log("Page1 Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }

    // </editor-fold>
    // Perform application initialization that must complete
    // *after* managed components are initialized
    // TODO - add your own initialization code here
    }

    /**
     * <p>Callback method that is called after the component tree has been
     * restored, but before any event processing takes place.  This method
     * will <strong>only</strong> be called on a postback request that
     * is processing a form submit.  Customize this method to allocate
     * resources that will be required in your event handlers.</p>
     */
    public void preprocess() {
    }

    /**
     * <p>Callback method that is called just before rendering takes place.
     * This method will <strong>only</strong> be called for the page that
     * will actually be rendered (and not, for example, on a page that
     * handled a postback and then navigated to a different page).  Customize
     * this method to allocate resources that will be required for rendering
     * this page.</p>
     */
    public void prerender() {
    }

    /**
     * <p>Callback method that is called after rendering is completed for
     * this request, if <code>init()</code> was called (regardless of whether
     * or not this was the page that was actually rendered).  Customize this
     * method to release resources acquired in the <code>init()</code>,
     * <code>preprocess()</code>, or <code>prerender()</code> methods (or
     * acquired during execution of an event handler).</p>
     */
    public void destroy() {
    }
    public String workflow;

    public String submitWorkflow() {

        String swf = (String) WorkflowSubmissionPage.workflowsList.getValue();
        long selectedWorkflow = Long.parseLong(swf.trim());


        String inputName;
        if (swf.equals("1")) {
            inputName = "count";
        }
        if (swf.equals("5")) {
            inputName = "chromosome_name";
        }
        if (swf.equals("41")) {
            inputName = "No input!";
            inputText1.setRendered(false);
        } else {
            inputName = "UniprotIds";
        }

        try {
            RESTClient tavernaRESTClient = new RESTClient();

            //uri for the server
            tavernaRESTClient.setBaseURL("http://rpc103.cs.man.ac.uk:8081/taverna-server-0.2.1/rest");
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
            ds.put(inputName, new DataValue(jsftest.WorkflowSubmissionPage.inputText1.getText()));

            Long inputID = tavernaRESTClient.addData(new DataResource(ds));
            System.out.println("DataId :" + inputID);

            //workflow file to be run
            /*String workflowXML = IOUtils.toString(getClass().getResource("pipeline.t2flow").openStream());
            Long workflowID = tavernaRESTClient.addWorkflow(workflowXML);
            System.out.println("WorkflowId :" + workflowID);*/

            long startTime = System.currentTimeMillis();
            long duration = 0;
            long boo = 2;
            long jobID = tavernaRESTClient.addJob(selectedWorkflow, inputID);//put null in if no inputs

            this.jobID.setValue(jobID);
            //displays message to user giving job ID
            jobIDString = "Your job has the ID: " + jobID;
            System.out.println("JobId :" + jobID);


        /*while (!tavernaRESTClient.getJobStatus(jobID).equals("COMPLETE")) {
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
        System.out.println("Job took " + (duration / 1000f / 60f) + " minutes");*/

        } catch (Exception e) {
        }
        return null;
    }

    /*public void selectWorkflow(ValueChangeEvent event){


    System.out.println(workflowsList.getValue());

    if(workflowsList.getValue().equals("1")){
    System.out.println("pipeline");
    description.setValue("This workflow accepts any integer and will count that many values as the output");
    inputLabel.setValue("Number: ");
    }

    if(workflowsList.getValue().equals("5")){
    inputLabel.setValue("Chromosome: ");
    description.setValue("This workflow accepts a valid chicken chromosome number (1 - 32) and finds the genes associated with it");

    }

    }*/
}
