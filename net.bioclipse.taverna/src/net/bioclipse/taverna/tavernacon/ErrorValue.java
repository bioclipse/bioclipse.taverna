package net.bioclipse.taverna.tavernacon;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * A workflow result returned when an error has occurred. This is the value of a
 * {@link DataValue} if DataValue.containsError() returns true and the DataValue
 * does not contain a list.
 *
 * @author David Withers
 */
@XmlRootElement(name = "ErrorValue")
public class ErrorValue {

	private String exceptionMessage = "";
	private String message = "";
	private List<ErrorTrace> stackTrace;

	/**
	 * Returns the exceptionMessage.
	 *
	 * @return the value of exceptionMessage
	 */
	public String getExceptionMessage() {
		return exceptionMessage;
	}

	/**
	 * Sets the exceptionMessage.
	 *
	 * @param exceptionMessage
	 *            the new value for exceptionMessage
	 */
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	/**
	 * Returns the message.
	 *
	 * @return the value of message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message
	 *            the new value for message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Returns the stackTrace.
	 *
	 * @return the value of stackTrace
	 */
	public List<ErrorTrace> getStackTrace() {
		return stackTrace;
	}

	/**
	 * Sets the stackTrace.
	 *
	 * @param stackTrace
	 *            the new value for stackTrace
	 */
	public void setStackTrace(List<ErrorTrace> stackTrace) {
		this.stackTrace = stackTrace;
	}
/*
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ERROR");
		if (getMessage() != null) {
			sb.append(" : ");
			sb.append(getMessage());
		}
		sb.append(Resource.lineSeparator);
		if (getExceptionMessage() != null) {
			sb.append(getExceptionMessage());
			sb.append(Resource.lineSeparator);
		}
		List<ErrorTrace> stackTrace = getStackTrace();
		if (stackTrace != null) {
			for (ErrorTrace errorTrace : stackTrace) {
				sb.append("  ");
				sb.append(errorTrace);
				sb.append(Resource.lineSeparator);
			}
		}
		return sb.toString();

 }
 */
}
