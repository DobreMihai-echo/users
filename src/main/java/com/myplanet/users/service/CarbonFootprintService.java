package com.myplanet.users.service;

import com.myplanet.users.entity.CarbonFootprint;
import com.myplanet.users.entity.User;
import com.myplanet.users.repository.CarbonFootprintRepository;
import com.myplanet.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarbonFootprintService {

    @Autowired
    private CarbonFootprintRepository repository;

    @Autowired
    private UserRepository userRepository;

    public void addCarbonFootprintForUser(CarbonFootprint carbonFootprint, String username) {
        try {
            User user = userRepository.findByUsername(username).orElseThrow(RuntimeException::new);

            CarbonFootprint footprint = CarbonFootprint.builder()
                    .carEmissionCo2(carbonFootprint.getCarEmissionCo2())
                    .energyEmissionCo2(carbonFootprint.getEnergyEmissionCo2())
                    .foodEmissionCo2(carbonFootprint.getFoodEmissionCo2())
                    .planeEmissionCo2(carbonFootprint.getPlaneEmissionCo2())
                    .transitEmissionCo2(carbonFootprint.getTransitEmissionCo2())
                    .fuelEmissionCo2(carbonFootprint.getFuelEmissionCo2())
                    .user(user)
                    .killedTrees(carbonFootprint.getKilledTrees())
                    .totalEmissionCo2(carbonFootprint.getTotalEmissionCo2())
                    .totalOffset(carbonFootprint.getTotalOffset())
                    .date(carbonFootprint.getDate())
                    .build();

            repository.save(footprint);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public List<CarbonFootprint> getCarbonFootprintsOfUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(RuntimeException::new);

        return user.getCarbonFootprints();
    }
}
