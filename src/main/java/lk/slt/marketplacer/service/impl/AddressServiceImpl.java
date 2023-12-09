package lk.slt.marketplacer.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import lk.slt.marketplacer.exceptions.AddressFoundException;
import lk.slt.marketplacer.model.Address;
import lk.slt.marketplacer.model.QAddress;
import lk.slt.marketplacer.model.User;
import lk.slt.marketplacer.repository.AddressRepository;
import lk.slt.marketplacer.service.AddressService;
import lk.slt.marketplacer.service.UserService;
import lk.slt.marketplacer.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {
    @Autowired
    private UserService userService;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address createAddress(Address address) {
        Address saveAddress = addressRepository.save(address);
        log.info("address has been successfully saved {}", saveAddress);

        return saveAddress;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Address createUserAddress(String userId, Address address) {
        User found = userService.getUser(userId);
        Address saveAddress = addressRepository.save(address);

        if (null == found.getAddresses()) {
            ArrayList<Address> addresses = new ArrayList<>();
            addresses.add(saveAddress);
            found.setAddresses(addresses);
        } else {
            found.getAddresses().add(saveAddress);
        }
        userService.updateUser(userId, found);
        log.info("address has been successfully saved {}", saveAddress);

        return saveAddress;
    }

    @Override
    public Address getAddressByUserIdAndId(String userId, String id) {
        User found = userService.getUser(userId);
        List<Address> addresses = found.getAddresses();
        if (null != addresses) {
            Optional<Address> optional = addresses.stream()
                    .filter(address -> address.getId().equals(id))
                    .findFirst();
            if (optional.isPresent()) {
                return optional.get();
            } else {
                throw new AddressFoundException(String.format(Constants.ADDRESS_NOT_FOUND_MSG, userId, id));
            }
        } else {
            throw new AddressFoundException(String.format(Constants.ADDRESS_NOT_FOUND_MSG, userId, id));
        }
    }

    @Override
    public Optional<Address> getAddressById(String id) {
        QAddress qAddress = QAddress.address;
        BooleanExpression eq = qAddress.id.eq(id);
        return addressRepository.findOne(eq);
    }

    @Override
    public Page<Address> getAddressByUserId(String userId, Pageable pageable) {
        User found = userService.getUser(userId);
        List<Address> addresses = found.getAddresses();
        return findAddressesPage(addresses, pageable);
    }

    @Override
    public Address updateAddress(String userId, String id, Address address) {
        Address found = getAddressByUserIdAndId(userId, id);
        address.setId(found.getId());
        Address updatedAddress = addressRepository.save(address);
        log.info("address has been successfully updated {}", updatedAddress);
        return updatedAddress;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Address removeAddress(String userId, String id) {
        Address found = getAddressByUserIdAndId(userId, id);
        User user = userService.getUser(userId);
        user.getAddresses().remove(found);
        userService.updateUser(userId, user);
        log.info("address has been successfully removed {}", found);
        return found;
    }

    private Page<Address> findAddressesPage(List<Address> addresses, Pageable pageable) {
        if (null != addresses && addresses.size() > 0) {
            // Create a Page using PageImpl
            int start = (int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), addresses.size());
            List<Address> pageContent = addresses.subList(start, end);
            return new PageImpl<>(pageContent, pageable, addresses.size());
        } else {
            return new PageImpl<>(new ArrayList<>(), pageable, 0);
        }
    }

}
