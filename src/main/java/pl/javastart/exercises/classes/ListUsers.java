package pl.javastart.exercises.classes;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class ListUsers {

    private ArrayList<User> listUsers = new ArrayList<>();

    public ListUsers() {
        listUsers.add(new User("Lukasz", "Okrasa", 25));
        listUsers.add(new User("Maria", "Beger", 67));
        listUsers.add(new User("Michal", "Wietnam", 15));
    }

    public ArrayList<User> getListUsers() {
        return listUsers;
    }

    public void setListUsers(ArrayList<User> listUsers) {
        this.listUsers = listUsers;
    }

    public void showUsers(){
        for (User user : listUsers) {
            System.out.println(user);
        }
    }
}
