package pl.crud.tasksplitter.service.impl;

import pl.crud.tasksplitter.dto.LoginRequest;
import pl.crud.tasksplitter.dto.Response;
import pl.crud.tasksplitter.dto.UserDto;
import pl.crud.tasksplitter.entities.User;
import pl.crud.tasksplitter.service.interf.UserService;

public class UserServiceImplementation implements UserService {

    @Override
    public Response registerUser(UserDto registrationRequest) {
        return null;
    }

    @Override
    public Response loginUser(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public Response getAllUsers() {
        return null;
    }

    @Override
    public User getLoginUser() {
        return null;
    }

    @Override
    public Response getUserInfoAndTasks() {
        return null;
    }
}
