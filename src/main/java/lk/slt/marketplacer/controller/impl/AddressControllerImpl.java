package lk.slt.marketplacer.controller.impl;

import lk.slt.marketplacer.controller.AddressController;
import lk.slt.marketplacer.dto.AddressDto;
import lk.slt.marketplacer.dto.CreateAddressDto;
import lk.slt.marketplacer.dto.ListResponseDto;
import lk.slt.marketplacer.dto.UpdateAddressDto;
import lk.slt.marketplacer.dto.mapper.AddressMapper;
import lk.slt.marketplacer.model.Address;
import lk.slt.marketplacer.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressControllerImpl implements AddressController {

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private AddressService addressService;


    @Override
    public AddressDto createAddress(String userId, CreateAddressDto createAddressDto) {
        Address createdAddress = addressService.createUserAddress(userId,
                addressMapper.createAddressDtoToAddress(createAddressDto));
        //
        return addressMapper.addressToAddressDto(createdAddress);
    }

    @Override
    public AddressDto getAddress(String userId, String addressId) {
        Address address = addressService.getAddressByUserIdAndId(userId, addressId);
        return addressMapper.addressToAddressDto(address);
    }

    @Override
    public ListResponseDto<AddressDto> getAddresses(String userId, Pageable pageable) {
        Page<Address> page = addressService.getAddressByUserId(userId, pageable);
        return ListResponseDto.<AddressDto>builder()
                .data(addressMapper.addressListToAddressDtoList(page.getContent()))
                .page(pageable.getPageNumber())
                .limit(pageable.getPageSize())
                .totalResults(page.getNumberOfElements())
                .totalPages(page.getTotalPages())
                .build();
    }


    @Override
    public AddressDto updateAddress(String userId, String addressId, UpdateAddressDto updateAddressDto) {
        Address address = addressService.getAddressByUserIdAndId(userId, addressId);
        Address updatedAddress = addressService.updateAddress(userId, addressId,
                addressMapper.updateAddressDtoToAddress(updateAddressDto, address));
        //
        return addressMapper.addressToAddressDto(updatedAddress);
    }

    @Override
    public AddressDto removeAddress(String userId, String addressId) {
        Address address = addressService.removeAddress(userId, addressId);
        return addressMapper.addressToAddressDto(address);
    }
}
