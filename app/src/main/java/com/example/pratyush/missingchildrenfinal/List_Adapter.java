package com.example.pratyush.missingchildrenfinal;
public class List_Adapter {
    private String mPerson_name;
    private String mPerson_Image;
    private String mPerson_Detail;

    public List_Adapter(String person_name, String person_image, String person_details){
        mPerson_name=person_name;
        mPerson_Image=person_image;
        mPerson_Detail=person_details;
    }

    public String getmPerson_name(){
        return mPerson_name;
    }

    public String getmPerson_Image(){
        return mPerson_Image;
    }

    public String getmPerson_Detail(){
        return mPerson_Detail;
    }
}