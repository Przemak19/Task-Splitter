package pl.crud.tasksplitter.service.interf;

import org.springframework.stereotype.Service;
import pl.crud.tasksplitter.dto.LoginRequest;
import pl.crud.tasksplitter.dto.Response;
import pl.crud.tasksplitter.dto.UserDto;
import pl.crud.tasksplitter.entities.User;

@Service
public interface UserService {

    Response registerUser(UserDto registrationRequest);

    Response loginUser(LoginRequest loginRequest);

    Response getAllUsers();

    User getLoginUser();

    Response getUserInfoAndTasks();
}
