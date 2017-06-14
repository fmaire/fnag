package fr.fmaire.fnag.report;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author F.MAIRE(FMI)
 *
 */
public class ReportBuilderTest {

    private final Logger log = LoggerFactory.getLogger(ReportBuilderTest.class);

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void reading_nominal_report() throws ReportException {
        // testing that the nominal report (AC1) is correctly read
        // GIVEN
        // WHEN
        final Report report = ReportBuilder.buildFromFile("report1.txt");
        // THEN
        this.log.info("Report :\n{}", report);
        assertEquals("Number of products", 3, ReportStatistics.getNumberOfProducts(report));
        assertEquals("Number of sales", 5, ReportStatistics.getNumberOfSales(report));
    }

}
