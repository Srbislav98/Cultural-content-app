package com.kts.cultural_content.suites;

import com.kts.cultural_content.controller.AdminControllerIntegrationTest;
import com.kts.cultural_content.controller.AuthenticationControllerIntegrationTest;
import com.kts.cultural_content.controller.RegistrovaniKorisnikControllerIntegrationTest;
import com.kts.cultural_content.repository.*;
import com.kts.cultural_content.service.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.test.context.TestPropertySource;

@RunWith(Suite.class)
@SuiteClasses({AdminControllerIntegrationTest.class, AuthenticationControllerIntegrationTest.class,
        RegistrovaniKorisnikControllerIntegrationTest.class, AdminServiceIntegrationTest.class, AdminServiceUnitTest.class,
CustomUserDetailsServiceIntegrationTest.class, CustomUserDetailsServiceUnitTest.class, EmailServiceIntegrationTest.class,
EmailServiceUnitTest.class,KorisnikServiceIntegrationTest.class,KorisnikServiceUnitTest.class,RegistrovaniKorisnikControllerIntegrationTest.class,
RegistrovaniKorisnikServiceUnitTest.class,UlogaServiceIntegrationTest.class,VerificationTokenServiceIntegrationTest.class,
        AdminRepositoryIntegrationTest.class, KorisnikRepositoryIntegrationTest.class, RegistrovaniKorisnikRepositoryIntegrationTest.class,
        UlogaRepositoryIntegrationTest.class,VerificationTokenRepositoryIntegrationTest.class})
@TestPropertySource("classpath:test.properties")
public class SuiteAllAboutUser {
}
