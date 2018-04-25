package arangodb;


import com.arangodb.ArangoDB;
import com.arangodb.ArangoDatabase;
import com.arangodb.entity.BaseDocument;
import com.arangodb.entity.CollectionEntity;


public class ArangoDBFactory  extends Exception{
    private ArangoDB arangoDB;

    public ArangoDBFactory(){
         arangoDB = new ArangoDB.Builder().build();

    }

   public ArangoDBFactory(String password) {
         arangoDB = new ArangoDB.Builder().password(password).build();
}

    public void createDataBase(String dbName){
        ArangoDatabase arangoDatabase;
            arangoDB.createDatabase(dbName);
            System.out.println("Database created: " + dbName);
      }

    public CollectionEntity createCollection(String dbName, String collectionName){
       CollectionEntity collectionEntity=null;
            arangoDB.db(dbName).createCollection(collectionName);
            System.out.println("Collection created: " + collectionName);
        return collectionEntity;
      }

    public CollectionEntity createDocument(String dbName, String collectionName, BaseDocument document){
       CollectionEntity collectionEntity=null;

            arangoDB.db(dbName).collection(collectionName).insertDocument(document);
            System.out.println("Collection created: " + collectionName);

        return collectionEntity;
      }

}
