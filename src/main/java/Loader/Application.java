package Loader;

import arangodb.ArangoDBFactory;

public class Application {
    public static void main(String[] args){
        ArangoDBFactory arangoDBFactory=new ArangoDBFactory("@four123");
        arangoDBFactory.createDataBase("user");
        arangoDBFactory.createCollection("user","userInfo");




    }
}
