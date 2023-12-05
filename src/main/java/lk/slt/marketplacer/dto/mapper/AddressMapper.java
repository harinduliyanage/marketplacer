package lk.slt.marketplacer.dto.mapper;


import lk.slt.marketplacer.dto.*;
import lk.slt.marketplacer.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDto addressToAddressDto(Address address);

    Address addressDtoToAddress(AddressDto addressDto);

    @Mapping(target = "id", ignore = true)
    Address createAddressDtoToAddress(CreateAddressDto createAddressDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "addressLine1", source = "addressLine1",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "addressLine2", source = "addressLine2",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "postalCode", source = "postalCode",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "country", source = "country",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "provinceOrState", source = "provinceOrState",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "contactNumber", source = "contactNumber",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Address updateAddressDtoToAddress(UpdateAddressDto updateAddressDto, @MappingTarget Address address);

    List<AddressDto> addressListToAddressDtoList(List<Address> addresses);
}
