/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.bioclipse.taverna.tavernacon;

import java.util.Date;

/**
 * Interface for objects that have created and modified times.
 *
 * @author David Withers
 */
public interface Dated {

	/**
	 * Returns the modified time.
	 *
	 * @return the modified time
	 */
	public Date getModified();

	/**
	 * Updates the modified time to the current time.
	 */
	public void updateModified();

	/**
	 * Returns the created time.
	 *
	 * @return the created time
	 */
	public Date getCreated();

}