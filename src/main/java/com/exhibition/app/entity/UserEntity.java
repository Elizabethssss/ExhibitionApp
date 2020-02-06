package com.exhibition.app.entity;

public class UserEntity {
    private final Long id;
    private final String username;
    private final String email;
    private final String password;
    private final RoleEntity roleEntity;

    private UserEntity(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.email = builder.email;
        this.password = builder.password;
        this.roleEntity = builder.roleEntity;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private long id;
        private String username;
        private String email;
        private String password;
        private RoleEntity roleEntity;

        private Builder() {}

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withRole(RoleEntity roleEntity) {
            this.roleEntity = roleEntity;
            return this;
        }

        public UserEntity build() {
            return new UserEntity(this);
        }
    }
}
