package com.example.adhdforparent2;

public class child_data {

    String childId;
    String childName;
    String childAge;
    String childGender;

    public child_data(){
//        this is super important
    }

    public child_data(String childId, String childName, String childAge, String childGender) {
        this.childId = childId;
        this.childName = childName;
        this.childAge = childAge;
        this.childGender = childGender;
    }

    public String getChildId() {
        return childId;
    }

    public String getChildName() {
        return childName;
    }

    public String getChildAge() {
        return childAge;
    }

    public String getChildGender() {
        return childGender;
    }

}
