package pl.crud.tasksplitter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.crud.tasksplitter.dto.AddressDto;
import pl.crud.tasksplitter.dto.Response;
import pl.crud.tasksplitter.entities.Address;
import pl.crud.tasksplitter.entities.Company;
import pl.crud.tasksplitter.entities.User;
import pl.crud.tasksplitter.repository.AddressRepository;
import pl.crud.tasksplitter.service.interf.AddressService;
import pl.crud.tasksplitter.service.interf.CompanyService;
import pl.crud.tasksplitter.service.interf.UserService;

@Service
@RequiredArgsConstructor
public class AddressServiceImplementation implements AddressService {

    private final AddressRepository addressRepository;
    private final UserService userService;
    private final CompanyService companyService;

    @Override
    public Response saveAndUpdateUserAddress(AddressDto addressDto) {
        User user = userService.getLoginUser();
        Address address = user.getAddress();

        address = setAddressRows(address, addressDto);

        String message = (user.getAddress() == null) ? "User address created" : "User address updated";

        addressRepository.save(address);

        user.setAddress(address);
        userService.saveUser(user);

        return Response.builder()
                .status(200)
                .message(message)
                .build();
    }

    @Override
    public Response saveAndUpdateCompanyAddress(AddressDto addressDto) {
        User user = userService.getLoginUser();
        Company company = user.getOwnedCompany();
        Address address = company.getAddress();

        address = setAddressRows(address, addressDto);

        String message = (company.getAddress() == null) ? "Company address created" : "Company address updated";

        addressRepository.save(address);

        company.setAddress(address);
        companyService.saveCompany(company);

        return Response.builder()
                .status(200)
                .message(message)
                .build();
    }

    private Address setAddressRows(Address address, AddressDto addressDto) {

        if(address == null) {
            address = new Address();
        }

        if(addressDto.getStreet() != null) address.setStreet(addressDto.getStreet());
        if(addressDto.getNumber() != null) address.setNumber(addressDto.getNumber());
        if(addressDto.getCity() != null) address.setCity(addressDto.getCity());
        if(addressDto.getPostalCode() != null) address.setPostalCode(addressDto.getPostalCode());
        if(addressDto.getCountry() != null) address.setCountry(addressDto.getCountry());

        return address;
    }
}
