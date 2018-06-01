/**
 * Lab4ServiceExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */
package edu.asupoly.ser422.adb;

public class Lab4ServiceExceptionException extends java.lang.Exception {
    private static final long serialVersionUID = 1523726504210L;
    private edu.asupoly.ser422.lab4.Lab4ServiceException faultMessage;

    public Lab4ServiceExceptionException() {
        super("Lab4ServiceExceptionException");
    }

    public Lab4ServiceExceptionException(java.lang.String s) {
        super(s);
    }

    public Lab4ServiceExceptionException(java.lang.String s,
        java.lang.Throwable ex) {
        super(s, ex);
    }

    public Lab4ServiceExceptionException(java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        edu.asupoly.ser422.lab4.Lab4ServiceException msg) {
        faultMessage = msg;
    }

    public edu.asupoly.ser422.lab4.Lab4ServiceException getFaultMessage() {
        return faultMessage;
    }
}
