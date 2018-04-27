package arangodb;

import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDBException;
import com.arangodb.entity.BaseDocument;
import com.arangodb.util.MapBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AQLQueries extends CrudOperations {
    public AQLQueries(String pwd) {
        super(pwd);
    }

    public AQLQueries(){
        super();

    }

    public List<BaseDocument> getDocumentByFilter(String dbName, String collectionName, String key, String value) {
        ArangoCursor<BaseDocument> cursor=null;

        try {
            String query = "FOR t IN "+collectionName+" FILTER t."+key+"== @key RETURN t";
            Map<String, Object> bindVars = new MapBuilder().put("key", value).get();
            cursor = arangoDB.db(dbName).query(query, bindVars, null,
                    BaseDocument.class);
        } catch (ArangoDBException e) {
            System.err.println("Failed to execute query. " + e.getMessage());
        }
        return cursor.asListRemaining();
    }

    public void insertData(String dbName,String collectionName,BaseDocument baseDocument) {
        try {
            String query = "INSERT @baseDocument INTO "+collectionName;
            Map<String, Object> bindVars = new MapBuilder().put("baseDocument", baseDocument).get();
            ArangoCursor<BaseDocument> cursor = arangoDB.db(dbName).query(query, bindVars, null,
                    BaseDocument.class);
        } catch (ArangoDBException e) {
            System.err.println("Failed to execute query. " + e.getMessage());
        }
    }

    public void deleteData(String dbName,String collectionName,String key,String value){
        try {
            String query = "FOR t IN "+collectionName+" FILTER t."+key+" == @key "
                    + "REMOVE t IN "+collectionName+"  LET removed = OLD RETURN removed";
            Map<String, Object> bindVars = new MapBuilder().put("key", value).get();
            ArangoCursor<BaseDocument> cursor = arangoDB.db(dbName).query(query, bindVars, null,
                    BaseDocument.class);
        } catch (ArangoDBException e) {
            System.err.println("Failed to execute query. " + e.getMessage());
        }

    }

    public List<BaseDocument> getData(String dbName, String collectionName, String key, String value, String colmn){
        ArangoCursor<BaseDocument> cursor=null;
        try {
            String query = "FOR t IN "+collectionName+" FILTER t."+key+" == @key "
                    + " RETURN t."+colmn;
            Map<String, Object> bindVars = new MapBuilder().put("key", value).get();
            cursor = arangoDB.db(dbName).query(query, bindVars, null,
                    BaseDocument.class);
        } catch (ArangoDBException e) {
            System.err.println("Failed to execute query. " + e.getMessage());
        }
        return cursor.asListRemaining();

    }
}
