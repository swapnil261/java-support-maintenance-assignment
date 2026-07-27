package com.assignment;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task5 {

    private static final Logger logger = LoggerFactory.getLogger(Task5.class);

    public ValidationResult validate(Document doc) {
        try {

            // FIX: Handle expected validation failure without flooding logs.
            if (doc == null) {
                throw new IllegalArgumentException("Document is null");
            }

            String content = doc.extractContent();

            // FIX: Handle expected validation failure without flooding logs.
            if (content == null || content.isEmpty()) {
                throw new IllegalArgumentException("Empty content");
            }

            return runValidationRules(content);

        } catch (IllegalArgumentException e) {

            // FIX: Log expected validation failures at WARN level instead of
            // printing stack traces.
            logger.warn("Validation failed: {}", e.getMessage());

            // FIX: Return a safe value instead of throwing or printing stack trace.
            return null;

        } catch (Exception e) {

            // FIX: Log unexpected runtime errors with stack trace.
            logger.error("Unexpected error while validating document.", e);

            return null;
        }
    }

    public void validateBatch(List<Document> docs) {

        for (Document doc : docs) {

            try {

                ValidationResult r = validate(doc);

                // FIX: Check for null before calling isValid() to prevent
                // NullPointerException.
                if (r != null && r.isValid()) {
                    saveResult(r);
                }

            } catch (Exception e) {

                // FIX: Do not silently swallow exceptions.
                logger.error("Error while processing document in batch.", e);
            }
        }
    }

    // Existing methods - do not modify.
    private ValidationResult runValidationRules(String content) {
        return null;
    }

    private void saveResult(ValidationResult result) {
    }
}