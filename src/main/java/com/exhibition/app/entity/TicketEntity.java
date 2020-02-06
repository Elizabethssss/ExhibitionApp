package com.exhibition.app.entity;

public class TicketEntity {
    private final Long id;
    private final Long userId;
    private final Long exhibitionId;
    private final boolean isBought;

    private TicketEntity(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.exhibitionId = builder.exhibitionId;
        this.isBought = builder.isBought;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getExhibitionId() {
        return exhibitionId;
    }

    public boolean isBought() {
        return isBought;
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private Long id;
        private Long userId;
        private Long exhibitionId;
        private boolean isBought;

        private Builder() {
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder withExhibitionId(Long exhibitionId) {
            this.exhibitionId = exhibitionId;
            return this;
        }

        public Builder withIsBought(boolean isBought) {
            this.isBought = isBought;
            return this;
        }

        public TicketEntity build() { return new TicketEntity(this); }
    }
}
