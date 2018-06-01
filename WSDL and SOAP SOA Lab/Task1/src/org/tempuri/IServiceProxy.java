package org.tempuri;

public class IServiceProxy implements org.tempuri.IService {
  private String _endpoint = null;
  private org.tempuri.IService iService = null;
  
  public IServiceProxy() {
    _initIServiceProxy();
  }
  
  public IServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initIServiceProxy();
  }
  
  private void _initIServiceProxy() {
    try {
      iService = (new org.tempuri.ServiceLocator()).getBasicHttpBinding_IService();
      if (iService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iService != null)
      ((javax.xml.rpc.Stub)iService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.IService getIService() {
    if (iService == null)
      _initIServiceProxy();
    return iService;
  }
  
  public java.lang.String getWeather(java.lang.String city_name, java.lang.String state_name) throws java.rmi.RemoteException{
    if (iService == null)
      _initIServiceProxy();
    return iService.getWeather(city_name, state_name);
  }
  
  public java.lang.String getWeather_hourly(java.lang.String city_name, java.lang.String state_name, java.lang.Boolean hourly) throws java.rmi.RemoteException{
    if (iService == null)
      _initIServiceProxy();
    return iService.getWeather_hourly(city_name, state_name, hourly);
  }
  
  public java.lang.String getWeather_tenDays(java.lang.String city_name, java.lang.String state_name, java.lang.Boolean tenday) throws java.rmi.RemoteException{
    if (iService == null)
      _initIServiceProxy();
    return iService.getWeather_tenDays(city_name, state_name, tenday);
  }
  
  
}