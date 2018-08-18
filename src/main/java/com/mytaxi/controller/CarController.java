package com.mytaxi.controller;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.service.car.CarService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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


    @GetMapping
    public List<CarDO> findAvailableCars()
    {
        List<CarDO> cars = carService.find(true);
        return cars;
    }
}
