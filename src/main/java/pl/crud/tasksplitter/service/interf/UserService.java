package pl.crud.tasksplitter.service.interf;

import pl.crud.tasksplitter.dto.LoginRequest;
import pl.crud.tasksplitter.dto.Response;
import pl.crud.tasksplitter.dto.UserDto;
import pl.crud.tasksplitter.entities.Company;
import pl.crud.tasksplitter.entities.CompanyMembership;
import pl.crud.tasksplitter.entities.User;

public interface UserService {

    Response registerUser(UserDto registrationRequest);

    Response loginUser(LoginRequest loginRequest);

    Response getAllUsers();

    User getLoginUser();

    Response getUserInfoAndTasks();

    User saveUser(User user);

    User saveUserCompanyMembership(CompanyMembership companyMembership, User user);

    Boolean isAdmin(User user);

    Boolean isUser(User user);

    Company getAdminCompany(User user);
}
