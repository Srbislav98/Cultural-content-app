package com.kts.cultural_content.suites;

import com.kts.cultural_content.service.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.test.context.TestPropertySource;

@RunWith(Suite.class)
@SuiteClasses({UlogaServiceIntegrationTest.class})
@TestPropertySource("classpath:test.properties")
public class SuiteAll {


}
