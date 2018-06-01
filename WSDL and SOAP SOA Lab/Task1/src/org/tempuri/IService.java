/**
 * IService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public interface IService extends java.rmi.Remote {
    public java.lang.String getWeather(java.lang.String city_name, java.lang.String state_name) throws java.rmi.RemoteException;
    public java.lang.String getWeather_hourly(java.lang.String city_name, java.lang.String state_name, java.lang.Boolean hourly) throws java.rmi.RemoteException;
    public java.lang.String getWeather_tenDays(java.lang.String city_name, java.lang.String state_name, java.lang.Boolean tenday) throws java.rmi.RemoteException;
}
