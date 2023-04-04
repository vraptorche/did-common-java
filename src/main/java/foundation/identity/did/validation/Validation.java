package foundation.identity.did.validation;

import foundation.identity.did.DIDDocument;

import java.net.URI;
import java.net.URISyntaxException;

public class Validation {

    private static void validateTrue(boolean valid) {

        if (! valid) throw new RuntimeException();
    }

    private static void validateUrl(URI uri) {

        try {

            if (! uri.isAbsolute()) throw new URISyntaxException("Not absolute.", uri.toString());
        } catch (URISyntaxException ex) {

            throw new IllegalStateException(ex.getMessage(), ex);
        }
    }

    private static void validateRun(Runnable runnable, String message) throws IllegalStateException {

        try {

            runnable.run();
        } catch (Exception ex) {

            if (ex.getMessage() != null && ! ex.getMessage().isEmpty()) message = message + " (" + ex.getMessage().trim() + ")";
            throw new IllegalStateException(message, ex);
        }
    }

    public static void validate(DIDDocument didDocument) throws IllegalStateException {

        validateRun(() -> validateTrue(didDocument.getJsonObject() != null), "Bad or missing JSON object.");
        validateRun(() -> validateTrue(!didDocument.getContexts().isEmpty()), "Bad or missing '@context'.");
        validateRun(() -> validateUrl(didDocument.getContexts().get(0)), "@context must be a valid URI: " + didDocument.getContexts().get(0));
        validateRun(() -> validateTrue(DIDDocument.DEFAULT_JSONLD_CONTEXTS[0].equals(didDocument.getContexts().get(0))), "First value of @context must be " + DIDDocument.DEFAULT_JSONLD_CONTEXTS[0] + ": " + didDocument.getContexts().get(0));
        validateRun(() -> { if (didDocument.getId() != null) validateUrl(didDocument.getId()); }, "'id' must be a valid URI.");
    }
}
