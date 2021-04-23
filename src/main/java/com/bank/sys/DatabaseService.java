package com.bank.sys;

import com.bank.sys.models.User;
import com.bank.sys.utils.NotEnoughMoneyException;
import com.bank.sys.utils.UserNotFoundException;
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

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

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

    public void transferMoney(String fromId, String toId, double amount) throws UserNotFoundException, NotEnoughMoneyException {
        initUsersCollection();

        User fromUser = usersCollection.find(eq("_id", new ObjectId(fromId))).first();
        User toUser = usersCollection.find(eq("_id", new ObjectId(toId))).first();

        if(fromUser == null || toUser == null) {
            throw new UserNotFoundException("Jeden z użytkowników nie istnieje");
        }

        if(fromUser.getMoney() < amount) {
            throw new NotEnoughMoneyException("Użytkownik ma za mało środków na koncie aby wykonać przelew");
        }

        usersCollection.updateOne(eq("_id", fromUser.getId()), set("money", fromUser.getMoney() - amount));
        usersCollection.updateOne(eq("_id", toUser.getId()), set("money", toUser.getMoney() + amount));
    }

    public void depositMoney(String userId, double amount) {
        initUsersCollection();

        User user = usersCollection.find(eq("_id", new ObjectId(userId))).first();

        usersCollection.updateOne(eq("_id", user.getId()), set("money", user.getMoney() + amount));
    }

    public void withdrawMoney(String userId, double amount) throws NotEnoughMoneyException {
        initUsersCollection();

        User user = usersCollection.find(eq("_id", new ObjectId(userId))).first();

        if(user.getMoney() < amount) {
            throw new NotEnoughMoneyException("Brak wystarczającej ilości środków");
        }

        usersCollection.updateOne(eq("_id", user.getId()), set("money", user.getMoney() - amount));
    }
}
