package com.rescueMeal.service.impl;
import com.rescueMeal.dao.NgoDAO;
import com.rescueMeal.dto.NgoDTO;
import com.rescueMeal.exceptionClasses.NgoNotFoundException;
import com.rescueMeal.exceptionClasses.NgoNotRegisteredException;
import com.rescueMeal.model.NGO;
import com.rescueMeal.service.NGOService;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NGOServiceImpl implements NGOService {

    @Autowired
    private NgoDAO ngoDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public long registerNGO(NgoDTO ngoDTO) {
        try {
            NGO ngo=modelMapper.map(ngoDTO,NGO.class);
            GeometryFactory geometryFactory = new GeometryFactory();
            double latitude = ngoDTO.getLatitude();
            double longitude = ngoDTO.getLongitude();
            Point location = geometryFactory.createPoint(new Coordinate(longitude, latitude));
            ngo.setLocation(location);
            ngoDAO.save(ngo);
            return ngo.getId();
        }catch (Exception e){
            throw new NgoNotRegisteredException("An error occurred while registering the NGO");
        }
    }

    @Override
    public NGO findById(Long id) {
        return ngoDAO.findById(id).orElseThrow(()->new NgoNotFoundException("NGO not present with id :"+id));
    }
}
