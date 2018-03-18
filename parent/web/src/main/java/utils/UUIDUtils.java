package utils;

import java.util.UUID;

public class UUIDUtils {
    private static UUIDUtils ourInstance = new UUIDUtils();

    public static UUIDUtils getInstance() {
        return ourInstance;
    }

    private UUIDUtils() {
    }
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");

    }
}
