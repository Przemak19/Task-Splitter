package pl.crud.tasksplitter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.crud.tasksplitter.dto.AddressDto;
import pl.crud.tasksplitter.dto.Response;
import pl.crud.tasksplitter.service.interf.AddressService;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/save-update-user")
    public ResponseEntity<Response> saveAndUpdateUserAddress(@RequestBody AddressDto addressDto) {
        return ResponseEntity.ok(addressService.saveAndUpdateUserAddress(addressDto));
    }

    @PostMapping("/save-update-company")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> saveAndUpdateCompanyAddress(@RequestBody AddressDto addressDto) {
        return ResponseEntity.ok(addressService.saveAndUpdateCompanyAddress(addressDto));
    }
}
