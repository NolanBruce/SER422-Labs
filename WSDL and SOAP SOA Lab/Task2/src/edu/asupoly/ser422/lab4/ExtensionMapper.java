/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:50 GMT)
 */
package edu.asupoly.ser422.lab4;


/**
 *  ExtensionMapper class
 */
@SuppressWarnings({"unchecked",
    "unused"
})
public class ExtensionMapper {
    public static java.lang.Object getTypeObject(
        java.lang.String namespaceURI, java.lang.String typeName,
        javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
        if ("http://lab4.ser422.asupoly.edu".equals(namespaceURI) &&
                "Exception".equals(typeName)) {
            return edu.asupoly.ser422.lab4.Exception.Factory.parse(reader);
        }

        if ("http://lab4.ser422.asupoly.edu/xsd".equals(namespaceURI) &&
                "Lab4Service".equals(typeName)) {
            return edu.asupoly.ser422.lab4.xsd.Lab4Service.Factory.parse(reader);
        }

        throw new org.apache.axis2.databinding.ADBException("Unsupported type " +
            namespaceURI + " " + typeName);
    }
}
