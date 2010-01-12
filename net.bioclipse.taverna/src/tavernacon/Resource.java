/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tavernacon;


import java.net.URI;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Vijay
 */
@XmlRootElement(name = "Resource")
public class Resource {

	private Date created;

	private Date lastModified;

	private Long id;

	private URI uri;

	public static final String lineSeparator = System.getProperty("line.separator");

	public Resource() {
	}

	public Resource(Identifiable<Long> identifiable, URI uri) {
		this.id = identifiable.getId();
		this.created = identifiable.getCreated();
		this.lastModified = identifiable.getModified();
		this.uri = uri;
	}

	/**
	 * Returns the created time.
	 *
	 * @return the created time
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * Sets the created time.
	 *
	 * @param created the new value for created time
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * Returns the lastModified time.
	 *
	 * @return the value of lastModified time
	 */
	public Date getLastModified() {
		return lastModified;
	}

	/**
	 * Sets the lastModified.
	 *
	 * @param lastModified the new value for lastModified
	 */
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	/**
	 * Returns the id.
	 *
	 * @return the value of id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new value for id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Returns the uri.
	 *
	 * @return the value of uri
	 */
	public URI getUri() {
		return uri;
	}

	/**
	 * Sets the uri.
	 *
	 * @param uri the new value for uri
	 */
	public void setUri(URI uri) {
		this.uri = uri;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ID : ");
		sb.append(getId());
		sb.append(lineSeparator);
		sb.append("Created : ");
		sb.append(getCreated());
		sb.append(lineSeparator);
		sb.append("Modified : ");
		sb.append(getLastModified());
		sb.append(lineSeparator);
		sb.append("URI : ");
		sb.append(getUri());
		sb.append(lineSeparator);
		return sb.toString();
	}
}
