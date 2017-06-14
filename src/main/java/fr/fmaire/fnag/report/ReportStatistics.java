package fr.fmaire.fnag.report;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.fmaire.fnag.model.Sale;

/**
 * Compute the statistics from a report.
 *
 * @author F.MAIRE(FMI)
 *
 */
public final class ReportStatistics {

    private static final Logger LOG = LoggerFactory.getLogger(ReportStatistics.class);

    private ReportStatistics() {
    }

    /**
     * @param report
     * @return the number of products in the report.
     */
    public static int getNumberOfProducts(final Report report) {
        return report.getProducts().size();
    }

    /**
     * @param report
     * @return the number of sales in the report.
     */
    public static int getNumberOfSales(final Report report) {
        return report.getSales().size();
    }

    /**
     * Find the top product sold and its sells.
     *
     * @param report the report to analyze
     * @return the top sale data
     */
    public static String findTopSale(final Report report) {
        // get the number of sold products <ProductRef, Sum of product sold>
        final Map<String, Integer> totalByProduct = report.getSales().stream()
                .collect(Collectors.groupingBy(Sale::getProductRef, Collectors.summingInt(Sale::getQuantity)));

        // find the top sale value
        final int top = Collections.max(totalByProduct.values()).intValue();
        LOG.debug("Max sales {}", top);
        LOG.debug(totalByProduct.toString());

        // list all the top sale and create statistic report
        final List<String> topSale = new ArrayList<>();
        totalByProduct.entrySet().stream().filter(entry -> top == entry.getValue()).forEach(entry -> {
            final String description = report.getProducts().get(entry.getKey()).getDescription();
            topSale.add(String.format("TOPSALE|%s|%s|%d", entry.getKey(), description, top));
        });

        return String.join("\n", topSale);
    }

    /**
     * Find the top seller and the amount of sells.
     *
     * @param report the report to analyze
     * @return the top seller data
     */
    public static String findTopSeller(final Report report) {
        // get the seller and the amount sold <seller identifier, amount sold>
        // seller identifier is the shopName+sellerName to avoid collision with seller having the same name in different
        // shops
        final Map<String, Long> sellerAmount = report.getSales().stream() //
                .collect(Collectors.groupingBy(sale -> String.format("%s|%s", sale.getShopName(), sale.getSellerName()), Collectors
                        .summingLong(sale -> report.getProducts().get(sale.getProductRef()).getPrice() * sale.getQuantity())));

        // find the amount for the top seller
        final long top = Collections.max(sellerAmount.values()).longValue();
        LOG.debug("Best seller amount {}", top);
        LOG.debug(sellerAmount.toString());

        // list all the top seller and create statistic report
        final List<String> topSeller = new ArrayList<>();
        sellerAmount.entrySet().stream().filter(entry -> top == entry.getValue()).forEach(
                entry -> topSeller.add(String.format(Locale.US, "TOPSELLER|%s|%.2f", entry.getKey(), entry.getValue() / 100.0)));

        return String.join("\n", topSeller);
    }
}
