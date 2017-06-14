package fr.fmaire.fnag.report;

import fr.fmaire.fnag.model.Sale;

/**
 * Builder for Sale objects according to the report file format.
 *
 * @author F.MAIRE(FMI)
 *
 */
public class SaleBuilder {

    /** separator for the different fields in the string to parse */
    private static final String SEP = "\\|";

    /** part of the product data in the report, in appearance order */
    private enum Parts {
        SHOP,
        SELLER,
        PRODUCT_REF,
        QUANTITY,
        //
        ;
    }

    private SaleBuilder() {
    }

    /**
     * Build a Sale object according to the report file format.
     *
     * @param saleStr the string for a sale in the report file
     * @return the parsed sale
     * @throws ReportException
     */
    public static Sale buildFromString(final String saleStr) throws ReportException {
        final String[] parts = saleStr.split(SEP);
        if (parts.length != Parts.values().length) {
            throw new ReportException(String.format("Invalid number of parts %s for product string: %s", parts.length, saleStr));
        }
        return new Sale(parts[Parts.SHOP.ordinal()], parts[Parts.SELLER.ordinal()], parts[Parts.PRODUCT_REF.ordinal()],
                Integer.parseInt(parts[Parts.QUANTITY.ordinal()]));
    }
}
