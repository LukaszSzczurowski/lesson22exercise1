package pl.javastart.exercises.classesToHomework;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private ArrayList<User> listUsers = new ArrayList<>();

    public UserRepository() {
        listUsers.add(new User("Lukasz", "Okrasa", 25));
        listUsers.add(new User("Maria", "Beger", 67));
        listUsers.add(new User("Michal", "Wietnam", 15));
    }

    public ArrayList<User> getListUsers() {
        return listUsers;
    }

    public void deleteUser(List<User> user) {
         listUsers.remove(user);
    }

    public void addUser(User user) {
        listUsers.add(user);
    }


}
