package com.sobook.web.validator;

import com.sobook.domain.club.ClubForm;
import com.sobook.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class ClubValidator implements Validator {

    private final ClubRepository clubRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return ClubForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ClubForm clubForm = (ClubForm) target;

        if(clubRepository.existsByPath(clubForm.getPath())) {
            errors.rejectValue("path", "wrong.path", "해당 스터디 경로는 이미 존재합니다.");
        }
    }
}
