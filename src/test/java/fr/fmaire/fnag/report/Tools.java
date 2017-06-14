package fr.fmaire.fnag.report;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Tools for the tests
 *
 * @author F.MAIRE(FMI)
 *
 */
public final class Tools {

    private Tools() {

    }

    /**
     * Read file content as string from an URI.
     *
     * @param path of the file to read
     * @param encoding charset encoding
     * @return the content of the file as a string
     * @throws IOException
     */
    public static String readFile(final URI path,
            final Charset encoding) throws IOException {
        final byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

}
