package com.mytaxi.controller;

import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/search")
public class SearchController
{
    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private CarRepository carRepository;


    @GetMapping("/drivers/onlineStatus")
    public List<DriverDTO> findDrivers(@RequestParam OnlineStatus onlineStatus)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        return DriverMapper.makeDriverDTOList(driverRepository.findByOnlineStatus(onlineStatus));
    }


    @GetMapping("/drivers/username")
    public List<DriverDTO> findDriversByUsername(
        @RequestParam String username)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        return DriverMapper.makeDriverDTOList(driverRepository
            .findAllByUsernameIsContaining(username));
    }


    @GetMapping("/drivers/attributes")
    public List<DriverDTO> findDriversByAttributes(
        @RequestParam(required = false) OnlineStatus onlineStatus, @RequestParam(required = false) String username)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        return DriverMapper.makeDriverDTOList(driverRepository
            .findAllByOnlineStatusEqualsAndUsernameIsContaining(onlineStatus, username));
    }


    @GetMapping("/cars/attributes")
    public List<CarDTO> findCarsByAttributes(
        @RequestParam(required = false) String licensePlate, @RequestParam(required = false) Integer seatCount)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        return CarMapper.makeCarDTOList(carRepository
            .findAllByLicensePlateContainsOrSeatCountIsGreaterThanEqual(licensePlate, seatCount));
    }


    @GetMapping("/cars/manufacturer")
    public List<CarDTO> findCarsByManufacturer(
        @RequestParam(required = false) String manufacturer)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        return CarMapper.makeCarDTOList(carRepository.findAllByManufacturerIsStartingWith(manufacturer));
    }


}
