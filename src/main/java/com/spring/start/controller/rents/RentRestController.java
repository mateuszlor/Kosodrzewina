package com.spring.start.controller.rents;

import com.spring.start.entity.Rent;
import com.spring.start.service.RentService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j
public class RentRestController {

    private static final String SLASH = "/";
    private static final String GET_RENTS= "getRents";
    private static final String REST = "rest";

    @Autowired
    @Getter
    @Setter
    private RentService rentService;

    @RequestMapping(value = SLASH + REST + SLASH + GET_RENTS, method = RequestMethod.GET)
    public Iterable<Rent> getGroupedRentsByRest(Model model) {
        log.info("REST: Pobrano listę wyporzyczeń");
        return rentService.findAllRentsWithoutAdditionalCarCarrier();
    }

}
