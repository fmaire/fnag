package fr.fmaire.fnag.report;

import fr.fmaire.fnag.model.Product;

/**
 * Builder for a Product model object according to the report format.
 *
 * @author F.MAIRE(FMI)
 *
 */
public class ProductBuilder {

    /** separator for the different fields in the string to parse */
    private static final String SEP = "\\|";
    /** decimal separator for the price in the report file */
    private static final String DECIMAL_POINT = ".";

    /** part of the product data in the report, in appearance order */
    private enum Parts {
        REFERENCE,
        PRICE,
        DESCRIPTION,
        //
        ;
    }

    private ProductBuilder() {
    }

    /**
     * Build a Product object according to the report file format.
     *
     * @param productStr the string for a product in the report file
     * @return the parsed product
     * @throws ReportException
     */
    public static Product buildFromString(final String productStr) throws ReportException {
        final String[] parts = productStr.split(SEP);
        if (parts.length != Parts.values().length) {
            throw new ReportException(String.format("Invalid number of parts %s for product string: %s", parts.length, productStr));
        }
        final String priceStr = parts[Parts.PRICE.ordinal()];
        return new Product(parts[Parts.REFERENCE.ordinal()], priceFromString(priceStr), parts[Parts.DESCRIPTION.ordinal()]);
    }

    /**
     * Check that price have not more than 2 decimal and convert it to cents
     *
     * @param priceStr
     * @return
     * @throws ReportException
     */
    private static int priceFromString(final String priceStr) throws ReportException {
        try {
            final int pos = priceStr.indexOf(DECIMAL_POINT);
            if (pos == -1) {
                return Integer.parseInt(priceStr) * 100;
            } else if ((priceStr.length() - pos) <= 3) {
                return (int) (Double.parseDouble(priceStr) * 100);
            }
        } catch (final Exception e) {
            throw new ReportException("Invalid price: " + priceStr, e);
        }
        throw new ReportException("Invalid 2 decimal price: " + priceStr);
    }
}
