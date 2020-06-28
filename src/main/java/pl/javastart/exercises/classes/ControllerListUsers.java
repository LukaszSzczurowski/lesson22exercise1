package pl.javastart.exercises.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

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

//    @RequestMapping(value = "/search", method = RequestMethod.POST)
//    @ResponseBody
//    String searchUser(@RequestParam(required = false) String imie, @RequestParam(required = false) String nazwisko, @RequestParam(required = false) int wiek) {
//        User listUsersToSearch = this.listUsers.getListUsers().stream()
//                .filter(user -> user.getFirstName().equals(imie))
//                .forEach(user -> System.out::println (user));
//
//    }


    @RequestMapping("/failed")
    String failedCreateUser() {
        return "err.html";
    }

    @RequestMapping("/success")
    String successCreateUser() {
        return "success.html";
    }

}
