package com.swankapps.corona.preventcorona.Model;

public class User {
    public static final String PYC_TABLE = "userlocaltable";
    public static final String COLUMN_COUNTRY = "country";
    public static final String COLUMN_ID = "userid";
    public static final String COLUMN_FULL_NAME = "username";
    public static final String COLUMN_EMAIL_ADD = "email";
    public static final String COLUMN_PROFIMAGE = "imageprof";
    public static final String COLUMN_DOB = "dob";
    public static final String COLUMN_STATUS = "userstatus";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_STAGE = "stage";
    public static final String PYCTABLE_CREATE = "CREATE TABLE " + PYC_TABLE + "("
            + COLUMN_ID + " TEXT,"
            + COLUMN_GENDER + " TEXT,"
            + COLUMN_STAGE + " TEXT,"
            + COLUMN_DOB + " TEXT,"
            + COLUMN_STATUS + " TEXT,"
            + COLUMN_FULL_NAME + " TEXT,"
            + COLUMN_EMAIL_ADD + " TEXT,"
            + COLUMN_PROFIMAGE + " TEXT,"
            + COLUMN_COUNTRY + " TEXT" + ")";
    private String userId;
    private String userImage;
    private String userName;
    private String userMail;
    private String userStatus;
    private String userDob;
    private String userGender;
    private String userCountry;
    private String userStage;


    public User(String userId, String userImage, String userName, String userMail, String userStatus, String userDob, String userGender, String userCountry, String userStage) {
        this.userId = userId;
        this.userImage = userImage;
        this.userName = userName;
        this.userMail = userMail;
        this.userStatus = userStatus;
        this.userDob = userDob;
        this.userGender = userGender;
        this.userCountry = userCountry;
        this.userStage = userStage;
    }

    public User() {
    }

    public String getUserStage() {
        return userStage;
    }

    public void setUserStage(String userStage) {
        this.userStage = userStage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserDob() {
        return userDob;
    }

    public void setUserDob(String userDob) {
        this.userDob = userDob;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }


}
