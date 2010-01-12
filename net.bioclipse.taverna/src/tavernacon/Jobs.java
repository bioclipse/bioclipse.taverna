/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tavernacon;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * A collection of jobs.
 *
 * @author David Withers
 */
@XmlRootElement(name = "Jobs")
public class Jobs {

	private Collection<JobResource> jobs;

	public Jobs() {
		this.jobs = new ArrayList<JobResource>();
	}

	public Jobs(Collection<JobResource> jobs) {
		this.jobs = jobs;
	}

	public Collection<JobResource> getJob() {
		return jobs;
	}

	public void setJob(Collection<JobResource> jobs) {
		this.jobs = jobs;
	}

}
