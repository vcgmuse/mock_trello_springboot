package com.mock_trello.services.helpers;

import java.lang.reflect.Method;

public class EntityUpdater {

    public static <T> T updateFields(T existingEntity, T updatedEntity) {
        try {
            // Get all methods of the class
            Method[] methods = existingEntity.getClass().getMethods();

            for (Method method : methods) {
                // Check if the method is a "setter"
                if (method.getName().startsWith("set")) {
                    // Derive the corresponding "getter" method
                    String getterName = "get" + method.getName().substring(3);
                    Method getterMethod = existingEntity.getClass().getMethod(getterName);

                    // Get values from both entities
                    Object existingValue = getterMethod.invoke(existingEntity);
                    Object updatedValue = getterMethod.invoke(updatedEntity);

                    // Update only if the new value is not null and different
                    if (updatedValue != null && !updatedValue.equals(existingValue)) {
                        method.invoke(existingEntity, updatedValue);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error updating entity fields", e);
        }

        return existingEntity;
    }
}