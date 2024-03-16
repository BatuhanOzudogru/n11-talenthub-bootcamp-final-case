package com.batuhanozudogru.userservice.general.message;

public class Message {

    public static final String CREATED = "Data successfully saved";

    public static final String UPDATED = "Data successfully updated";

    public static final String OK = "Process successfully executed";

    public static final String HARD_DELETED = "Data successfully deleted";

    public static final String SOFT_DELETED = "Data successfully deactivated";
    public static final String ACTIVATED = "Data successfully activated";

    public static final String ALL_DELETED = "All data successfully deleted";

    public static final String RESTAURANT_NOT_FOUND = "Restaurant not found";
    public static final String USERNAME_TAKEN = "This username taken by another user";
    public static final String TURKISH_REPUBLIC_ID_NO_ALREADY_EXIST = "This Turkish Republic ID number already exist";

    public static final String TURKISH_REPUBLIC_ID_CAN_NOT_BE_VERIFIED = "Turkish Republic ID number can not be verified";

    public static final String OUT_OF_ENUM = "Rate value must be between 1 and 5";
    public static final String USER_NOT_FOUND_BY_ID(Long id) {
        return "User with id '" + id + "' not found";
    }

    public static final String USER_NOT_FOUND_BY_USERNAME (String username) {
        return "User with username '" + username + "' not found";
    }

    public static final String USER_NOT_FOUND_BY_TURKISH_REPUBLIC_ID_NUMBER (String turkishRepublicIdNumber) {
        return "User with Turkish Republic ID number '" + turkishRepublicIdNumber + "' not found";
    }

    public static final String USER_REVIEW_NOT_FOUND_BY_ID(Long id) {
        return "User review with id '" + id + "' not found";
    }

    public static final String FIELD_LENGTH_EXCEEDED(String fieldName, int maxLength) {
        return fieldName + " field length can not exceed " + maxLength + " characters";
    }

    public static final String CAN_NOT_BE_NULL(String fieldName) {
        return fieldName + " can not be null";
    }


}
