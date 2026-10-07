package me.jerryhan3.statCleaner.api;

import java.util.Map;

public interface AttributeList {
    Map<String, Double> getDefaultValues();
    boolean isAttributeAvailable(String attribute);
}
