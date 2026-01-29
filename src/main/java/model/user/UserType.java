package model.user;

public enum UserType {
    BUYER,
    PUBLISHER;

    public static UserType toUserType(String userType){
        if(userType.equalsIgnoreCase("Buyer"))
            return BUYER;
        else if(userType.equalsIgnoreCase("Publisher"))
            return PUBLISHER;
        else return null;
    }
}
