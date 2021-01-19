package com.kts.cultural_content.suites;

import com.kts.cultural_content.controller.FotografijaControllerIntegrationTest;

import com.kts.cultural_content.controller.RecenzijaControllerIntegrationTest;
import com.kts.cultural_content.service.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.test.context.TestPropertySource;

@RunWith(Suite.class)
@Suite.SuiteClasses({ RecenzijaServiceIntegrationTest.class,FotografijaControllerIntegrationTest.class, RecenzijaServiceUnitTest.class,
        FotografijaServiceIntegrationTest.class, FotografijaServiceUnitTest.class, RecenzijaControllerIntegrationTest.class})
@TestPropertySource("classpath:test.properties")
public class SuiteAllAboutOcenaKomentarFotografija {
}
