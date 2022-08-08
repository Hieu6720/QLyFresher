package com.management.fresher.service;

import com.management.fresher.dto.FresherDto;
import com.management.fresher.entity.Fresher;
import com.management.fresher.exception.CustomException;
import com.management.fresher.mapper.FresherMapper;
import com.management.fresher.repository.CenterRepository;
import com.management.fresher.repository.FresherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FresherService {
    private final FresherRepository repository;
    private final CenterRepository centerRepository;
    private final FresherMapper mapper;

    @Autowired
    public FresherService(FresherRepository repository, CenterRepository centerRepository, FresherMapper mapper) {
        this.repository = repository;
        this.centerRepository = centerRepository;
        this.mapper = mapper;
    }

    public List<FresherDto> getFresherByName(String name){
        List<FresherDto> res = new ArrayList<>();
        List<Fresher> fresherList = repository.findByNameContainsIgnoreCase(name);
        fresherList.forEach(fresher -> res.add(mapper.EntityToDto(fresher)));
        return res;
    }

    public FresherDto getFresherByEmail(String email){
        if(!repository.existsByEmail(email)) return null;
        return mapper.EntityToDto(repository.getByEmail(email));
    }

    public List<FresherDto> getFresherByProgramLanguage(String programLanguage){
        List<FresherDto> res = new ArrayList<>();
        List<Fresher> fresherList = repository.findByProgramLanguageIgnoreCase(programLanguage);
        fresherList.forEach(fresher -> res.add(mapper.EntityToDto(fresher)));
        return res;
    }

    public List<FresherDto> getAllFresher(){
        List<FresherDto> res = new ArrayList<>();
        List<Fresher> fresherList = repository.findAll();
        fresherList.forEach(fresher -> res.add(mapper.EntityToDto(fresher)));
        return res;
    }

    public List<FresherDto> getFresherByCenterId(Long centerId){
        if(!centerRepository.existsById(centerId)) throw new CustomException(HttpStatus.BAD_REQUEST.value(), "Center not found");
        List<FresherDto> res = new ArrayList<>();
        List<Fresher> fresherList = repository.findByCenterId(centerId);
        fresherList.forEach(fresher -> res.add(mapper.EntityToDto(fresher)));
        return res;
    }

    public List<FresherDto> getFresherByTestScore(Float score, String type){
        List<FresherDto> res = new ArrayList<>();
        List<Fresher> fresherList = new ArrayList<>();
        switch (type) {
            case "test1" -> fresherList.addAll(repository.findByTestScore1(score));
            case "test2" -> fresherList.addAll(repository.findByTestScore2(score));
            case "test3" -> fresherList.addAll(repository.findByTestScore3(score));
            case "final" -> fresherList.addAll(repository.findByFinalScore(score));
            default -> throw new CustomException(HttpStatus.BAD_REQUEST.value(), "Bad request");
        }
        fresherList.forEach(fresher -> res.add(mapper.EntityToDto(fresher)));
        return res;
    }

    public void create(FresherDto fresherDto){
        if(repository.existsByEmail(fresherDto.getEmail())) throw new CustomException(HttpStatus.BAD_REQUEST.value(), "Email already exist");
        Fresher fresher = mapper.DtoToEntity(fresherDto);
        fresher.setId(null);
        if(fresher.getTestScore1() != null && fresher.getTestScore2() != null && fresher.getTestScore3() != null){
            fresher.setFinalScore((fresher.getTestScore1()+fresher.getTestScore2()+fresher.getTestScore3())/3);
        }
        repository.save(fresher);
    }

    public void update(FresherDto fresherDto){
        if(!repository.existsById(fresherDto.getId())) throw new CustomException(HttpStatus.BAD_REQUEST.value(), "not found");
        if(repository.existsByEmail(fresherDto.getEmail())) throw new CustomException(HttpStatus.BAD_REQUEST.value(), "Email already exist");
        Fresher fresher = mapper.DtoToEntity(fresherDto);
        if(fresher.getTestScore1() != null && fresher.getTestScore2() != null && fresher.getTestScore3() != null){
            fresher.setFinalScore((fresher.getTestScore1()+fresher.getTestScore2()+fresher.getTestScore3())/3);
        }
        repository.save(fresher);
    }

    public void delete(Long id){
        if(!repository.existsById(id)) throw new CustomException(400, "id not found");
        repository.deleteById(id);
    }
}
