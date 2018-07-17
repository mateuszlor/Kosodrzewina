package com.spring.start.controller.rents;

import com.spring.start.annotations.RestAPIController;
import com.spring.start.entity.Rent;
import com.spring.start.service.RentService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RestAPIController
@Log4j
public class RentRestController {

    private static final String GET_RENTS= "getRents";

    @Autowired
    @Getter
    @Setter
    private RentService rentService;

    @GetMapping(value = GET_RENTS)
    public Iterable<Rent> getGroupedRentsByRest(Model model) {
        log.info("REST: Pobrano listę wyporzyczeń");
        return rentService.findAllRentsWithoutAdditionalCarCarrier();
    }

}
