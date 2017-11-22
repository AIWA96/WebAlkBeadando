package hu.iit.uni.miskolc.wabalk.dao;

public class ConnectionString {
    private static String OS = null;

    public static String getConnectionString(){
        OS = System.getProperty("os.name");
        if (OS.startsWith("Windows")){
            return "jdbc:sqlite:C:/Users/Dani/IdeaProjects/WebAlkBeadando/dao/src/resources/glassShop.db";
        }
        return "jdbc:sqlite:/home/dani/IdeaProjects/WebAlkBeadando/dao/src/resources/glassShop.db";
    }
}
