package pl.crud.tasksplitter.service.interf;

import pl.crud.tasksplitter.dto.CompanyDto;
import pl.crud.tasksplitter.dto.Response;
import pl.crud.tasksplitter.entities.Company;
import pl.crud.tasksplitter.entities.CompanyMembership;
import pl.crud.tasksplitter.entities.User;

public interface CompanyService {

    Company saveCompany(Company company);

    Response createAndUpdateCompany(CompanyDto companyDto);

    Company saveCompanyMembership(CompanyMembership companyMembership, Company company);
}
