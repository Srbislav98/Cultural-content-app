package com.kts.cultural_content.service;

import com.kts.cultural_content.model.VerificationToken;
import com.kts.cultural_content.repository.UlogaRepository;
import com.kts.cultural_content.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationTokenService {
    @Autowired
    private VerificationTokenRepository vtRepository;

    public VerificationToken findByToken(String url) {
        return vtRepository.findByToken(url);
    }
}
