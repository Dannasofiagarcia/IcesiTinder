package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import data_structures.WeightedGraph.Edge;
import data_structures.WeightedGraph.Graph;

public class MasterClass {

    public final static String[] FACULTIES = { "Ingenieria", "Ciencias Administrativas y Economicas",
            "Ciencias de la Salud", "Derecho", "Ciencias Naturales", "Ciencias de la Educación" };

    public Graph<User> db;

    public HashMap<String, User> auxDb;

    private User currentUser;

    // True = adjList --- False = adjMatrix;
    private boolean dataStructureType;

    public MasterClass() {

        db = new Graph<User>();

        auxDb = new HashMap<String, User>();

        dataStructureType = true;

        User a = new User("andreNR19", "pinina", "Andrea Nuñez", FACULTIES[0], "andrea.nr2000@gmail.com", 'f', 1);
        User b = new User("dannaSG18", "cloe", "Danna Garcia", FACULTIES[0], "dannita123@yahoo.com", 'f', 1);
        User c = new User("escobarElPatronDelMal", "chumby", "Camilo Escobar Junior", FACULTIES[0],
                "traquetoMan123@yahoo.com", 'm', 1);
        User d = new User("cristancho666", "theBestPassword", "Camilo Cordoba", FACULTIES[0], "kamneklogs@gmail.com",
                'm', 1);
        User e = new User("elTicher", "iLoveTheCode", "Juan Manuel Reyes", FACULTIES[0], "seyerman@gmail.com", 'm', 1);

        db.addNode(a);
        db.addNode(b);
        db.addNode(c);
        db.addNode(d);
        db.addNode(e);

        auxDb.put(a.getUserName(), a);
        auxDb.put(b.getUserName(), b);
        auxDb.put(c.getUserName(), c);
        auxDb.put(d.getUserName(), d);
        auxDb.put(e.getUserName(), e);

        db.addConnection(a.hashCode(), d.hashCode(), 3);

        db.addConnection(a.hashCode(), c.hashCode(), 1);

        db.addConnection(d.hashCode(), c.hashCode(), 2);

        db.addConnection(c.hashCode(), e.hashCode(), 3);

        db.addConnection(a.hashCode(), b.hashCode(), 3);

    }

    public boolean signUp(String userName, String password, String name, String faculty, String email, char gender,
            int preferenceGender) {

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

        if (auxDb.get(userName) == null) {
            return false;
        }
        if (auxDb.get(userName).getPassword().equals(password)) {

            currentUser = db.searchNode(auxDb.get(userName).hashCode()).getValue();
            return true;
        }

        return false;

    }

    public List<User> mySocialCircle() {
        return db.getAdjNodes(currentUser.hashCode());
    }

    public User getRandomUser() {

        List<User> tempUsers = new ArrayList<User>();
        tempUsers.addAll(db.bfs(currentUser.hashCode()));
        int iName = (int) (Math.random() * (double) tempUsers.size());

        return tempUsers.get(iName);

    }

    public boolean doMatch(String userName, int conectionForce) {

        User temp = auxDb.get(userName);
        db.addConnection(currentUser.hashCode(), temp.hashCode(), conectionForce);

        return true;

    }

    public User findUser(String userName) {

        return auxDb.get(userName);

    }

    public List<User> getBestSocialPath(String userName) {

        if (dataStructureType) {

            return db.dijkstraForAdjaList(currentUser.hashCode(), auxDb.get(userName).hashCode());

        }

        return db.dijkstraForAdjaMatrix(currentUser.hashCode(), auxDb.get(userName).hashCode());

    }

    public User getCurrentUser() {
        return currentUser;
    }

    public int getRelationForce(int hashCode) {

        User temporal = null;
        List<User> mySocialCircle = mySocialCircle();

        int result = 0;
        for (int i = 0; i < mySocialCircle.size(); i++) {

            if (mySocialCircle.get(i).hashCode() == hashCode) {
                temporal = mySocialCircle.get(i);
            }

        }

        if (temporal == null) {
            return result;
        }

        List<Edge<User>> myConnections = db.searchNode(currentUser.hashCode()).getEdges();

        for (int i = 0; i < myConnections.size(); i++) {

            if (myConnections.get(i).getDestination().getValue().hashCode() == temporal.hashCode()) {
                return myConnections.get(i).getWeight();
            }

        }

        return 0;

    }

    public boolean isDataStructureType() {
        return dataStructureType;
    }

    public void setDataStructureType(boolean dataStructureType) {
        this.dataStructureType = dataStructureType;
    }

}