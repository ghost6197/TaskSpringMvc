package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.exceptions.DataNotFoundException;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User post(User user){
        return userRepository.save(user);
    }

    public User put(User user, Long id){
        get(id);
        user.setUserId(id);
        return userRepository.save(user);
    }

    public User get(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Нет записи с id=" + id));
        return user;
    }

    public void delete(Long id){
        get(id);
        userRepository.deleteById(id);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }
}
