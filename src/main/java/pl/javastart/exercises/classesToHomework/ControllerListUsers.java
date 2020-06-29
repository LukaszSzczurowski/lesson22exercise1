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
        User user = new User(imie, nazwisko, wiek);
        boolean isNameNull = userRepository.addUser(user);
        if (isNameNull)
            return "redirect:/success.html";
        else
            return "redirect:/err.html";
    }

    @PostMapping("/search")
    @ResponseBody
    String searchUser(@RequestParam(required = false) String imie, @RequestParam(required = false) String nazwisko, @RequestParam(required = false) int wiek) {
        boolean searchUser = userRepository.searchUser(new User(imie, nazwisko, wiek));
        if (searchUser)
            return "Znaleziono użytkownika " + imie;
        else
            return "Nie znaleziono użytkownika " + imie;
    }

    @PostMapping("/delete")
    @ResponseBody
    String deleteUser(@RequestParam(required = false) String imie, @RequestParam(required = false) String nazwisko, @RequestParam(required = false) int wiek) {
        boolean deleteUser = userRepository.deleteUser(new User(imie, nazwisko, wiek));
        if (deleteUser)
            return "Usunięto użytkownika " + imie;
        else
            return "Nie usunięto użytkownika " + imie;
    }
}
