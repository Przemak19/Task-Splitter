package pl.crud.tasksplitter.service.interf;

import pl.crud.tasksplitter.dto.CompanyDto;
import pl.crud.tasksplitter.dto.Response;
import pl.crud.tasksplitter.entities.Company;

public interface CompanyService {

    Company saveCompany(Company company);

    Response createAndUpdateCompany(CompanyDto companyDto);
}
