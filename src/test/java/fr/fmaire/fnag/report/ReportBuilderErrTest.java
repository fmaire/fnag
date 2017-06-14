package fr.fmaire.fnag.report;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

/**
 * Report reading and parsing, error cases.
 *
 * @author F.MAIRE(FMI)
 *
 */
@RunWith(JUnitParamsRunner.class)
public class ReportBuilderErrTest {

    private final Logger log = LoggerFactory.getLogger(ReportBuilderErrTest.class);

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @SuppressWarnings("unused")
    private Object[] parametersForErrorReport() {
        return new Object[][] {
            // @formatter:off
            { "report_err_empty.txt", "Invalid report"},
            { "report_err_invalidprice.txt", "Invalid 2 decimal price: 200.999"},
            { "report_err_invalidprice_nan.txt", "Invalid price"},
            { "report_err_duplicateproduct.txt", "Duplicate product"},
            { "report_err_toomuchproducts.txt", "Illegal number of products:999"},
            { "report_err_invalidproductscount.txt", "Illegal number of products:-1"},
            { "report_err_invalidproductscount_nan.txt", "Invalid report"},
            { "report_err_toomuchsales.txt", "Illegal number of sales:9999"},
            { "report_err_invalidsalescount.txt", "Illegal number of sales:-1"},
            { "report_err_invalidsalescount_nan.txt", "Invalid report"},
            { "report_err_invalidproduct.txt", "Invalid number of parts"},
            { "report_err_invalidsale.txt", "Invalid number of parts"},
            { "report_err_unknownproduct.txt", "Cannot sell a product with unknown reference GeekProd"},
            { "report_err_wrongproductscount_max.txt", "Invalid report"},
            { "report_err_wrongproductscount_min.txt", "Invalid"},
            { "report_err_wrongsalescount_max.txt", "Missing some sale record"},
            { "report_err_wrongsalescount_min.txt", "Invalid"},
            // @formatter:on
        };
    }

    /**
     * Check the different error during the parsing of the report
     *
     * @param reportName the report file name
     * @param msg the message to check in the exception
     * @throws ReportException
     */
    @Test
    @Parameters(method = "parametersForErrorReport")
    public void errorReport(final String reportName,
            final String msg) throws ReportException {
        // GIVEN
        this.log.info("File: {} => {}", reportName, msg);
        // WHEN THEN
        this.thrown.expect(ReportException.class);
        this.thrown.expectMessage(msg);
        ReportBuilder.buildFromFile(reportName);
    }

    @Test
    public void error_report_not_found() throws ReportException {
        // Having a report name that is not found
        // GIVEN
        final String reportName = "fake-name.txt";
        // WHEN THEN
        this.thrown.expect(ReportException.class);
        this.thrown.expectMessage("Fail to read");
        ReportBuilder.buildFromFile(reportName);
    }

}
