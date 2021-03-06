package com.mongodb.test;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

@SuppressWarnings("deprecation")
public class MongodbTest {
	
	private static final Logger logger = LoggerFactory.getLogger( MongodbTest.class );

	private Mongo mongo;
	
	private DB db;
	
	private DBCollection collection;
	
	@Before
	public void init() {
		
		if ( mongo == null ) {
			
			try {
				mongo = new Mongo();
				db = mongo.getDB( "fbb" );
				collection = db.getCollection( "log" );
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@After
	public void destory() {
		
		if ( mongo != null ) {
			
			mongo.close();
		}
		
		System.gc();
	}
	
	@Test
	public void testGetDatabaseNames() {
		
		List<String> list = mongo.getDatabaseNames();
		
		if ( list != null && list.size() > 0 ) {
			
			logger.info( "list.size() = " + list.size() );
			
			for ( String str : list ) {
				
				logger.info( str );
			}
		}
	}
	
	@Test
	public void testGetVersion() {
		
		logger.info( "version: " + mongo.getVersion() );
	}
	
	@Test
	public void testGetConnectPoint() {
		
		logger.info( "point: " + mongo.getConnectPoint() );
	}
	
	@Test
	public void test() {
		
		logger.info( "isLocked: " + mongo.isLocked() );
		logger.info( "getOptions: " + mongo.getOptions() );
		logger.info( "debugString: " + mongo.debugString() );
		logger.info( "getMaxBsonObjectSize: " + mongo.getMaxBsonObjectSize() );
		logger.info( "getAddress: " + mongo.getAddress() );
		logger.info( "getAllAddress: " + mongo.getAllAddress() );
		logger.info( "getConnector: " + mongo.getConnector() );
		
	}
	
	@Test
	public void testDB() {
		
		logger.info( "getName: " + db.getName() );
		logger.info( "getOptions: " + db.getOptions() );
		logger.info( "getMongo: " + db.getMongo() );
		logger.info( "getCollectionNames: " + db.getCollectionNames() );
		
	}
	
	@Test
	public void testCollection() {
		
		logger.info( "count: " + collection.count() );
		logger.info( "getCount: " + collection.getCount() );
		logger.info( "getFullName: " + collection.getFullName() );
		logger.info( "getDB: " + collection.getDB() );
	}
	
	@Test
	public void testQuerry() {
		
		DBCursor dc = collection.find();
		
		if ( dc != null && dc.size() > 0 ) {
			
			logger.info( "size: " + dc.size() );
			
			while ( dc.hasNext() ) {
				
				logger.info( "" + dc.next() );
			}
		}
	}
	
	@Test
	public void add() {
		
		DBObject obj = new BasicDBObject();
		
		obj.put( "heihaier", "heihaier" );
		logger.info( "getN(): " + collection.save( obj ).getN() );
	}
	
	@Test
	public void addMore() {
		
		List<DBObject> list = Lists.newArrayList();
		
		DBObject obj1 = new BasicDBObject();
		
		obj1.put( "heihaier1", "heihaier1" );
		
		DBObject obj2 = new BasicDBObject();
		
		obj2.put( "heihaier1", "heihaier1" );
		
		list.add( obj1 );
		list.add( obj2 );
		
		logger.info( "getN: " + collection.insert( list ).getN() );
	}
	
	@Test
	public void remove() {
		
		collection.remove( new BasicDBObject( "_id", new ObjectId( "54c6fae6fb27076f342e590f" ) ) );
		collection.remove( new BasicDBObject( "heihaier1", "heihaier1" ) );
		logger.info( "remove age >= 24: " + collection.remove(new BasicDBObject("age", new BasicDBObject("$gte", 24))).getN());
	}
	
	@Test
	public void update() {
		
		int n1 = collection.update( 
				new BasicDBObject( "_id", new ObjectId( "54c61992fb27280e2ad6f120") ), 
				new BasicDBObject( "heihaier", "heixiaohai") ).getN();
		logger.info( "n1 = " + n1 );
		
		int n2 = collection.update(
				new BasicDBObject("_id", new ObjectId("4dde2b06feb038463ff09042")), 
	            new BasicDBObject("age", 121),
	            true,//如果数据库不存在，是否添加
	            false//多条修改
	            ).getN();
		
		logger.info( "n2 = " + n2 );
	}
	
	@Test
	public void query() {
		
		logger.info( "collection.find().toArray(): " + collection.find().toArray() );
		logger.info( "collection.find().toArray(): " + collection.find( new BasicDBObject( "_id", new ObjectId( "54c6fae6fb27076f342e590f" ) ) ) );
		
	}
}
