package fr.fmaire.fnag.model;

/**
 * Product data.
 *
 * @author F.MAIRE(FMI)
 *
 */
public class Product {

    /** the product reference */
    private final String ref;
    /**
     * the price of the product. Integer cents, to avoid computation with double and problems with equality and roundup
     */
    private final int price;
    /** the description of the product */
    private final String description;

    /**
     * @param ref the product reference
     * @param price the price in cents
     * @param description description of the product
     */
    public Product(
            final String ref,
            final int price,
            final String description) {
        super();
        this.ref = ref;
        this.price = price;
        this.description = description;
    }

    /**
     * @return the ref
     */
    public String getRef() {
        return this.ref;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Product [ref=").append(this.ref).append(", price=").append(this.price).append(", description=")
                .append(this.description).append("]");
        return builder.toString();
    }

}
