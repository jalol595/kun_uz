package com.company.kun_uz.service;

import com.company.kun_uz.dto.AuthDto;
import com.company.kun_uz.dto.ProfileDto;
import com.company.kun_uz.dto.RegionDto;
import com.company.kun_uz.entity.ProfileEntity;
import com.company.kun_uz.entity.RegionEntity;
import com.company.kun_uz.enums.ProfileStatus;
import com.company.kun_uz.exps.AlreadyExist;
import com.company.kun_uz.exps.ItemNotFoundEseption;
import com.company.kun_uz.repository.ProfileRepository;
import com.company.kun_uz.repository.RegionRepository;
import com.company.kun_uz.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public void create(RegionDto regionDto) {

        Optional<RegionEntity> region = regionRepository.findByKey(regionDto.getKey());

        if (region.isPresent()) {
            throw new AlreadyExist("Already exist");
        }

        RegionEntity regionEntity = new RegionEntity();
        regionEntity.setKey(regionDto.getKey());
        regionEntity.setNameUz(regionDto.getNameUz());
        regionEntity.setNameRu(regionDto.getNameRu());
        regionEntity.setNameEn(regionDto.getNameEn());

        regionRepository.save(regionEntity);
    }

    //public
    public List<RegionDto> getList() {

        Iterable<RegionEntity> all = regionRepository.findAllByVisible(true);
        List<RegionDto> dtoList = new LinkedList<>();

        all.forEach(regionEntity -> {
            RegionDto dto = new RegionDto();
            dto.setKey(regionEntity.getKey());
            dto.setNameUz(regionEntity.getNameUz());
            dto.setNameRu(regionEntity.getNameRu());
            dto.setNameEn(regionEntity.getNameEn());
            dtoList.add(dto);
        });
        return dtoList;
    }

    //admin
    public List<RegionDto> getListOnlyForAdmin() {

        Iterable<RegionEntity> all = regionRepository.findAll();
        List<RegionDto> dtoList = new LinkedList<>();

        all.forEach(regionEntity -> {
            RegionDto dto = new RegionDto();
            dto.setKey(regionEntity.getKey());
            dto.setNameUz(regionEntity.getNameUz());
            dto.setNameRu(regionEntity.getNameRu());
            dto.setNameEn(regionEntity.getNameEn());
            dtoList.add(dto);
        });
        return dtoList;
    }

    public void update(Integer id, RegionDto dto) {
        Optional<RegionEntity> regionEntity = regionRepository.findById(id);

        if (regionEntity.isEmpty()) {
            throw new ItemNotFoundEseption("not found region");
        }

        RegionEntity entity = regionEntity.get();

        entity.setKey(dto.getKey());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        regionRepository.save(entity);
    }

    public void delete(Integer id) {

        Optional<RegionEntity> regionEntity = regionRepository.findById(id);

        if (regionEntity.isEmpty()) {
            throw new ItemNotFoundEseption("not found region");
        }

        if (regionEntity.get().getVisible().equals(Boolean.FALSE)){
            throw new AlreadyExist("this region already visible false");
        }

        RegionEntity region = regionEntity.get();

        region.setVisible(Boolean.FALSE);

        regionRepository.save(region);
    }
}
