package edu.asupoly.ser422.lab4;

import java.util.List;
import java.util.ArrayList;

public class Student {
    private String __firstName;
    private String __lastName;
    private String __year;
    private String __major;
    private double __grade;
    
    public Student(String fname, String lname, String year, String major, double grade) {
	__firstName = fname;
	__lastName  = lname;
	__year      = year;
	__major     = major;
	__grade     = grade;
    }

    public String getFirstName() { return __firstName; }
    public String getLastName()  { return __lastName; }
    public String getYear()      { return __year; }
    public String getMajor()     { return __major; }
    public double getGrade()     { return __grade; }
}
	
