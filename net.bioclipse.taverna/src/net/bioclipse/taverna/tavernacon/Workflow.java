/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.bioclipse.taverna.tavernacon;

/**
 *
 * @author Vijay
 */
public class Workflow extends IdentifiableImpl {

	private String xml;

	private boolean enabled;

	/**
	 * Returns the serialized form of a workflow.
	 *
	 * @return the serialized form of a workflow
	 */
	public String getXml() {
		return xml;
	}

	/**
	 * Sets the serialized form of a workflow.
	 *
	 * @param xml the serialized form of a workflow
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((xml == null) ? 0 : xml.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		Workflow other = (Workflow) obj;
		if (enabled != other.enabled) {
			return false;
		}
		if (xml == null) {
			if (other.xml != null) {
				return false;
			}
		} else if (!xml.equals(other.xml)) {
			return false;
		}
		return true;
	}

}

