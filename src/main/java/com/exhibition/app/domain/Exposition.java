package com.exhibition.app.domain;

public class Exposition {
    private final Long id;
    private final String name;
    private final String about;
    private final String image;
    private final Long exhibitionId;

    public Exposition(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.about = builder.about;
        this.image = builder.image;
        this.exhibitionId = builder.exhibitionId;
    }

    public Long getId() {
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

        public Exposition build() { return new Exposition(this); }
    }
}
