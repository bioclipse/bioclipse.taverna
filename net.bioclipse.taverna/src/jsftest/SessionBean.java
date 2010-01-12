/*
 * SessionBean1.java
 *
 * Created on 22-Sep-2009, 15:18:33
 * Copyright me
 */
package jsftest;

import com.sun.rave.web.ui.appbase.AbstractSessionBean;
import java.util.HashMap;
import javax.faces.FacesException;
import javax.faces.event.ValueChangeEvent;

import net.bioclipse.taverna.tavernacon.DataValue;

/**
 * <p>Session scope data bean for your application.  Create properties
 *  here to represent cached data that should be made available across
 *  multiple HTTP requests for an individual user.</p>
 *
 * <p>An instance of this class will be created for you automatically,
 * the first time your application evaluates a value binding expression
 * or method binding expression that references a managed bean using
 * this class.</p>
 */
public class SessionBean extends AbstractSessionBean {
    // <editor-fold defaultstate="collapsed" desc="Managed Component Definition">
    private int __placeholder;

    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

        HashMap<String, DataValue[]> jobOutputs = new HashMap<String, DataValue[]>();
    /**
     * <p>Construct a new session data bean instance.</p>
     */
    public SessionBean() {
    }

    /**
     * <p>This method is called when this bean is initially added to
     * session scope.  Typically, this occurs as a result of evaluating
     * a value binding or method binding expression, which utilizes the
     * managed bean facility to instantiate this bean and store it into
     * session scope.</p>
     * 
     * <p>You may customize this method to initialize and cache data values
     * or resources that are required for the lifetime of a particular
     * user session.</p>
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
            log("SessionBean1 Initialization Failure", e);
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
        }
        
        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here
    }

    /**
     * <p>This method is called when the session containing it is about to be
     * passivated.  Typically, this occurs in a distributed servlet container
     * when the session is about to be transferred to a different
     * container instance, after which the <code>activate()</code> method
     * will be called to indicate that the transfer is complete.</p>
     * 
     * <p>You may customize this method to release references to session data
     * or resources that can not be serialized with the session itself.</p>
     */
    public void passivate() {
    }

    /**
     * <p>This method is called when the session containing it was
     * reactivated.</p>
     * 
     * <p>You may customize this method to reacquire references to session
     * data or resources that could not be serialized with the
     * session itself.</p>
     */
    public void activate() {
    }

    /**
     * <p>This method is called when this bean is removed from
     * session scope.  Typically, this occurs as a result of
     * the session timing out or being terminated by the application.</p>
     * 
     * <p>You may customize this method to clean up resources allocated
     * during the execution of the <code>init()</code> method, or
     * at any later time during the lifetime of the application.</p>
     */
    public void destroy() {
    }

    public void selectWorkflow(ValueChangeEvent event){


        System.out.println(WorkflowSubmissionPage.workflowsList.getValue());

        if(WorkflowSubmissionPage.workflowsList.getValue().equals("1")){
            System.out.println("pipeline");
            WorkflowSubmissionPage.description.setValue("This workflow accepts any integer and will count that many values as the output");
            WorkflowSubmissionPage.inputLabel.setValue("Number: ");
            WorkflowSubmissionPage.inputText1.setVisible(true);
        }

        if(WorkflowSubmissionPage.workflowsList.getValue().equals("5")){
            WorkflowSubmissionPage.inputLabel.setValue("Chromosome: ");
            WorkflowSubmissionPage.description.setValue("This workflow accepts a valid chicken chromosome (1 - 28, Z or W) and finds the genes associated with it");
            WorkflowSubmissionPage.inputText1.setVisible(true);
        }

        if(WorkflowSubmissionPage.workflowsList.getValue().equals("39")){
            WorkflowSubmissionPage.inputLabel.setValue("Uniprot ID: ");
            WorkflowSubmissionPage.description.setValue("This workflow accepts a Uniprot ID eg. A2ABK7 but produces an error for testing purposes");
            WorkflowSubmissionPage.inputText1.setVisible(true);
        }

        if(WorkflowSubmissionPage.workflowsList.getValue().equals("41")){
            WorkflowSubmissionPage.inputLabel.setValue("No input");
            WorkflowSubmissionPage.description.setValue("This workflow accepts no input and produces an error for testing purposes");
            WorkflowSubmissionPage.inputText1.setVisible(false);
        }

    }
}
