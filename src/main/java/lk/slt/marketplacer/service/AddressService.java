package lk.slt.marketplacer.service;

import lk.slt.marketplacer.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AddressService {

    public Address createUserAddress(String userId, Address address);

    public Address getAddressByUserIdAndId(String userId, String id);

    public Optional<Address> getAddressById(String id);

    public Page<Address> getAddressByUserId(String userId, Pageable pageable);

    public Address updateAddress(String userId, String id, Address address);

    public Address removeAddress(String userId, String id);
}
