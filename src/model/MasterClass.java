package model;

import java.util.HashMap;

import data_structures.WeightedGraph.Graph;

public class MasterClass {

    private Graph<User> db;

    private HashMap<String, User> auxDb;

    private User currentUser;

    public MasterClass() {

        db = new Graph<User>();

        auxDb = new HashMap<String, User>();

    }

    public boolean createUser(String userName, String password, String name, String faculty, String email, char gender,
            char preferenceGender) {

        if (auxDb.get(userName) == null) {

            User temp = new User(userName, password, name, faculty, email, gender, preferenceGender);

            db.addNode(temp);

            auxDb.put(userName, temp);

            return true;
        }

        return false;

    }

    public boolean deactivatedAccount(String userName, String password) {

        if (auxDb.get(userName).getPassword().equals(password)) {

            db.searchNode(auxDb.get(userName).hashCode()).getValue().setStatusAccount(false);
            currentUser = null;
            return true;
        }

        return false;

    }

    public boolean signIn(String userName, String password) {
        if (auxDb.get(userName).getPassword().equals(password)) {

            currentUser = db.searchNode(auxDb.get(userName).hashCode()).getValue();
            return true;
        }

        return false;
    }

}
