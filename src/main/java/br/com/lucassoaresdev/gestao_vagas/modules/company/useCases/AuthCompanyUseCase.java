package br.com.lucassoaresdev.gestao_vagas.modules.company.useCases;

import javax.naming.AuthenticationException;

import br.com.lucassoaresdev.gestao_vagas.exceptions.UserNotFoundException;
import br.com.lucassoaresdev.gestao_vagas.modules.company.dto.AuthCompanyRequestDTO;
import br.com.lucassoaresdev.gestao_vagas.modules.company.dto.AuthCompanyResponseDTO;
import br.com.lucassoaresdev.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.lucassoaresdev.gestao_vagas.modules.company.repositories.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret.company}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCompanyResponseDTO execute(AuthCompanyRequestDTO authCompanyRequestDTO) throws AuthenticationException {
        Optional<CompanyEntity> company = Optional.ofNullable(
                this.companyRepository.findByUsername(authCompanyRequestDTO.username()).orElseThrow(UserNotFoundException::new));

        boolean passwordMatches = this.passwordEncoder.matches(authCompanyRequestDTO.password(), company.get().getPassword());

        if (!passwordMatches)
            throw new AuthenticationException("username/password incorrect");

        Algorithm algorithm = Algorithm.HMAC256(this.secretKey);

        Instant expiresIn = Instant.now().plus(Duration.ofHours(2));

        String token = JWT.create().withIssuer("javagas")
                .withExpiresAt(expiresIn)
                .withSubject(company.get().getId().toString())
                .withClaim("roles", List.of("COMPANY"))
                .sign(algorithm);

        return new AuthCompanyResponseDTO(token, expiresIn.toEpochMilli());
    }
}
