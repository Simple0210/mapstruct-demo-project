package com.simplecode.mapstructexample.service;

import com.simplecode.mapstructexample.dto.AddressReqDto;
import com.simplecode.mapstructexample.dto.AddressResDto;
import com.simplecode.mapstructexample.dto.ApiResponse;
import com.simplecode.mapstructexample.entity.Address;
import com.simplecode.mapstructexample.mapper.AddressMapper;
import com.simplecode.mapstructexample.repository.AddressRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public ResponseEntity<ApiResponse<List<AddressResDto>>> getAllAddresses() {
        List<AddressResDto> addresses = addressMapper.addressesToDtos(addressRepository.findAll());
        if (addresses.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse<>(false, null, "Addresses not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ApiResponse<>(true, addresses, ""), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<AddressResDto>> getAddressById(Integer id) {
        Address address = addressRepository.findById(id).orElse(null);
        if (address != null) {
            AddressResDto addressResDto = addressMapper.addressToDto(address);
            return new ResponseEntity<>(new ApiResponse<>(true, addressResDto, ""), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse<>(false, null, "Address not found!"), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<ApiResponse<String>> saveAddress(AddressReqDto addressReqDto) {
        if (addressReqDto != null) {
            Address address = addressMapper.dtoToAddress(addressReqDto);
            addressRepository.save(address);
            return new ResponseEntity<>(new ApiResponse<>(true, null, "Address saved!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse<>(false, null, "Address not saved"), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<ApiResponse<String>> editAddress(Integer id, AddressReqDto addressReqDto) {
        if (id != null) {
            Address address = addressRepository.findById(id).orElse(null);
            if (address != null) {
                Address address1 = addressMapper.dtoToAddress(addressReqDto);
                address1.setId(id);
                addressRepository.save(address1);
                return new ResponseEntity<>(new ApiResponse<>(true, null, "Address successfully changed!"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse<>(false, null, "Address not found!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ApiResponse<>(false, null, "Address not found!"), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<ApiResponse<String>> deleteAddress(Integer id) {
        try {
            addressRepository.deleteById(id);
            return new ResponseEntity<>(new ApiResponse<>(true, null, "Address successfully deleted!"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(false, null, "Address not deleted!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
