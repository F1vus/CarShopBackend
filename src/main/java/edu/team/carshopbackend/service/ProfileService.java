package edu.team.carshopbackend.service;

import edu.team.carshopbackend.dto.AuthDTO.ProfileDTO;
import edu.team.carshopbackend.entity.Profile;
import edu.team.carshopbackend.repository.ProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Transactional
    public Profile patchProfile(Long userId, ProfileDTO dto) {
        Profile profile = getProfileByUserId(userId);

        // Aktualizacja tylko przesłanych pól
        if (dto.getName() != null) {
            profile.setName(dto.getName());
        }

        if (dto.getEmail() != null) {
            if (profileRepository.existsByEmail(dto.getEmail()) &&
                    !dto.getEmail().equals(profile.getEmail())) {
                throw new RuntimeException("Email already exists");
            }
            profile.setEmail(dto.getEmail());
        }

        if (dto.getPhoneNumber() != null) {
            profile.setPhoneNumber(dto.getPhoneNumber());
        }

        if (dto.getProfileImage() != null) {
            profile.setProfileImage(dto.getProfileImage());
        }

        return profileRepository.save(profile);
    }

    private Profile getProfileByUserId(Long userId) {
        return profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }
}
