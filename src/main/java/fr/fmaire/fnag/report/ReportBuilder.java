package fr.fmaire.fnag.report;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Building a report according to the report specification.
 *
 * @author F.MAIRE(FMI)
 *
 */
public final class ReportBuilder {

    private ReportBuilder() {
    }

    /**
     * Read a report from a string.
     *
     * @param reportStr the report in a string form
     * @return the report parsed
     * @throws ReportException when the report is not conform
     */
    public static Report buildFromString(final String reportStr) throws ReportException {
        final Report report = new Report();
        try {
            final BufferedReader reportReader = new BufferedReader(new StringReader(reportStr));

            // parsing the products
            final int nbProducts = Integer.parseInt(reportReader.readLine());
            if ((nbProducts > Report.MAX_PRODUCTS) || (nbProducts < 0)) {
                throw new ReportException("Illegal number of products:" + nbProducts);
            }
            for (int i = 0; i < nbProducts; i++) {
                report.addProduct(ProductBuilder.buildFromString(reportReader.readLine()));
            }

            // parsing the sales
            final int nbSales = Integer.parseInt(reportReader.readLine());
            if ((nbSales > Report.MAX_SALES) || (nbSales < 0)) {
                throw new ReportException("Illegal number of sales:" + nbSales);
            }
            for (int i = 0; i < nbSales; i++) {
                report.addSale(SaleBuilder.buildFromString(reportReader.readLine()));
            }

            // check if more sale not counted
            final String line = reportReader.readLine();
            if (line != null) {
                throw new ReportException("Missing some sale record:" + nbSales);
            }

        } catch (final ReportException e) {
            throw e;
        } catch (final Exception e) {
            // no spec for erroneous report management
            throw new ReportException("Invalid report", e);
        }
        return report;
    }

    /**
     * Read a report from a file.
     *
     * @param filename of a file report
     * @return the report read
     * @throws ReportException when the report is not conform or the file not found
     */
    public static Report buildFromFile(final String filename) throws ReportException {
        try {
            final URL repUrl = ReportBuilder.class.getClassLoader().getResource(filename);
            final byte[] encoded = Files.readAllBytes(Paths.get(repUrl.toURI()));
            final String reportStr = new String(encoded, StandardCharsets.UTF_8);
            return ReportBuilder.buildFromString(reportStr);
        } catch (IOException | URISyntaxException | NullPointerException e) {
            throw new ReportException("Fail to read report file: " + filename, e);
        }
    }

}
