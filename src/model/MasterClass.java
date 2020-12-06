package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    public void saveData() throws FileNotFoundException, IOException {

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/backups/backupAux.dat"));

        oos.writeObject(auxDb);
        oos.close();

        oos = new ObjectOutputStream(new FileOutputStream("data/backups/backupMain.dat"));
        oos.writeObject(db);

        oos.close();
    }

    @SuppressWarnings("unchecked")
    public void loadData() throws ClassNotFoundException, IOException {

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/backups/backupAux.dat"));

        auxDb = (HashMap<String, User>) ois.readObject();

        ois.close();

        ois = new ObjectInputStream(new FileInputStream("data/backups/backupMain.dat"));

        db = (Graph<User>) ois.readObject();

        ois.close();
    }

}