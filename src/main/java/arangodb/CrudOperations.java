package arangodb;

import com.arangodb.entity.BaseDocument;
import com.arangodb.entity.CollectionEntity;
import com.arangodb.velocypack.VPackSlice;

public class CrudOperations extends ArangoDBFactory{
public CrudOperations(){
super();
}
    public CrudOperations(String pwd){
    super(pwd);
    }

    public CollectionEntity createDocument(String dbName, String collectionName, BaseDocument document){
        CollectionEntity collectionEntity=null;

        arangoDB.db(dbName).collection(collectionName).insertDocument(document);

        return collectionEntity;
    }

    public BaseDocument getDocument(String dbName, String collectionName, String documentName){
        BaseDocument baseDocument=null;

        baseDocument=arangoDB.db(dbName).collection(collectionName).getDocument(documentName,BaseDocument.class);

        return baseDocument;
    }

    public VPackSlice getDocumentVelocyPack(String dbName, String collectionName, String documentName){
        VPackSlice vPackSlice=null;

        vPackSlice=arangoDB.db(dbName).collection(collectionName).getDocument(documentName,VPackSlice.class);

        return vPackSlice;
    }

    public BaseDocument updateDocument(String dbName, String collectionName, String documentName,BaseDocument baseDocument){

        return arangoDB.db(dbName).collection(collectionName).updateDocument(documentName,baseDocument).getNew();

    }

    public void deleteDocument(String dbName, String collectionName, String documentName){

         arangoDB.db(dbName).collection(collectionName).deleteDocument(documentName);

    }



}
