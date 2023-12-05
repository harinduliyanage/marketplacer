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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressControllerImpl implements AddressController {

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private AddressService addressService;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public AddressDto createAddress(String userId, CreateAddressDto createAddressDto) {
        Address address = addressService.createUserAddress(userId,
                addressMapper.createAddressDtoToAddress(createAddressDto));
        //
        return addressMapper.addressToAddressDto(address);
    }

    @Override
    public AddressDto getAddress(String userId, String addressId) {
        return addressMapper.addressToAddressDto(addressService.getAddressByUserIdAndId(userId, addressId));
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
        return addressMapper.addressToAddressDto(
                addressService.updateAddress(
                        userId, addressId, addressMapper.updateAddressDtoToAddress(
                                updateAddressDto, addressService.getAddressByUserIdAndId(userId, addressId)
                        )
                )
        );
    }

    @Override
    public AddressDto removeAddress(String userId, String addressId) {
        return addressMapper.addressToAddressDto(
                addressService.removeAddress(userId, addressId)
        );
    }
}
