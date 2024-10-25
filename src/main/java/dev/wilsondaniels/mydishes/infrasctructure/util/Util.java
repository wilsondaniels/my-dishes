package dev.wilsondaniels.mydishes.infrasctructure.util;

import java.util.UUID;

public interface Util {

    public static boolean isUUIDValid(final String uudiStr) {
        try {
            UUID.fromString(uudiStr);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}