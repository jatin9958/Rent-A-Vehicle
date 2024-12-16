package com.service.vehicle_rental_service.service.impl;

import com.service.vehicle_rental_service.entity.BookingPoint;
import com.service.vehicle_rental_service.entity.VehicleAddition;
import com.service.vehicle_rental_service.model.VehicleModel;
import com.service.vehicle_rental_service.repository.BookingPointRepository;
import com.service.vehicle_rental_service.repository.VehicleAdditionRepository;
import com.service.vehicle_rental_service.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleAdditionRepository vehicleAdditionRepository;

    @Autowired
    private BookingPointRepository bookingPointRepository;

    @Override
    public ResponseEntity<String> addVehicle(VehicleModel vehicleModel, String adminUserName) {

        try {
            Optional<VehicleAddition> vehicleDetails = vehicleAdditionRepository.findById(vehicleModel.getVehicleNo());
            if (vehicleDetails.isPresent()) {
                return ResponseEntity.ok("'" + vehicleModel.getVehicleNo() + "' Vehicle Registered Already for Rent.");
            }
        } catch (Exception exception) {
            log.error("Exception occurred while finding the vehicle data in db, ex");
            return ResponseEntity.internalServerError().body("Please try again later");
        }

        VehicleAddition vehicleAddition = new VehicleAddition();

        vehicleAddition.setVehicleNo(vehicleModel.getVehicleNo());
        vehicleAddition.setModel(vehicleModel.getModel());
        vehicleAddition.setLocation(vehicleModel.getLocation());
        vehicleAddition.setType(vehicleModel.getType());
        vehicleAddition.setPrice(vehicleModel.getPrice());
        vehicleAddition.setAvailability(vehicleModel.getAvailability());
        vehicleAddition.setUserName(adminUserName);

        try {
            vehicleAdditionRepository.save(vehicleAddition);
        } catch (Exception ex) {
            log.error("Exception occurred during saving vehicle data in db, ex");
            return ResponseEntity.internalServerError().body("Please try again later");

        }
        return ResponseEntity.ok("'" + vehicleModel.getVehicleNo() + "' Registered Successfully for Rent.");
    }

    @Override
    public ResponseEntity<List<VehicleModel>> getAllVehicle() {
        List<VehicleAddition> vehicleDetails = new ArrayList<>();
        List<VehicleModel> vehicleModels = new ArrayList<>();

        try {
         vehicleDetails = vehicleAdditionRepository.findAll();
        }
        catch (Exception ex) {
            log.error("Exception occurred while finding all vehicle data in db, ex");
            return ResponseEntity.internalServerError().body(vehicleModels);
        }

        for(VehicleAddition vehicles : vehicleDetails) {
            if(vehicles.getAvailability()){
                VehicleModel newVehicle = new VehicleModel();
                BeanUtils.copyProperties(vehicles, newVehicle);
                vehicleModels.add(newVehicle);
            }

        }

        return ResponseEntity.ok(vehicleModels);
    }


    @Override
    public ResponseEntity<List<VehicleModel>> getVehiclesByAdmin(String adminUserName) {
        List<VehicleAddition> vehicleDetails;
        List<VehicleModel> vehicleModels = new ArrayList<>();

        try {
            vehicleDetails = vehicleAdditionRepository.findAll();
        }
        catch (Exception ex) {
            log.error("Exception occurred while finding vehicle data by admin in db, ex");
            return ResponseEntity.internalServerError().body(vehicleModels);
        }

        for(VehicleAddition vehicles : vehicleDetails) {
            if(adminUserName.equalsIgnoreCase(vehicles.getUserName())){
                VehicleModel newVehicle = new VehicleModel();
                BeanUtils.copyProperties(vehicles, newVehicle);
                vehicleModels.add(newVehicle);
            }
        }

        return ResponseEntity.ok(vehicleModels);
    }

    @Override
    public ResponseEntity<String> makeCurrentVehicleChanges(VehicleModel vehicleModel) {

        try {
            Optional<VehicleAddition> vehicle  = vehicleAdditionRepository.findById(vehicleModel.getVehicleNo());
            VehicleAddition newVehicle = vehicle.get();
            newVehicle.setModel(vehicleModel.getModel());
            newVehicle.setLocation(vehicleModel.getLocation());
            newVehicle.setType(vehicleModel.getType());
            newVehicle.setPrice(vehicleModel.getPrice());
            newVehicle.setAvailability(vehicleModel.getAvailability());
            vehicleAdditionRepository.save(newVehicle);
        }
        catch (Exception ex) {
            log.error("Exception occurred while finding the vehicle in db, ex");
            return ResponseEntity.internalServerError().body("Something Went Wrong, Please try again later!");
        }
        return ResponseEntity.ok("Some Changes done with vehicles details by admin");
    }

    @Override
    public ResponseEntity<String> bookVehicle(String vehicleId, String customer, Date returnDate) {
        try {
            Optional<VehicleAddition> vehicle  = vehicleAdditionRepository.findById(vehicleId);
            VehicleAddition newVehicle = vehicle.get();
            newVehicle.setAvailability(!newVehicle.getAvailability());
            vehicleAdditionRepository.save(newVehicle);

            BookingPoint bookedVehicle = new BookingPoint();
            bookedVehicle.setBookingId(vehicleId);
            bookedVehicle.setUserName(customer);
            bookedVehicle.setOwner(newVehicle.getUserName());
            bookedVehicle.setBookingDate(new Date());
            bookedVehicle.setReturnDate(returnDate);
            bookingPointRepository.save(bookedVehicle);
        }
        catch (Exception ex) {
            log.error("Exception occurred while finding the vehicles in db, ex");
            return ResponseEntity.internalServerError().body("Something Went Wrong, Please try again later!");
        }
        return ResponseEntity.ok("booked successful for rent");

    }


}
