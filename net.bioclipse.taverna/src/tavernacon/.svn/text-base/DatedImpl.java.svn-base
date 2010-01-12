/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tavernacon;

import java.util.Date;

/**
 * Implementation of the {@link Dated} interface.
 *
 * @author David Withers
 */
public class DatedImpl implements Dated {

	private Date created = new Date();

	private Date modified = created;

	public Date getModified() {
		return modified;
	}

	public void updateModified() {
		modified = new Date();
	}

	public Date getCreated() {
		return created;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result
				+ ((modified == null) ? 0 : modified.hashCode());
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
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		DatedImpl other = (DatedImpl) obj;
		if (created == null) {
			if (other.created != null) {
				return false;
			}
		} else if (!created.equals(other.created)) {
			return false;
		}
		if (modified == null) {
			if (other.modified != null) {
				return false;
			}
		} else if (!modified.equals(other.modified)) {
			return false;
		}
		return true;
	}

}
