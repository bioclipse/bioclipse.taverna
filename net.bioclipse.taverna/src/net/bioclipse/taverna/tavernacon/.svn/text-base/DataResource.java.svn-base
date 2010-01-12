/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tavernacon;

import java.net.URI;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Data")

/**
 *
 * @author Vijay
 */
public class DataResource {

    private Map<String, DataValue> dataMap;

	public DataResource() {
	}

    public DataResource(Map<String, DataValue> dataMap) {
		this.dataMap = dataMap;
	}

    public Map<String, DataValue> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, DataValue> dataMap) {
		this.dataMap = dataMap;
	}

    @Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("---------------------------------------");
		sb.append("::");
		sb.append("DataResource");
		sb.append("::");
		sb.append(super.toString());
		sb.append("DataMap : { ");
		sb.append("::");
		Map<String, DataValue> dataMap = getDataMap();
		for (Entry<String, DataValue> entry : dataMap.entrySet()) {
			sb.append("Port '" + entry.getKey() + "' = '" + entry.getValue() + "'");
			sb.append("::");
		}
		sb.append("}");
		sb.append("::");
		sb.append("---------------------------------------");
		sb.append("::");
		return sb.toString();
	}


}
