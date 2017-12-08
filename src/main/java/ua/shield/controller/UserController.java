package ua.shield.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.shield.entity.Chield;
import ua.shield.entity.User;
import ua.shield.service.SecurityService;
import ua.shield.service.UserService;
import ua.shield.validator.UserValidator;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * Created by sa on 06.12.17.
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;

    @Autowired
    UserValidator userValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/registration",method= RequestMethod.GET)
    public String registration(Model model){
        model.addAttribute("userForm",new User());
        return "/user/registration";
    }

    @RequestMapping(value = "/registration",method= RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult,Model model){
        userValidator.validate(userForm,bindingResult);
        if(bindingResult.hasErrors()){
            return "/user/registration";
        }
       userService.add(userForm);
       securityService.autoLogin(userForm.getUsername(),userForm.getConfirmPassword());
       return "redirect:/welcome";
    }

    @RequestMapping(value = "/login",method=RequestMethod.GET)
    public String login(Model model, String error, String logout){
        if(error!=null){
            model.addAttribute("error","Користувач або пароль не вірний");
        }
        return "/user/login";
    }

    @RequestMapping(value = "/cabinet",method=RequestMethod.GET)
    ModelAndView cabinet(Principal principal,ModelAndView modelAndView){
        User user = userService.findByUsername(principal.getName());
        modelAndView.addObject("user",user);
        modelAndView.setViewName("/user/cabinet");
        return modelAndView;
    }

    @RequestMapping(value = "/cabinet/addChield",method= RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Set<Chield> addChield(@ModelAttribute Chield chield, BindingResult bindingResult,Principal principal){
        User user = userService.findByUsername(principal.getName());
        user.addChield(chield);
        return userService.update(user).getChields();
    }

    @RequestMapping(value = "/cabinet/getChield",method= RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Set<Chield> getChield(Principal principal){
        User user = userService.findByUsername(principal.getName());
       return user.getChields();
    }

}
