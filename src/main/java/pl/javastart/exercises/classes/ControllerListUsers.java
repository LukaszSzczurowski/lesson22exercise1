package pl.javastart.exercises.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class ControllerListUsers {

    private ListUsers listUsers;

    @Autowired
    public ControllerListUsers(ListUsers listUsers) {
        this.listUsers = listUsers;
    }

    @RequestMapping("/users")
    @ResponseBody
    void printUsers() {
        listUsers.showUsers();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    String addUser(@RequestParam(required = false) String imie, @RequestParam String nazwisko, @RequestParam int wiek) {
        if (imie.isEmpty()) {
            return "redirect:/failed";
        }
        User user = new User(imie, nazwisko, wiek);
        ArrayList<User> listUsers1 = listUsers.getListUsers();
        listUsers1.add(user);
        return "redirect:/success";
    }


    @RequestMapping("/failed")
    String failedCreateUser() {
        return "err.html";
    }

    @RequestMapping("/success")
    String successCreateUser() {
        return "success.html";
    }

    @PostMapping("/search")
    @ResponseBody
    List<User> searchUser(@RequestParam(required = false) String imie, @RequestParam(required = false) String nazwisko, @RequestParam(required = false) int wiek) {
        List<User> userList = listUsers.getListUsers();
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
        listUsers.deleteUser(userTodelete);

        return "Usunięto użytkownika ";
    }
}
