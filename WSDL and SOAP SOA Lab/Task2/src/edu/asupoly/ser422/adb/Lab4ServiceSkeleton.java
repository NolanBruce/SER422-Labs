/**
 * Lab4ServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */
package edu.asupoly.ser422.adb;

import edu.asupoly.ser422.lab4.*;

/**
 *  Lab4ServiceSkeleton java skeleton for the axisService
 */
public class Lab4ServiceSkeleton implements Lab4ServiceSkeletonInterface {
    /**
     * Auto generated method signature
     *
     * @param getSubjects0
     * @return getSubjectsResponse1
     */
    public edu.asupoly.ser422.lab4.GetSubjectsResponse getSubjects(
        edu.asupoly.ser422.lab4.GetSubjects getSubjects0) {
    	try {
	    	GetSubjectsResponse response = new GetSubjectsResponse();
	        response.set_return(Lab4Service.getService().getSubjects());
	        return response;
	    } catch (java.lang.Exception e) {
	    	e.printStackTrace();
	    }
	    return new GetSubjectsResponse();
    }

    /**
     * Auto generated method signature
     *
     * @param getService2
     * @return getServiceResponse3
     * @throws Lab4ServiceExceptionException
     */
    public edu.asupoly.ser422.lab4.GetServiceResponse getService(
        edu.asupoly.ser422.lab4.GetService getService2)
        throws Lab4ServiceExceptionException {
        //TODO : fill this with the necessary business logic
        throw new java.lang.UnsupportedOperationException("Intentionally not implemented:  " +
            this.getClass().getName() + "#getService");
    }

    /**
     * Auto generated method signature
     *
     * @param calculateGrade4
     * @return calculateGradeResponse5
     */
    public edu.asupoly.ser422.lab4.CalculateGradeResponse calculateGrade(
        edu.asupoly.ser422.lab4.CalculateGrade calculateGrade4) {
    	try {
	        CalculateGradeResponse response = new CalculateGradeResponse();
	        response.set_return(Lab4Service.getService().calculateGrade(calculateGrade4.getArgs0(), calculateGrade4.getArgs1()));
	        return response;
	    } catch (java.lang.Exception e ) {
	    	e.printStackTrace();
	    }
	    return new CalculateGradeResponse();
    }

    /**
     * Auto generated method signature
     *
     * @param mapToLetterGrade6
     * @return mapToLetterGradeResponse7
     */
    public edu.asupoly.ser422.lab4.MapToLetterGradeResponse mapToLetterGrade(
        edu.asupoly.ser422.lab4.MapToLetterGrade mapToLetterGrade6) {
    	try {
	        MapToLetterGradeResponse response = new MapToLetterGradeResponse();
	        response.set_return(Lab4Service.getService().mapToLetterGrade(mapToLetterGrade6.getArgs0()));
	        return response;
	    } catch (java.lang.Exception e ) {
	    	e.printStackTrace();
	    }
	    return new MapToLetterGradeResponse();
    }
}
