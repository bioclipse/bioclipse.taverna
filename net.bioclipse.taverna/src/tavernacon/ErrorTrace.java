/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tavernacon;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ErrorTrace")
public class ErrorTrace {
	private String className, methodName, fileName;
	private int lineNumber;

	public ErrorTrace() {			
	}

	public ErrorTrace(String declaringClass, String methodName,
			String fileName, int lineNumber) {
		this.className = declaringClass;
		this.methodName = methodName;
		this.fileName = fileName;
		this.lineNumber = lineNumber;
	}

	/**
	 * Returns the className.
	 *
	 * @return the value of className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * Sets the className.
	 *
	 * @param className the new value for className
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * Returns the methodName.
	 *
	 * @return the value of methodName
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * Sets the methodName.
	 *
	 * @param methodName the new value for methodName
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * Returns the fileName.
	 *
	 * @return the value of fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the fileName.
	 *
	 * @param fileName the new value for fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Returns the lineNumber.
	 *
	 * @return the value of lineNumber
	 */
	public int getLineNumber() {
		return lineNumber;
	}

	/**
	 * Sets the lineNumber.
	 *
	 * @param lineNumber the new value for lineNumber
	 */
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

    @Override
	public String toString() {
        return getClassName() + "." + methodName +
            (lineNumber == -2 ? "(Native Method)" :
             (fileName != null && lineNumber >= 0 ?
              "(" + fileName + ":" + lineNumber + ")" :
              (fileName != null ?  "("+fileName+")" : "(Unknown Source)")));
    }

}