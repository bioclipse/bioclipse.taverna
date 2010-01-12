/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tavernacon;

public class Job extends IdentifiableImpl {

	private Long workflow, inputs, outputs;

	private String status;

	/**
	 * Returns the id of the workflow.
	 *
	 * @return the id of the workflow
	 */
	public Long getWorkflow() {
		return workflow;
	}

	/**
	 * Sets the id of the workflow.
	 *
	 * @param workflow the id of the workflow
	 */
	public void setWorkflow(Long workflow) {
		this.workflow = workflow;
	}

	/**
	 * Returns the id of the workflow inputs.
	 *
	 * @return the id of the workflow inputs
	 */
	public Long getInputs() {
		return inputs;
	}

	/**
	 * Sets the id of the workflow inputs.
	 *
	 * @param inputs the id of the workflow inputs
	 */
	public void setInputs(Long inputs) {
		this.inputs = inputs;
	}

	/**
	 * Returns the id of the workflow outputs.
	 *
	 * @return the id of the workflow outputs
	 */
	public Long getOutputs() {
		return outputs;
	}

	/**
	 * Sets the id of the workflow outputs
	 *
	 * @param outputs the id of the workflow outputs
	 */
	public void setOutputs(Long outputs) {
		this.outputs = outputs;
	}

	/**
	 * Returns the status of the <code>Job</code>.
	 *
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the <code>Job</code> status. Valid values are :
	 * <p>
	 * CREATED, INITIALISING, RUNNING, COMPLETE, CANCELLING, CANCELLED, FAILED,
	 * PAUSED
	 *
	 * @param status the current status of the <code>Job</code>
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((inputs == null) ? 0 : inputs.hashCode());
		result = prime * result + ((outputs == null) ? 0 : outputs.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((workflow == null) ? 0 : workflow.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Job other = (Job) obj;
		if (inputs == null) {
			if (other.inputs != null) {
				return false;
			}
		} else if (!inputs.equals(other.inputs)) {
			return false;
		}
		if (outputs == null) {
			if (other.outputs != null) {
				return false;
			}
		} else if (!outputs.equals(other.outputs)) {
			return false;
		}
		if (status == null) {
			if (other.status != null) {
				return false;
			}
		} else if (!status.equals(other.status)) {
			return false;
		}
		if (workflow == null) {
			if (other.workflow != null) {
				return false;
			}
		} else if (!workflow.equals(other.workflow)) {
			return false;
		}
		return true;
	}

}
