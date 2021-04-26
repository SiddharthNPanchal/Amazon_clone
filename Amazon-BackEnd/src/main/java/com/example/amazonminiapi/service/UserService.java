package com.example.amazonminiapi.service;

import com.example.amazonminiapi.model.User;
import com.example.amazonminiapi.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public void insertuser(User user){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        repo.insert(user);
    }

    public List<User> login(User user){
        List<User> u = (List<User>) repo.findByemail(user.getEmail());
        System.out.println("Email"+u.size());
        if(u.size()==0){
            return null;

        }
        else
        {
            System.out.println("Email"+u.get(0).getEmail());
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            if(passwordEncoder.matches(user.getPassword(), u.get(0).getPassword()) && u.get(0).getEmail().toString().equals(user.getEmail())){
                return (List<User>) u;
            }
            else{
                return null;
            }

        }



    }
}
