package model.jdbc.entity;

public class UserExhib {
    private long userId;
    private long exhibitionId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getExhibitionId() {
        return exhibitionId;
    }

    public void setExhibitionId(long exhibitionId) {
        this.exhibitionId = exhibitionId;
    }
}
