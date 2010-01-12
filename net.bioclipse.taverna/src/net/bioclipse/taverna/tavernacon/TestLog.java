/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.bioclipse.taverna.tavernacon;

import java.io.PrintStream;

import org.apache.commons.logging.Log;

/**
 *
 * @author Vijay
 */

public class TestLog implements Log {

	private PrintStream out = System.out;

	public void debug(Object message) {
		out.println(message);
	}

	public void debug(Object message, Throwable t) {
		out.println(message);
	}

	public void error(Object message) {
		out.println(message);
	}

	public void error(Object message, Throwable t) {
		out.println(message);
	}

	public void fatal(Object message) {
		out.println(message);
	}

	public void fatal(Object message, Throwable t) {
		out.println(message);
	}

	public void info(Object message) {
		out.println(message);
	}

	public void info(Object message, Throwable t) {
		out.println(message);
	}

	public boolean isDebugEnabled() {
		return true;
	}

	public boolean isErrorEnabled() {
		return true;
	}

	public boolean isFatalEnabled() {
		return true;
	}

	public boolean isInfoEnabled() {
		return true;
	}

	public boolean isTraceEnabled() {
		return true;
	}

	public boolean isWarnEnabled() {
		return true;
	}

	public void trace(Object message) {
		out.println(message);
	}

	public void trace(Object message, Throwable t) {
		out.println(message);
	}

	public void warn(Object message) {
		out.println(message);
	}

	public void warn(Object message, Throwable t) {
		out.println(message);
	}

}
