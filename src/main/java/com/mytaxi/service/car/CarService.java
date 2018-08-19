package com.mytaxi.service.car;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import java.util.List;

public interface CarService
{

    CarDO find(Long carId) throws EntityNotFoundException;

    CarDO create(CarDO carDO) throws ConstraintsViolationException;

    void delete(Long carId) throws EntityNotFoundException;

    CarDO selectCar(long carId) throws EntityNotFoundException, CarAlreadyInUseException;

    void dropSelectedCar(long carId) throws EntityNotFoundException;

    void updateLocation(long carId, double longitude, double latitude) throws EntityNotFoundException;

    void updateAvailable(long carId, boolean available) throws EntityNotFoundException;

    List<CarDO> find(Boolean available);

    List<CarDO> findAll();


}
