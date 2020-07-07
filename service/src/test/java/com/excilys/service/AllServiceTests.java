package com.excilys.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({CompanyServiceTest.class, ComputerServiceTest.class,
        ValidatorsTest.class})
public class AllServiceTests {

}
