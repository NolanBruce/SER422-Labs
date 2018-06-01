/**
 * Lab4ServiceSkeletonInterface.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */
package edu.asupoly.ser422.adb;

/**
 *  Lab4ServiceSkeletonInterface java skeleton interface for the axisService
 */
public interface Lab4ServiceSkeletonInterface {
    /**
     * Auto generated method signature
     *
     * @param getSubjects
     */
    public edu.asupoly.ser422.lab4.GetSubjectsResponse getSubjects(
        edu.asupoly.ser422.lab4.GetSubjects getSubjects);

    /**
     * Auto generated method signature
     *
     * @param getService
     * @throws Lab4ServiceExceptionException :
     */
    public edu.asupoly.ser422.lab4.GetServiceResponse getService(
        edu.asupoly.ser422.lab4.GetService getService)
        throws Lab4ServiceExceptionException;

    /**
     * Auto generated method signature
     *
     * @param calculateGrade
     */
    public edu.asupoly.ser422.lab4.CalculateGradeResponse calculateGrade(
        edu.asupoly.ser422.lab4.CalculateGrade calculateGrade);

    /**
     * Auto generated method signature
     *
     * @param mapToLetterGrade
     */
    public edu.asupoly.ser422.lab4.MapToLetterGradeResponse mapToLetterGrade(
        edu.asupoly.ser422.lab4.MapToLetterGrade mapToLetterGrade);
}
