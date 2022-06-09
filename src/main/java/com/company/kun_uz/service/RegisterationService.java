package com.company.kun_uz.service;

import com.company.kun_uz.dto.ProfileDto;
import com.company.kun_uz.entity.ProfileEntity;
import com.company.kun_uz.exps.AlreadyExistPhone;
import com.company.kun_uz.exps.BadRequestException;
import com.company.kun_uz.repository.ProfileRepository;
import com.company.kun_uz.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterationService {

    @Autowired
    private ProfileRepository profileRepository;

    public ProfileDto create(ProfileDto profileDto) {

        Optional<ProfileEntity> entity = profileRepository.findByPhone(profileDto.getPhone());
        if (entity.isPresent()){
            throw new AlreadyExistPhone("Already exist phone");
        }

        isValid(profileDto);

        ProfileEntity profile=new ProfileEntity();
        profile.setName(profileDto.getName());
        profile.setSurName(profileDto.getSurName());
        profile.setPhone(profileDto.getPhone());
        profile.setEmail(profileDto.getEmail());

        profileRepository.save(profile);

        ProfileDto responseDTO=new ProfileDto();
        responseDTO.setName(profileDto.getName());
        responseDTO.setSurName(profileDto.getSurName());
        responseDTO.setPhone(profileDto.getPhone());
        responseDTO.setEmail(profileDto.getEmail());
        responseDTO.setJwt(JwtUtil.encode1(profile.getId(), profile.getRole()));

        return responseDTO;
    }

    private void isValid(ProfileDto dto) {

        if (dto.getName() == null || dto.getName().length() < 3) {
            throw new BadRequestException("wrong name");
        }

        if (dto.getSurName() == null || dto.getSurName().length() < 4) {
            throw new BadRequestException("surname required.");
        }

        if (dto.getEmail() == null || dto.getEmail().length() < 3) {
            throw new BadRequestException("email required.");
        }

        if (dto.getPhone() == null || dto.getPhone().length() < 10) {
            throw new BadRequestException("phone required.");
        }


    }

}
