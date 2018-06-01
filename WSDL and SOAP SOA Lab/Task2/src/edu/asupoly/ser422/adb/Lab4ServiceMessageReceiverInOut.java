/**
 * Lab4ServiceMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */
package edu.asupoly.ser422.adb;


/**
 *  Lab4ServiceMessageReceiverInOut message receiver
 */
public class Lab4ServiceMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver {
    public void invokeBusinessLogic(
        org.apache.axis2.context.MessageContext msgContext,
        org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault {
        try {
            // get the implementation class for the Web Service
            Object obj = getTheImplementationObject(msgContext);

            Lab4ServiceSkeletonInterface skel = (Lab4ServiceSkeletonInterface) obj;

            //Out Envelop
            org.apache.axiom.soap.SOAPEnvelope envelope = null;

            //Find the axisOperation that has been set by the Dispatch phase.
            org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext()
                                                                      .getAxisOperation();

            if (op == null) {
                throw new org.apache.axis2.AxisFault(
                    "Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
            }

            java.lang.String methodName;

            if ((op.getName() != null) &&
                    ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(
                            op.getName().getLocalPart())) != null)) {
                if ("getSubjects".equals(methodName)) {
                    edu.asupoly.ser422.lab4.GetSubjectsResponse getSubjectsResponse25 =
                        null;
                    edu.asupoly.ser422.lab4.GetSubjects wrappedParam = (edu.asupoly.ser422.lab4.GetSubjects) fromOM(msgContext.getEnvelope()
                                                                                                                              .getBody()
                                                                                                                              .getFirstElement(),
                            edu.asupoly.ser422.lab4.GetSubjects.class);

                    getSubjectsResponse25 = skel.getSubjects(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            getSubjectsResponse25, false,
                            new javax.xml.namespace.QName(
                                "http://lab4.ser422.asupoly.edu",
                                "getSubjectsResponse"));
                } else
                 if ("getService".equals(methodName)) {
                    edu.asupoly.ser422.lab4.GetServiceResponse getServiceResponse27 =
                        null;
                    edu.asupoly.ser422.lab4.GetService wrappedParam = (edu.asupoly.ser422.lab4.GetService) fromOM(msgContext.getEnvelope()
                                                                                                                            .getBody()
                                                                                                                            .getFirstElement(),
                            edu.asupoly.ser422.lab4.GetService.class);

                    getServiceResponse27 = skel.getService(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            getServiceResponse27, false,
                            new javax.xml.namespace.QName(
                                "http://lab4.ser422.asupoly.edu",
                                "getServiceResponse"));
                } else
                 if ("calculateGrade".equals(methodName)) {
                    edu.asupoly.ser422.lab4.CalculateGradeResponse calculateGradeResponse29 =
                        null;
                    edu.asupoly.ser422.lab4.CalculateGrade wrappedParam = (edu.asupoly.ser422.lab4.CalculateGrade) fromOM(msgContext.getEnvelope()
                                                                                                                                    .getBody()
                                                                                                                                    .getFirstElement(),
                            edu.asupoly.ser422.lab4.CalculateGrade.class);

                    calculateGradeResponse29 = skel.calculateGrade(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            calculateGradeResponse29, false,
                            new javax.xml.namespace.QName(
                                "http://lab4.ser422.asupoly.edu",
                                "calculateGradeResponse"));
                } else
                 if ("mapToLetterGrade".equals(methodName)) {
                    edu.asupoly.ser422.lab4.MapToLetterGradeResponse mapToLetterGradeResponse31 =
                        null;
                    edu.asupoly.ser422.lab4.MapToLetterGrade wrappedParam = (edu.asupoly.ser422.lab4.MapToLetterGrade) fromOM(msgContext.getEnvelope()
                                                                                                                                        .getBody()
                                                                                                                                        .getFirstElement(),
                            edu.asupoly.ser422.lab4.MapToLetterGrade.class);

                    mapToLetterGradeResponse31 = skel.mapToLetterGrade(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            mapToLetterGradeResponse31, false,
                            new javax.xml.namespace.QName(
                                "http://lab4.ser422.asupoly.edu",
                                "mapToLetterGradeResponse"));
                } else {
                    throw new java.lang.RuntimeException("method not found");
                }

                newMsgContext.setEnvelope(envelope);
            }
        } catch (Lab4ServiceExceptionException e) {
            msgContext.setProperty(org.apache.axis2.Constants.FAULT_NAME,
                "Lab4ServiceException");

            org.apache.axis2.AxisFault f = createAxisFault(e);

            if (e.getFaultMessage() != null) {
                f.setDetail(toOM(e.getFaultMessage(), false));
            }

            throw f;
        }
        catch (java.lang.Exception e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    //
    private org.apache.axiom.om.OMElement toOM(
        edu.asupoly.ser422.lab4.GetSubjects param, boolean optimizeContent)
        throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(edu.asupoly.ser422.lab4.GetSubjects.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        edu.asupoly.ser422.lab4.GetSubjectsResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(edu.asupoly.ser422.lab4.GetSubjectsResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        edu.asupoly.ser422.lab4.GetService param, boolean optimizeContent)
        throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(edu.asupoly.ser422.lab4.GetService.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        edu.asupoly.ser422.lab4.GetServiceResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(edu.asupoly.ser422.lab4.GetServiceResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        edu.asupoly.ser422.lab4.Lab4ServiceException param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(edu.asupoly.ser422.lab4.Lab4ServiceException.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        edu.asupoly.ser422.lab4.CalculateGrade param, boolean optimizeContent)
        throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(edu.asupoly.ser422.lab4.CalculateGrade.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        edu.asupoly.ser422.lab4.CalculateGradeResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(edu.asupoly.ser422.lab4.CalculateGradeResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        edu.asupoly.ser422.lab4.MapToLetterGrade param, boolean optimizeContent)
        throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(edu.asupoly.ser422.lab4.MapToLetterGrade.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        edu.asupoly.ser422.lab4.MapToLetterGradeResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(edu.asupoly.ser422.lab4.MapToLetterGradeResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        edu.asupoly.ser422.lab4.GetSubjectsResponse param,
        boolean optimizeContent, javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    edu.asupoly.ser422.lab4.GetSubjectsResponse.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private edu.asupoly.ser422.lab4.GetSubjectsResponse wrapgetSubjects() {
        edu.asupoly.ser422.lab4.GetSubjectsResponse wrappedElement = new edu.asupoly.ser422.lab4.GetSubjectsResponse();

        return wrappedElement;
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        edu.asupoly.ser422.lab4.GetServiceResponse param,
        boolean optimizeContent, javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    edu.asupoly.ser422.lab4.GetServiceResponse.MY_QNAME, factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private edu.asupoly.ser422.lab4.GetServiceResponse wrapgetService() {
        edu.asupoly.ser422.lab4.GetServiceResponse wrappedElement = new edu.asupoly.ser422.lab4.GetServiceResponse();

        return wrappedElement;
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        edu.asupoly.ser422.lab4.CalculateGradeResponse param,
        boolean optimizeContent, javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    edu.asupoly.ser422.lab4.CalculateGradeResponse.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private edu.asupoly.ser422.lab4.CalculateGradeResponse wrapcalculateGrade() {
        edu.asupoly.ser422.lab4.CalculateGradeResponse wrappedElement = new edu.asupoly.ser422.lab4.CalculateGradeResponse();

        return wrappedElement;
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        edu.asupoly.ser422.lab4.MapToLetterGradeResponse param,
        boolean optimizeContent, javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    edu.asupoly.ser422.lab4.MapToLetterGradeResponse.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private edu.asupoly.ser422.lab4.MapToLetterGradeResponse wrapmapToLetterGrade() {
        edu.asupoly.ser422.lab4.MapToLetterGradeResponse wrappedElement = new edu.asupoly.ser422.lab4.MapToLetterGradeResponse();

        return wrappedElement;
    }

    /**
     *  get the default envelope
     */
    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory) {
        return factory.getDefaultEnvelope();
    }

    private java.lang.Object fromOM(org.apache.axiom.om.OMElement param,
        java.lang.Class type) throws org.apache.axis2.AxisFault {
        try {
            if (edu.asupoly.ser422.lab4.CalculateGrade.class.equals(type)) {
                return edu.asupoly.ser422.lab4.CalculateGrade.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (edu.asupoly.ser422.lab4.CalculateGradeResponse.class.equals(
                        type)) {
                return edu.asupoly.ser422.lab4.CalculateGradeResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (edu.asupoly.ser422.lab4.GetService.class.equals(type)) {
                return edu.asupoly.ser422.lab4.GetService.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (edu.asupoly.ser422.lab4.GetServiceResponse.class.equals(type)) {
                return edu.asupoly.ser422.lab4.GetServiceResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (edu.asupoly.ser422.lab4.GetSubjects.class.equals(type)) {
                return edu.asupoly.ser422.lab4.GetSubjects.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (edu.asupoly.ser422.lab4.GetSubjectsResponse.class.equals(type)) {
                return edu.asupoly.ser422.lab4.GetSubjectsResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (edu.asupoly.ser422.lab4.Lab4ServiceException.class.equals(type)) {
                return edu.asupoly.ser422.lab4.Lab4ServiceException.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (edu.asupoly.ser422.lab4.MapToLetterGrade.class.equals(type)) {
                return edu.asupoly.ser422.lab4.MapToLetterGrade.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (edu.asupoly.ser422.lab4.MapToLetterGradeResponse.class.equals(
                        type)) {
                return edu.asupoly.ser422.lab4.MapToLetterGradeResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }
        } catch (java.lang.Exception e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

        return null;
    }

    private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
        org.apache.axis2.AxisFault f;
        Throwable cause = e.getCause();

        if (cause != null) {
            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
        } else {
            f = new org.apache.axis2.AxisFault(e.getMessage());
        }

        return f;
    }
} //end of class
