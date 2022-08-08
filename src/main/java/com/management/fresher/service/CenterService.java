package com.management.fresher.service;

import com.management.fresher.dto.CenterDto;
import com.management.fresher.entity.Center;
import com.management.fresher.entity.Fresher;
import com.management.fresher.exception.CustomException;
import com.management.fresher.mapper.CenterMapper;
import com.management.fresher.repository.CenterRepository;
import com.management.fresher.repository.FresherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CenterService {
    private final CenterRepository repository;
    private final CenterMapper mapper;
    private final FresherRepository fresherRepository;

    @Autowired
    public CenterService(CenterRepository repository, CenterMapper mapper, FresherRepository fresherRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.fresherRepository = fresherRepository;
    }

    public List<CenterDto> getAllCenter(){
        List<CenterDto> res = new ArrayList<>();
        repository.findAll().forEach(center -> res.add(mapper.EntityToDto(center)));
        return res;
    }

    public void addFresherToCenter(Long centerId, Long fresherId){
        if(!repository.existsById(centerId)) throw new CustomException(HttpStatus.BAD_REQUEST.value(), "center id not found");
        if(!fresherRepository.existsById(fresherId)) throw new CustomException(HttpStatus.BAD_REQUEST.value(), "fresher id not found");
        Fresher fresher = fresherRepository.findById(fresherId).get();
        fresher.setCenter(repository.findById(centerId).get());
        fresherRepository.save(fresher);
    }

    public void create(CenterDto centerDto){
        Center center = mapper.DtoToEntity(centerDto);
        center.setId(null);
        repository.save(center);
    }

    public void update(CenterDto centerDto){
        if(!repository.existsById(centerDto.getId())) throw new CustomException(HttpStatus.BAD_REQUEST.value(), "Bad request");
        Center center = repository.findById(centerDto.getId()).get();
        center.setName(center.getName());
        center.setAddress(center.getAddress());
        center.setDescription(center.getDescription());
        repository.save(center);
    }

    public void delete(Long id){
        if(!repository.existsById(id)) throw new CustomException(HttpStatus.BAD_REQUEST.value(), "Id not found");
        repository.deleteById(id);
    }
}
