/**
 * Copyright (c) 2017 - 2017 Nagravision SA. All rights reserved.
 * NAGRA and NAGRAVISION are registered trademarks of Kudelski SA, 1033 Cheseaux, Switzerland.
 * Confidential and privileged information.
 *
 * Classification level : strictly confidential
 */

package fr.fmaire.fnag.report;

/**
 * Exception thrown while report format or content is invalid.
 *
 * @author F.MAIRE(FMI)
 *
 */
public class ReportException extends Exception {

    /** */
    private static final long serialVersionUID = 3446836341176362070L;

    /**
     * @param message
     * @param cause
     */
    public ReportException(
            final String message,
            final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public ReportException(
            final String message) {
        super(message);
    }

}
