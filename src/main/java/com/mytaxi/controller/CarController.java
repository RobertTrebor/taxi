package com.mytaxi.controller;

import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.service.car.CarService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * All operations with a car will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/cars")
public class CarController
{

    @Autowired
    private CarService carService;


    @GetMapping("/available")
    public List<CarDTO> findCars(@RequestParam boolean available)
    {
        return CarMapper.makeCarDTOList(carService.find(available));
    }


    @GetMapping("/all")
    public List<CarDTO> findAllCars()
    {
        return CarMapper.makeCarDTOList(carService.findAll());
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO createCar(@Valid @RequestBody CarDTO carDTO) throws ConstraintsViolationException
    {
        CarDO carDO = CarMapper.makeCarDO(carDTO);
        return CarMapper.makeCarDTO(carService.create(carDO));
    }

}
