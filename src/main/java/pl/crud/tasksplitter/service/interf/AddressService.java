package pl.crud.tasksplitter.service.interf;

import pl.crud.tasksplitter.dto.AddressDto;
import pl.crud.tasksplitter.dto.Response;

public interface AddressService {
    Response saveAndUpdateUserAddress(AddressDto addressDto);
    Response saveAndUpdateCompanyAddress(AddressDto addressDto);
}
