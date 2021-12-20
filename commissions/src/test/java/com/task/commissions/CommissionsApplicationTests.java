package com.task.commissions;

import com.task.commissions.service.CommissionServiceTest;
import com.task.commissions.service.strategy.StrategyChangerTest;
import org.junit.platform.suite.api.Suite;
import org.junit.runners.Suite.SuiteClasses;

@Suite
@SuiteClasses({ StrategyChangerTest.class, CommissionServiceTest.class })
class CommissionsApplicationTests {
}
