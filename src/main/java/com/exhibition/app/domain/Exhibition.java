package com.exhibition.app.domain;

import java.sql.Date;

public class Exhibition {
    private final Long id;
    private final String name;
    private final Date dateFrom;
    private final Date dateTo;
    private final String theme;
    private final String about;
    private final String longAbout;
    private final double price;
    private final String image;

    private Exhibition(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.dateFrom = builder.dateFrom;
        this.dateTo = builder.dateTo;
        this.theme = builder.theme;
        this.about = builder.about;
        this.longAbout = builder.longAbout;
        this.price = builder.price;
        this.image = builder.image;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public String getTheme() {
        return theme;
    }

    public String getAbout() {
        return about;
    }

    public String getLongAbout() {
        return longAbout;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private long id;
        private String name;
        private Date dateFrom;
        private Date dateTo;
        private String theme;
        private String about;
        private String longAbout;
        private double price;
        private String image;

        private Builder() {}

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }
        public Builder withDateFrom(Date dateFrom) {
            this.dateFrom = dateFrom;
            return this;
        }

        public Builder withDateTo(Date dateTo) {
            this.dateTo = dateTo;
            return this;
        }

        public Builder withTheme(String theme) {
            this.theme = theme;
            return this;
        }

        public Builder withAbout(String about) {
            this.about = about;
            return this;
        }

        public Builder withLongAbout(String longAbout) {
            this.longAbout = longAbout;
            return this;
        }

        public Builder withPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder withImage(String image) {
            this.image = image;
            return this;
        }

        public Exhibition build() {return new Exhibition(this); }
    }
}
