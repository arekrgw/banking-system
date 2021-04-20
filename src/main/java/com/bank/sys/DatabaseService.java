package com.bank.sys;

import com.bank.sys.models.User;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import static com.mongodb.client.model.Filters.eq;

public class DatabaseService {
    
    private MongoClient mClient = null;
    private MongoDatabase mDb = null;
    private MongoCollection<User> usersCollection = null;


    DatabaseService() {
        ConnectionString conn = new ConnectionString("mongodb+srv://root:" + System.getenv("DB_PASSWORD") + "@banking-sys.wi9ci.mongodb.net");
        CodecRegistry pojoCodec = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codec = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                                pojoCodec);
        MongoClientSettings settings = MongoClientSettings.builder()
                                                        .applyConnectionString(conn)
                                                        .codecRegistry(codec)
                                                        .build();

        mClient = MongoClients.create(settings);

        mDb = mClient.getDatabase("bank");
    }

    private void initUsersCollection() {
        if(usersCollection == null) {
            usersCollection = mDb.getCollection("users", User.class);
        }
    }

    public void insertUser(User user) {
        initUsersCollection();

        usersCollection.insertOne(user);
    }

    public User findUser(String userId) {
        initUsersCollection();

        return usersCollection.find(eq("_id", new ObjectId(userId))).first();
    }

    public void deleteUser(String userId) {
        initUsersCollection();

        usersCollection.deleteOne(eq("_id", new ObjectId(userId)));
    }

    public FindIterable<User> findUsersCriteria(String filterType, String filterValue) {
        initUsersCollection();
        return usersCollection.find(eq(filterType, filterValue));
    }
}
