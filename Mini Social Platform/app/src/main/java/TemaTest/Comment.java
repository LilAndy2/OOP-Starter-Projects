package TemaTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment implements Likeable {
    //////////////////////////////////////// FIELDS ////////////////////////////////////////
    private final String text;
    private final User author;
    private final Post post;
    private static int idCount = 1;
    private final int currentId;
    private int likeCount;
    private final Date postDate;
    /* to store a comments's details, the fields I used were the ones listed above */
    //////////////////////////////////////// FIELDS ////////////////////////////////////////



    //////////////////////////////////////// CONSTRUCTOR, GETTERS AND SETTERS ////////////////////////////////////////
    public Comment(String text, User author, Post post) {
        /* this is the comments's constructor, which sets the post's fields appropiately; */
        this.text = text;
        this.author = author;
        this.post = post;
        this.likeCount = 0;
        this.currentId = idCount;
        this.increaseId();
        this.postDate = new Date();
    }
    public int getCurrentId() {
        return this.currentId;
    }
    public Post getPost() {
        return this.post;
    }
    public String getText() {
        return this.text;
    }
    public User getAuthor() {
        return this.author;
    }
    public int getLikeCount() {
        return this.likeCount;
    }
    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(this.postDate);
    }
    //////////////////////////////////////// CONSTRUCTOR, GETTERS AND SETTERS ////////////////////////////////////////



    //////////////////////////////////////// OTHERS ////////////////////////////////////////
    public void like() {
        likeCount++;
    }
    public void unlike() {
        likeCount--;
    }
    void increaseId() {
        idCount++;
    }
    static void resetIdCount() {
        idCount = 1;
    }
    //////////////////////////////////////// OTHERS ////////////////////////////////////////
}
