package pl.javastart.exercises.classesToHomework;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private ArrayList<User> usersRepository = new ArrayList<>();

    public UserRepository() {
        usersRepository.add(new User("Lukasz", "Okrasa", 25));
        usersRepository.add(new User("Maria", "Beger", 67));
        usersRepository.add(new User("Michal", "Wietnam", 15));
    }

    public ArrayList<User> getListUsers() {
        return usersRepository;
    }

    public boolean addUser(User user) {
        if (user.getFirstName() == null)
            return false;
        usersRepository.add(user);
        return true;
    }

    public boolean searchUser(User user) {
        if (user == null) {
            return false;
        }
        for (User listUser : usersRepository) {
            if (listUser.getFirstName().equals(user.getFirstName()))
                return true;
        }
        return false;
    }

    public boolean deleteUser(User user) {
        if (!(user.getFirstName() == null))
            usersRepository.remove(user);

        return false;
    }


}
