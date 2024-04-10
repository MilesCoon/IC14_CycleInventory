public class InvalidFrameSizeException extends Exception {
    InvalidFrameSizeException() {
        super("Invalid frame size: Only 15\" (small), 17\" (medium) or 19\" (large) frame sizes are recognized.");
    }
}
