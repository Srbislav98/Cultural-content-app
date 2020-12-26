package com.kts.cultural_content.suites;

import com.kts.cultural_content.controller.FotografijaControllerIntegrationTest;
import com.kts.cultural_content.controller.KomentarControllerIntegrationTest;
import com.kts.cultural_content.controller.OcenaControllerIntegrationTest;
import com.kts.cultural_content.service.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.test.context.TestPropertySource;

@RunWith(Suite.class)
@Suite.SuiteClasses({ KomentarServiceIntegrationTest.class,FotografijaControllerIntegrationTest.class, OcenaControllerIntegrationTest.class,
         KomentarServiceUnitTest.class,KomentarControllerIntegrationTest.class, OcenaServiceIntegrationTest.class, OcenaServiceUnitTest.class,
        FotografijaServiceIntegrationTest.class, FotografijaServiceUnitTest.class})
@TestPropertySource("classpath:test.properties")
public class SuiteAllAboutOcenaKomentarFotografija {
}
