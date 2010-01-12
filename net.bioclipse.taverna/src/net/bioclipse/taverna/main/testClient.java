package net.bioclipse.taverna.main;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.bioclipse.taverna.tavernacon.DataResource;
import net.bioclipse.taverna.tavernacon.DataValue;
import net.bioclipse.taverna.tavernacon.JobResource;
import net.bioclipse.taverna.tavernacon.RESTClient;

import org.apache.commons.io.IOUtils;


public class testClient {

    public static void main(String[] args) throws Exception {
        
//        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
//        System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
//        System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire.header", "debug");
//        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "debug");
        
        testClient tc = new testClient();
        tc.testRunWorkflow();
    }

    public String testRunWorkflow() {
        
        System.out.println("Start");

        String inputName;
        inputName = "count";
        inputName = "chromosome_name";

        try {
            RESTClient tavernaRESTClient = new RESTClient();

            //uri for the server
            tavernaRESTClient.setBaseURL("http://ws1.bmc.uu.se:8080/taverna-server-0.2.1/rest");

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
            ds.put(inputName, new DataValue("12345"));

            Long inputID = tavernaRESTClient.addData(new DataResource(ds));
            System.out.println("DataId :" + inputID);

            //workflow file to be run
            String workflowXML = IOUtils.toString(getClass().getResource("pipeline.t2flow").openStream());
            Long workflowID = tavernaRESTClient.addWorkflow(workflowXML);
            System.out.println("WorkflowId :" + workflowID);

            long startTime = System.currentTimeMillis();
            long duration = 0;
            long boo = 2;
            long jobID = tavernaRESTClient.addJob(new Long(1), inputID);//put null in if no inputs

            //displays message to user giving job ID
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

            //  tavernaRESTClient.deleteWorkflow(workflowID);
            //  tavernaRESTClient.deleteJob(jobID);
            //  tavernaRESTClient.deleteData(inputID);
            //  tavernaRESTClient.deleteData(outputID);
            System.out.println("Job took " + (duration / 1000f / 60f) + " minutes");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
