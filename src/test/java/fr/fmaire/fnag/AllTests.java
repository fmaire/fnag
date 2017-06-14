package fr.fmaire.fnag;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.fmaire.fnag.report.ReportBuilderErrTest;
import fr.fmaire.fnag.report.ReportBuilderTest;
import fr.fmaire.fnag.report.ReportStatisticsTest;

@RunWith(value = Suite.class)
@SuiteClasses(value = { ReportBuilderTest.class, ReportBuilderErrTest.class, ReportStatisticsTest.class })
public class AllTests {

}
