package com.dxtrs.hack.gamify.controller;

import com.dxtrs.hack.gamify.model.User;
import com.dxtrs.hack.gamify.repository.UserRepository;
import com.dxtrs.hack.gamify.util.GamifierUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

@RestController
@CrossOrigin(origins = "*")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/api/users/all")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(value="/api/users/add", method= RequestMethod.POST)
    public @ResponseBody User addNewUser(@ModelAttribute() User user) throws ParseException{
        user.setDob(GamifierUtil.convertDateFromString(user.getDateOfBirth()));
        user.setCreatedTS(GamifierUtil.getCurrentDate());
        user.setLastUpdatedTS(GamifierUtil.getCurrentDate());
        System.out.println(user);
        return userRepository.save(user);
    }
}
