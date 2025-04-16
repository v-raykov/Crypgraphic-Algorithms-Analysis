package com.dreamteam.algorithm.analysis.config.jackson;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Base64;

public class JsonUtils {
    public static int getIntIfProvided(JsonNode node, String fieldName) {
        if (node.has("parameters")) {
            node = node.get("parameters");
            if (node.has(fieldName)) {
                return node.get(fieldName).asInt();
            }
        }
        return 0;
    }

    public static byte[] getBytesIfProvided(JsonNode node, String fieldName) {
        if (node.has("parameters")){
            node = node.get("parameters");
            if (node.has(fieldName)) {
                return Base64.getDecoder().decode(node.get(fieldName).asText());
            }
        }
        return null;
    }
}
