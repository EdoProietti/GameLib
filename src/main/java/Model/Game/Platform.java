package Model.Game;

public enum Platform {
    PC,
    PS5,
    XBOX,
    SWITCH;

    public static Platform toPlatform(String platform){
        for(Platform p : Platform.values()){
            if(p.name().equalsIgnoreCase(platform)){
                return p;
            }
        }
        return null;
    }
}
