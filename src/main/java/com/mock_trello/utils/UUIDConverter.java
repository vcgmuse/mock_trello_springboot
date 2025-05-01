package com.mock_trello.utils;

import java.nio.ByteBuffer;
import java.util.UUID;

public class UUIDConverter {

    /**
     * Converts a UUID to its binary (byte array) representation.
     *
     * @param uuid the UUID to convert
     * @return a 16-byte array representing the UUID
     */
    public static byte[] uuidToBytes(UUID uuid) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[16]);
        byteBuffer.putLong(uuid.getMostSignificantBits()); // First 64 bits
        byteBuffer.putLong(uuid.getLeastSignificantBits()); // Last 64 bits
        return byteBuffer.array();
    }

    /**
     * Converts a binary (byte array) representation back to a UUID.
     *
     * @param bytes the 16-byte array to convert
     * @return the UUID constructed from the byte array
     */
    public static UUID bytesToUUID(byte[] bytes) {
        if (bytes.length != 16) {
            throw new IllegalArgumentException("Invalid byte array length for UUID conversion. Expected 16 bytes.");
        }
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        long mostSigBits = byteBuffer.getLong(); // First 64 bits
        long leastSigBits = byteBuffer.getLong(); // Last 64 bits
        return new UUID(mostSigBits, leastSigBits);
    }

    /**
     * Generates a new random UUID and converts it to its binary (byte array) representation.
     *
     * @return a 16-byte array representing a newly generated UUID
     */
    public static byte[] createByteId() {
        UUID newUUID = UUID.randomUUID();
        return uuidToBytes(newUUID);
    }

    /**
     * Converts a String representation of a UUID to its binary (byte array) representation.
     *
     * @param uuidString the UUID string to convert
     * @return a 16-byte array representing the UUID
     */
    public static byte[] fromString(String uuidString) {
        UUID uuid = UUID.fromString(uuidString);
        return uuidToBytes(uuid);
    }

    /**
     * Converts a binary (byte array) representation to a String representation of a UUID.
     *
     * @param bytes the 16-byte array to convert
     * @return the String representation of the UUID
     */
    public static String toString(byte[] bytes) {
        UUID uuid = bytesToUUID(bytes);
        return uuid.toString();
    }

    public static void main(String[] args) {
        // Example: Generate a random UUID and test methods
        UUID originalUUID = UUID.randomUUID();
        System.out.println("Original UUID: " + originalUUID);

        // Convert UUID to binary
        byte[] binaryUUID = uuidToBytes(originalUUID);
        System.out.println("Binary UUID (byte array): " + java.util.Arrays.toString(binaryUUID));

        // Convert binary back to UUID
        UUID convertedUUID = bytesToUUID(binaryUUID);
        System.out.println("Converted UUID: " + convertedUUID);

        // Convert UUID string to binary
        String uuidString = originalUUID.toString();
        byte[] binaryFromString = fromString(uuidString);
        System.out.println("Binary from String: " + java.util.Arrays.toString(binaryFromString));

        // Convert binary back to UUID string
        String stringFromBinary = toString(binaryUUID);
        System.out.println("String from Binary: " + stringFromBinary);

        // Verify the conversions
        System.out.println("Conversion is successful: " + originalUUID.equals(convertedUUID));
        System.out.println("String conversion is successful: " + uuidString.equals(stringFromBinary));
    }
}