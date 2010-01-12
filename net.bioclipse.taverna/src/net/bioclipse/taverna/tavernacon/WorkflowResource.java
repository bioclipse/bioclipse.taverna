/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.bioclipse.taverna.tavernacon;

/**
 *
 * @author Vijay
 */
import java.net.URI;

import javax.xml.bind.annotation.XmlRootElement;

//import net.sf.taverna.t2.service.model.Workflow;


@XmlRootElement(name = "Workflow")
public class WorkflowResource extends Resource {

	private String xml;

	private boolean enabled;

	public WorkflowResource() {
	}

	public WorkflowResource(Workflow workflow, URI uri) {
		super(workflow, uri);
		setXml(workflow.getXml());
		setEnabled(workflow.isEnabled());
	}

	/**
	 * Returns the workflow xml.
	 *
	 * @return the value of workflow xml
	 */
	public String getXml() {
		return xml;
	}

	/**
	 * Sets the workflow xml.
	 *
	 * @param xml the new value for workflow xml
	 */
	public void setXml(String xml) {
		this.xml = xml;
	}

	/**
	 * Returns the enabled.
	 *
	 * @return the value of enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Sets the enabled.
	 *
	 * @param enabled the new value for enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
