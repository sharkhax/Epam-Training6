package com.drobot.task6.type;

public enum CustomTag {
    ID(1), NAME(2), RELEASE_YEAR(3), PAGES(4), AUTHOR(5);

    private final int tagId;

    CustomTag(int tagId) {
        this.tagId = tagId;
    }

    public int getTagId() {
        return tagId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tag{");

        sb.append("tagId=").append(tagId);
        sb.append('}');
        return sb.toString();
    }
}
