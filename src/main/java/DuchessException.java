class DuchessException extends Exception {
    DuchessException(String errorMessage) {
        super("\tStop causing me trouble...\n\t" + errorMessage);
    }
}
