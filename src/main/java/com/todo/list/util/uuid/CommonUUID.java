package com.todo.list.util.uuid;

import java.util.UUID;

public class CommonUUID {

    private final static String separator = ".";

    private String generator() {
        return UUID.randomUUID().toString();
    }

    private UUID convertToUUID(String uuid) {

        return UUID.fromString(uuid);
    }

    public String generatorCommentUUID() {
        return generator().replace("-", "");
    }

    public String generatorImageUUID() {
        return "IMAGE" + separator + generator().replace("-", "");
    }

    public UUID generateImageUUID() {
        String uuid = generatorImageUUID();
        return convertToUUID(uuid);
    }

    public String getSeparator() {
        return separator;
    }

}
