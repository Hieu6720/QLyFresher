package com.management.fresher.mapper;

import com.management.fresher.dto.CenterDto;
import com.management.fresher.entity.Center;
import org.springframework.stereotype.Component;

@Component
public class CenterMapper implements Mapper<CenterDto, Center> {
    @Override
    public Center DtoToEntity(CenterDto centerDto) {
        Center res = new Center();
        res.setId(centerDto.getId());
        res.setName(centerDto.getName());
        res.setAddress(centerDto.getAddress());
        res.setDescription(centerDto.getDescription());
        return res;
    }

    @Override
    public CenterDto EntityToDto(Center center) {
        CenterDto res = new CenterDto();
        res.setId(center.getId());
        res.setName(center.getName());
        res.setAddress(center.getAddress());
        res.setDescription(center.getDescription());
        return res;
    }
}
