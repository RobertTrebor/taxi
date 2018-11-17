package com.mytaxi.controller;

import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.CarService;
import com.mytaxi.service.driver.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private CarService carService;


    @GetMapping("/{driverId}")
    public DriverDTO getDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException {
        return DriverMapper.makeDriverDTO(driverService.find(driverId));
    }


    @PutMapping("/{driverId}/select")
    public DriverDTO selectCar(
            @Valid @PathVariable long driverId, @RequestParam long carId) throws EntityNotFoundException, CarAlreadyInUseException {
        DriverDO driverDO = driverService.findOnline(driverId);
        CarDO carDO = carService.selectCar(carId);
        driverService.updateSelectedCar(driverId, carDO);
        return DriverMapper.makeDriverInfoDTO(driverService.find(driverId));
    }


    @PutMapping("/{driverId}/drop")
    public DriverDTO dropSelectedCar(
            @Valid @PathVariable long driverId) throws EntityNotFoundException {
        DriverDO driverDO = driverService.find(driverId);
        if (driverDO.getSelectedCar() != null) {
            carService.dropSelectedCar(driverDO.getSelectedCar().getId());
            driverService.updateSelectedCar(driverId, null);
        }
        return DriverMapper.makeDriverInfoDTO(driverDO);
    }


    @GetMapping("/{driverId}/car")
    public DriverDTO getDriverAndCar(@Valid @PathVariable long driverId) throws EntityNotFoundException {
        return DriverMapper.makeDriverInfoDTO(driverService.find(driverId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException {
        DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);
        return DriverMapper.makeDriverDTO(driverService.create(driverDO));
    }


    @DeleteMapping("/{driverId}")
    public void deleteDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException {
        driverService.delete(driverId);
    }


    @PutMapping("/{driverId}")
    public void updateLocation(
            @Valid @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude)
            throws ConstraintsViolationException, EntityNotFoundException {
        driverService.updateLocation(driverId, longitude, latitude);
    }


    @GetMapping("/onlineStatus")
    public List<DriverDTO> findDrivers(@RequestParam OnlineStatus onlineStatus)
            throws ConstraintsViolationException, EntityNotFoundException {
        return DriverMapper.makeDriverDTOList(driverService.find(onlineStatus));
    }


}
