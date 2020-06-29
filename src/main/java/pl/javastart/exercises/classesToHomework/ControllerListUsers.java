package pl.javastart.exercises.classesToHomework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class ControllerListUsers {

    private UserRepository userRepository;

    @Autowired
    public ControllerListUsers(UserRepository listUsers) {
        this.userRepository = listUsers;
    }

    @RequestMapping("/users")
    @ResponseBody
    String printUsers() {
        ArrayList<User> usersToPrint = this.userRepository.getListUsers();
        return "Użytkownicy : <br />" + usersToPrint.toString();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    String addUser(@RequestParam(required = false) String imie, @RequestParam String nazwisko, @RequestParam int wiek) {
        if (imie == null){
            return "redirect:/err.html";
        }

        User user = new User(imie, nazwisko, wiek);
        userRepository.addUser(user);
        return "redirect:/success.html";
    }

    @PostMapping("/search")
    @ResponseBody
    List<User> searchUser(@RequestParam(required = false) String imie, @RequestParam(required = false) String nazwisko, @RequestParam(required = false) int wiek) {
        List<User> userList = userRepository.getListUsers();
        Stream<User> findUser = userList.stream();
        if (!imie.isEmpty()) {
            findUser = findUser.filter(user -> imie.equals(user.getFirstName()));
        } else if (!nazwisko.isEmpty()) {
            findUser = findUser.filter(user -> nazwisko.equals(user.getLastName()));
        } else {
            findUser = findUser.filter(user -> wiek == user.getAge());
        }

        return findUser.collect(Collectors.toList());
    }

    @PostMapping("/delete")
    @ResponseBody
    String deleteUser(@RequestParam(required = false) String imie, @RequestParam(required = false) String nazwisko, @RequestParam(required = false) int wiek) {
        List<User> userTodelete = searchUser(imie, nazwisko, wiek);
        userRepository.deleteUser(userTodelete);

        return "Usunięto użytkownika ";
    }
}
