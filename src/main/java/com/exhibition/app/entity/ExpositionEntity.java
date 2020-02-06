package com.exhibition.app.entity;

public class ExpositionEntity {
    private final long id;
    private final String name;
    private final String about;
    private final String image;
    private final Long exhibitionId;

    public ExpositionEntity(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.about = builder.about;
        this.image = builder.image;
        this.exhibitionId = builder.exhibitionId;
    }

    public long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getAbout() {
        return about;
    }

    public Long getExhibitionId() {
        return exhibitionId;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private long id;
        private String name;
        private String about;
        private String image;
        private Long exhibitionId;

        private Builder() {}

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withAbout(String about) {
            this.about = about;
            return this;
        }

        public Builder withImage(String image) {
            this.image = image;
            return this;
        }

        public Builder withExhibitionId(Long exhibitionId) {
            this.exhibitionId = exhibitionId;
            return this;
        }

        public ExpositionEntity build() { return new ExpositionEntity(this); }
    }
}
