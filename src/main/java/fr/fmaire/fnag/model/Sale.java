package fr.fmaire.fnag.model;

/**
 * Sale.
 *
 * @author F.MAIRE(FMI)
 *
 */
public class Sale {

    /** the shop name */
    private final String shopName;
    /** the seller name */
    private final String sellerName;
    /** the product reference */
    private final String productRef;
    /** the sold quantity */
    private final int quantity;

    /**
     * @param shopName the shop name
     * @param sellerName the seller name
     * @param productRef the product reference
     * @param quantity the sold quantity
     */
    public Sale(
            final String shopName,
            final String sellerName,
            final String productRef,
            final int quantity) {
        super();
        this.shopName = shopName;
        this.sellerName = sellerName;
        this.productRef = productRef;
        this.quantity = quantity;
    }

    /**
     * @return the shopName
     */
    public String getShopName() {
        return this.shopName;
    }

    /**
     * @return the sellerName
     */
    public String getSellerName() {
        return this.sellerName;
    }

    /**
     * @return the productRef
     */
    public String getProductRef() {
        return this.productRef;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return this.quantity;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Sale [shopName=").append(this.shopName).append(", sellerName=").append(this.sellerName)
                .append(", productRef=").append(this.productRef).append(", quantity=").append(this.quantity).append("]");
        return builder.toString();
    }

}
