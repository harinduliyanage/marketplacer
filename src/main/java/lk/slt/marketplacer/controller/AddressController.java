package lk.slt.marketplacer.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lk.slt.marketplacer.dto.*;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Tag(name = "address-services")
@RequestMapping("/api/v1/users/{userId}/addresses")
public interface AddressController {
    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public AddressDto createAddress(@PathVariable("userId") String userId,
                                    @RequestBody @Valid CreateAddressDto createAddressDto);

    @GetMapping(value = "/{addressId}", produces = {"application/json"})
    public AddressDto getAddress(@PathVariable("userId") String userId,
                                 @PathVariable("addressId") String addressId);

    @GetMapping()
    public ListResponseDto<AddressDto> getAddresses(@PathVariable("userId") String userId,
                                                    @ParameterObject Pageable pageable);

    @PatchMapping(value = "/{addressId}", consumes = {"application/json"}, produces = {"application/json"})
    public AddressDto updateAddress(@PathVariable("userId") String userId,
                                    @PathVariable("addressId") String addressId,
                                    @Valid @RequestBody UpdateAddressDto updateAddressDto);

    @DeleteMapping(value = "/{addressId}", produces = {"application/json"})
    public AddressDto removeAddress(@PathVariable("userId") String userId,
                                    @PathVariable("addressId") String addressId);
}
