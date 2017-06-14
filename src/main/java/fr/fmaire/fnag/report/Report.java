package fr.fmaire.fnag.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.fmaire.fnag.model.Product;
import fr.fmaire.fnag.model.Sale;

/**
 * Report from all the shops of FNAG company.
 *
 * @author F.MAIRE(FMI)
 *
 */
public class Report {

    /** maximum products allowed in the report */
    public static final int MAX_PRODUCTS = 100;
    /** maximum sales allowed in the report */
    public static final int MAX_SALES = 1000;

    /** the products list */
    private final Map<String, Product> products = new HashMap<>();
    /** the sales list */
    private final List<Sale> sales = new ArrayList<>(MAX_SALES);

    /**
     * @return the products
     */
    Map<String, Product> getProducts() {
        return this.products;
    }

    /**
     * @return the sales
     */
    List<Sale> getSales() {
        return this.sales;
    }

    /**
     * Add a Product to the report.
     *
     * @param p the product to add
     * @throws ReportException
     */
    void addProduct(final Product product) throws ReportException {
        final Product previous = this.products.put(product.getRef(), product);
        // we do not allow to receive the same product definition more than once
        if (previous != null) {
            throw new ReportException("Duplicate product data for reference " + product.getRef());
        }
    }

    /**
     * Add a sale to the report
     *
     * @param sale the sale to add
     * @return
     */
    boolean addSale(final Sale sale) throws ReportException {
        if (this.products.get(sale.getProductRef()) == null) {
            throw new ReportException("Cannot sell a product with unknown reference " + sale.getProductRef());
        }
        return this.sales.add(sale);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Report [productList=").append(this.products).append(", saleList=").append(this.sales).append("]");
        return builder.toString();
    }

}
