package com.rescueMeal.service.impl;
import com.rescueMeal.dao.FoodPostDAO;
import com.rescueMeal.dao.NgoDAO;
import com.rescueMeal.dao.UserDAO;
import com.rescueMeal.dto.DonorDTO;
import com.rescueMeal.dto.NgoDTO;
import com.rescueMeal.dto.NgoResponseDTO;
import com.rescueMeal.exceptionClasses.FoodPostNotFound;
import com.rescueMeal.exceptionClasses.NgoNotFoundException;
import com.rescueMeal.exceptionClasses.NgoNotRegisteredException;
import com.rescueMeal.model.FoodPost;
import com.rescueMeal.model.NGO;
import com.rescueMeal.model.User;
import com.rescueMeal.service.NGOService;
import com.rescueMeal.utils.Roles;
import com.rescueMeal.utils.Status;
import jakarta.transaction.Transactional;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class NGOServiceImpl implements NGOService {

    @Autowired
    private NgoDAO ngoDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FoodPostDAO foodPostDAO;

    @Autowired
    private UserDAO userDAO;



    @Override
    @Transactional
    public NgoResponseDTO acceptThePost(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        FoodPost foodPost = foodPostDAO.findById(id)
                .orElseThrow(() -> new FoodPostNotFound("Food to Donate is not present with the id: " + id));
        User u= (User) authentication.getPrincipal();
        foodPost.setStatus(Status.CLAIMED);
        User user=userDAO.findById(u.getId()).orElseThrow(()->new NgoNotFoundException("NGO is present"));
        foodPost.setNgoName(user.getName());
        foodPost.setNgoemail(user.getEmail());
        NgoResponseDTO ngoResponseDTO=modelMapper.map(foodPost,NgoResponseDTO.class);
        return ngoResponseDTO;
    }

    @Override
    public NGO findById(Long ngoId) {
        NGO ngo=ngoDAO.findById(ngoId).orElseThrow(()->new NgoNotFoundException("Ngo not found with id "+ngoId));
        return ngo;
    }

}
