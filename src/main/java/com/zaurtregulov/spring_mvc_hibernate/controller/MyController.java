package com.zaurtregulov.spring_mvc_hibernate.controller;

import com.zaurtregulov.spring_mvc_hibernate.entity.User;
import com.zaurtregulov.spring_mvc_hibernate.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MyController {

    /*Внедряем зависимость DI
    MyController должен зависеть теперь не от DAO напрямую, а от UserService
    В Controller теперь мы будем обращаться к сервису
     */

    @Autowired
    private UserService userService;

    // Когда в адресе будем указывать '/', то будет возвращаться список user-ов
    @RequestMapping("/")
    public String showAllUsers(Model model) {
    /* Вызываем метод getAllUsers, чтобы вернул всех User-ов, можем вызвать этот метод
    потому, что класс UserDAOImpl реализует интерфейс UserDAO, помещаем результат в List<User>*/

        List<User> allUsers = userService.getAllUsers();

    /*
    Чтобы view смог отобразить значение полей наших работников, мы должны в методе Controller-а создать Model и добавить наших
    работников в качестве атрибута к этой Model-и. Пропишем Model в параметрах метода showAllUsers.
    Помещаем в Model название атрибута и значение атрибута -allUsers
    */
        model.addAttribute("allUsers", allUsers);

        return "all-users";                  // Возвращает названия view, который будет показывать всех user-ов
    }

    @RequestMapping("/addNewUser")
    public String addNewUser(Model model){

        User user = new User();
        model.addAttribute("user", user);
        return "user-info";                 // Этот view будет открываться после нажатия кнопки Add
    }

    @RequestMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user){

        userService.saveUser(user);
        // Перенаправление, где / отвечает за view, который выводит всех работников на экран
        return "redirect:/";               // После сохранения user-а, должен возвращаться view с информацией о всех user-рах
    }



    @RequestMapping("/updateInfo")
    public String updateUser(@RequestParam("userId") int id, Model model){

        User user = userService.getUser(id);
        model.addAttribute("user", user);

        return "user-info";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") int id){

        userService.deleteUser(id) ;

        return "redirect:/";

    }


}
