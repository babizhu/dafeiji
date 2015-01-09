package mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.QueryOperators;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * user         LIUKUN
 * time         2015-1-9 17:34
 */

public class MongoTest{

    @Test
    public void test1(){

        BasicDBObject query11 = new BasicDBObject();
        query11.put( "a", 1 );
        BasicDBObject query12 = new BasicDBObject();
        query12.put( "b", 2 );
        List<BasicDBObject> orQueryList1 = new ArrayList<>();
        orQueryList1.add( query11 );
        orQueryList1.add( query12 );
//        BasicDBObject orQuery1 = new BasicDBObject( "$or", orQueryList1 );
        BasicDBObject orQuery1 = new BasicDBObject( QueryOperators.OR, orQueryList1 );

        BasicDBObject query21 = new BasicDBObject();
        query21.put( "c", 5 );
        BasicDBObject query22 = new BasicDBObject();
        query22.put( "d", 6 );
        List<BasicDBObject> orQueryList2 = new ArrayList<>();
        orQueryList2.add( query21 );
        orQueryList2.add( query22 );
        BasicDBObject orQuery2 = new BasicDBObject( "$or", orQueryList2 );

        List<BasicDBObject> orQueryCombinationList = new ArrayList<>();
        orQueryCombinationList.add( orQuery1 );
        orQueryCombinationList.add( orQuery2 );

        BasicDBObject finalQuery = new BasicDBObject( "$and",
                orQueryCombinationList );
        System.out.println(finalQuery);

    }
}
