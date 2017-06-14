package fr.fmaire.fnag;

import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.fmaire.fnag.report.Report;
import fr.fmaire.fnag.report.ReportBuilder;
import fr.fmaire.fnag.report.ReportStatistics;
import fr.fmaire.fnag.report.Tools;

/**
 * Main for a demo showing the acceptance criteria.
 *
 * @author F.MAIRE(FMI)
 *
 */
public class FnagDemoMain {

    private static final Logger LOG = LoggerFactory.getLogger(FnagDemoMain.class);

    /**
     * @param args
     */
    public static void main(final String[] args) {
        try {
            String reportName = "report1.txt";
            if ((args.length == 1) && (args[0] != null)) {
                reportName = args[0];
            }
            final URL repUrl = FnagDemoMain.class.getClassLoader().getResource(reportName);
            LOG.info("Readin report file: " + repUrl);
            final String reportStr = Tools.readFile(repUrl.toURI(), StandardCharsets.UTF_8);
            LOG.info("Report file:\n{}", reportStr);

            final Report report = ReportBuilder.buildFromString(reportStr);

            final String topSale = ReportStatistics.findTopSale(report);
            final String topSeller = ReportStatistics.findTopSeller(report);

            LOG.info("\n{}\n{}", topSale, topSeller);

        } catch (final Exception e) {
            LOG.error("Demo failed %s", e.getMessage());
        }
    }

}
