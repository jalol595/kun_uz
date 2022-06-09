package com.company.kun_uz.service;

import com.company.kun_uz.dto.AuthDto;
import com.company.kun_uz.dto.ProfileDto;
import com.company.kun_uz.entity.ProfileEntity;
import com.company.kun_uz.enums.ProfileStatus;
import com.company.kun_uz.exps.ItemNotFoundEseption;
import com.company.kun_uz.repository.ProfileRepository;
import com.company.kun_uz.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private ProfileRepository profileRepository;


    public ProfileDto auth(AuthDto authDto) {

        Optional<ProfileEntity> profile = profileRepository.findByPhone(authDto.getPhone());
        if (profile.isEmpty()) {
            throw new ItemNotFoundEseption("profile not found");
        }
        ProfileEntity entity = profile.get();

        if (!entity.getPhone().equals(authDto.getPhone())) {
            throw new ItemNotFoundEseption("profile not found");
        }

        if (!entity.getStatus().equals(ProfileStatus.ACTIVE)) {
            throw new ItemNotFoundEseption("You are blocked");
        }

        ProfileDto dto = new ProfileDto();

        dto.setName(entity.getName());
        dto.setSurName(entity.getSurName());
        dto.setJwt(JwtUtil.encode1(entity.getId(), entity.getRole()));

        return dto;
    }
}
