package com.simplecode.mapstructexample.rest;

import com.simplecode.mapstructexample.dto.AddressReqDto;
import com.simplecode.mapstructexample.dto.AddressResDto;
import com.simplecode.mapstructexample.dto.ApiResponse;
import com.simplecode.mapstructexample.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<AddressResDto>>> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<AddressResDto>> getAddressById(@PathVariable Integer id) {
        return addressService.getAddressById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<String>> saveAddress(@RequestBody AddressReqDto addressReqDto) {
        return addressService.saveAddress(addressReqDto);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ApiResponse<String>> editAddress(@PathVariable Integer id, @RequestBody AddressReqDto addressReqDto) {
        return addressService.editAddress(id, addressReqDto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<String>> deleteAddress(@PathVariable Integer id) {
        return addressService.deleteAddress(id);
    }
}
