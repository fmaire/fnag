package fr.fmaire.fnag.report;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tests on Statistics for a report.
 * 
 * @author F.MAIRE(FMI)
 *
 */
public class ReportStatisticsTest {

    private final Logger log = LoggerFactory.getLogger(ReportStatisticsTest.class);

    // test vector from specs
    // report1.txt
    // TOPSALE|T127|T-shirt 'no place like 127.0.0.1'|4
    // TOPSELLER|Lyon|Alice|229.98

    @Test
    public void nominal_topsale() throws ReportException {
        // nominal TOPSALE case from specifications
        // GIVEN
        final Report report = ReportBuilder.buildFromFile("report1.txt");
        // WHEN
        final String topSale = ReportStatistics.findTopSale(report);
        // THEN
        this.log.info(topSale);
        assertEquals("TopSale", "TOPSALE|T127|T-shirt 'no place like 127.0.0.1'|4", topSale);
    }

    @Test
    public void nominal_topseller() throws ReportException {
        // nominal TOPSELLER from the specifications
        // GIVEN
        final Report report = ReportBuilder.buildFromFile("report1.txt");
        // WHEN
        final String topSeller = ReportStatistics.findTopSeller(report);
        // THEN
        this.log.info(topSeller);
        assertEquals("TopSeller", "TOPSELLER|Lyon|Alice|229.98", topSeller);
    }

    @Test
    public void duplicate_topsale() throws ReportException {
        // TOPSALE have more than one element
        // GIVEN
        final Report report = ReportBuilder.buildFromFile("report_duplicatetopsale.txt");
        // WHEN
        final String topSale = ReportStatistics.findTopSale(report);
        // THEN
        this.log.info(topSale);
        assertTrue("LMUSB in top", topSale.indexOf("TOPSALE|LMUSB|Lance-missile USB|5") >= 0);
        assertTrue("USB in top", topSale.indexOf("TOPSALE|MKB|Clavier mécanique|5") >= 0);
        assertTrue("T127 not present", topSale.indexOf("T127") < 0);
    }

    @Test
    public void duplicate_topsale_sum() throws ReportException {
        // multiple top sale, some direct and some with sum, and all products are top sale
        // GIVEN
        final Report report = ReportBuilder.buildFromFile("report_duplicatetopsale2.txt");
        // WHEN
        final String topSale = ReportStatistics.findTopSale(report);
        // THEN
        this.log.info(topSale);
        assertTrue("LMUSB in top", topSale.indexOf("TOPSALE|LMUSB|Lance-missile USB|5") >= 0);
        assertTrue("USB in top", topSale.indexOf("TOPSALE|MKB|Clavier mécanique|5") >= 0);
        assertTrue("T127 in top", topSale.indexOf("TOPSALE|T127|T-shirt 'no place like 127.0.0.1'|5") > 0);
    }

    @Test
    public void duplicate_topseller() throws ReportException {
        // TOPSELLER have more than one element
        // GIVEN
        final Report report = ReportBuilder.buildFromFile("report_duplicatetopseller.txt");
        // WHEN
        final String topSeller = ReportStatistics.findTopSeller(report);
        // THEN
        this.log.info(topSeller);
        // sellers can appear in different order
        assertTrue("TopSeller Alice", topSeller.indexOf("TOPSELLER|Lyon|Alice|229.98") >= 0);
        assertTrue("TopSeller Bob", topSeller.indexOf("TOPSELLER|Paris|Bob|229.98") >= 0);
        assertEquals("Only the 2 topseller", 54, topSeller.length());
    }

    @Test
    public void clone_seller() throws ReportException {
        // A seller with the same name in different shops
        // GIVEN
        final Report report = ReportBuilder.buildFromFile("report_clones.txt");
        // WHEN
        final String topSeller = ReportStatistics.findTopSeller(report);
        // THEN
        this.log.info(topSeller);
        assertEquals("TOPSELLER|Lyon|John Doe|229.98", topSeller);
    }

}
