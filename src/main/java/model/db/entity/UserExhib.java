package model.db.entity;

public class UserExhib {
    private long id;
    private long userId;
    private long exhibitionId;
    private boolean isBought;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public boolean isBought() {
        return isBought;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }
}
