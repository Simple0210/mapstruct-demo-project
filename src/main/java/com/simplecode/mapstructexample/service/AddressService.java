package com.simplecode.mapstructexample.service;

import com.simplecode.mapstructexample.dto.AddressReqDto;
import com.simplecode.mapstructexample.dto.AddressResDto;
import com.simplecode.mapstructexample.dto.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AddressService {

    ResponseEntity<ApiResponse<List<AddressResDto>>> getAllAddresses();

    ResponseEntity<ApiResponse<AddressResDto>> getAddressById(Integer id);

    ResponseEntity<ApiResponse<String>> saveAddress(AddressReqDto addressReqDto);

    ResponseEntity<ApiResponse<String>> editAddress(Integer id, AddressReqDto addressReqDto);

    ResponseEntity<ApiResponse<String>> deleteAddress(Integer id);
}
