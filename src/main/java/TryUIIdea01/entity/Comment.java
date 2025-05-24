package TryUIIdea01.entity;

import com.google.gson.annotations.SerializedName;

public class Comment {
    private String ID;
    private String Link;

    @SerializedName("CommentStatus")
    private int commentStatus;

    @SerializedName("CommentUserName")
    private String commentUserName;

    private String Title;
    private int Ratings;
    private String Content;

    @SerializedName("TimeCreated")
    private String timeCreated;

    private String Color;
    private String Config;

    // Constructor
    public Comment() {}

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public int getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(int commentStatus) {
        this.commentStatus = commentStatus;
    }

    public String getCommentUserName() {
        return commentUserName;
    }

    public void setCommentUserName(String commentUserName) {
        this.commentUserName = commentUserName;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getRatings() {
        return Ratings;
    }

    public void setRatings(int ratings) {
        Ratings = ratings;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getConfig() {
        return Config;
    }

    public void setConfig(String config) {
        Config = config;
    }

    @Override
    public String toString() {
        String displayContent;
        if (Content == null) {
            displayContent = "null";
        } else if (Content.length() > 20) {
            displayContent = Content.substring(0, 20) + "...";
        } else {
            displayContent = Content;
        }

        return "entity.Comment{" +
                "ID=" + ID +
                ", User='" + commentUserName + '\'' +
                ", Rating=" + Ratings +
                ", Title='" + Title + '\'' +
                ", Content='" + displayContent + '\'' +
                '}';
    }
}