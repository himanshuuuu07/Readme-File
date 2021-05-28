package com.example.newtpo;

public class Student {

    String fullName, prn, branch, email, altEmail, dob, phone, phone_alt, linkdin_id, gender;
    String pAddress, rAddress, collgen12_diploma_Name, schoolName10;
    String passingYear, currentYear, age, passYear12_diploma, passingyear10;
    String percent12, percent10, userID;
    String spi_sem1, spi_sem2, spi_sem3, spi_sem4, spi_sem5, spi_sem6, cgpa;
    String isUser;

    public Student(String fullName, String prn, String branch, String email, String altEmail, String dob, String phone, String phone_alt, String linkdin_id, String gender, String pAddress, String rAddress, String collgen12_diploma_Name, String schoolName10, String passingYear, String currentYear, String age, String passYear12_diploma, String passingyear10, String percent12, String percent10, String userID, String spi_sem1, String spi_sem2, String spi_sem3, String spi_sem4, String spi_sem5, String spi_sem6, String cgpa, String isUser)
    {
        this.fullName = fullName;
        this.prn = prn;
        this.branch = branch;
        this.email = email;
        this.altEmail = altEmail;
        this.dob = dob;
        this.phone = phone;
        this.phone_alt = phone_alt;
        this.linkdin_id = linkdin_id;
        this.gender = gender;
        this.pAddress = pAddress;
        this.rAddress = rAddress;
        this.collgen12_diploma_Name = collgen12_diploma_Name;
        this.schoolName10 = schoolName10;
        this.passingYear = passingYear;
        this.currentYear = currentYear;
        this.age = age;
        this.passYear12_diploma = passYear12_diploma;
        this.passingyear10 = passingyear10;
        this.percent12 = percent12;
        this.percent10 = percent10;
        this.userID = userID;
        this.spi_sem1 = spi_sem1;
        this.spi_sem2 = spi_sem2;
        this.spi_sem3 = spi_sem3;
        this.spi_sem4 = spi_sem4;
        this.spi_sem5 = spi_sem5;
        this.spi_sem6 = spi_sem6;
        this.cgpa = cgpa;
        this.isUser = isUser;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPrn() {
        return prn;
    }

    public String getBranch() {
        return branch;
    }

    public String getEmail() {
        return email;
    }

    public String getAltEmail() {
        return altEmail;
    }

    public String getDob() {
        return dob;
    }

    public String getPhone() {
        return phone;
    }

    public String getPhone_alt() {
        return phone_alt;
    }

    public String getLinkdin_id() {
        return linkdin_id;
    }

    public String getGender() {
        return gender;
    }

    public String getpAddress() {
        return pAddress;
    }

    public String getrAddress() {
        return rAddress;
    }

    public String getCollgen12_diploma_Name() {
        return collgen12_diploma_Name;
    }

    public String getSchoolName10() {
        return schoolName10;
    }

    public String getPassingYear() {
        return passingYear;
    }

    public String getCurrentYear() {
        return currentYear;
    }

    public String getAge() {
        return age;
    }

    public String getPassYear12_diploma() {
        return passYear12_diploma;
    }

    public String getPassingyear10() {
        return passingyear10;
    }

    public String getPercent12() {
        return percent12;
    }

    public String getPercent10() {
        return percent10;
    }

    public String getUserID() {
        return userID;
    }

    public String getSpi_sem1() {
        return spi_sem1;
    }

    public String getSpi_sem2() {
        return spi_sem2;
    }

    public String getSpi_sem3() {
        return spi_sem3;
    }

    public String getSpi_sem4() {
        return spi_sem4;
    }

    public String getSpi_sem5() {
        return spi_sem5;
    }

    public String getSpi_sem6() {
        return spi_sem6;
    }

    public String getCgpa() {
        return cgpa;
    }

    public String getIsUser() {
        return isUser;
    }
}