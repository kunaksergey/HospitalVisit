package ua.shield.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.shield.service.UserService;

/**
 * Created by sa on 16.12.17.
 */
@Controller
@RequestMapping("/image")
public class ImageController {
    @Autowired
    UserService userService;

    @RequestMapping("/{id}")
    String load(@PathVariable Integer id){
        byte[] image = userService.findOne(id).getImage();
        return null;
    }
}
