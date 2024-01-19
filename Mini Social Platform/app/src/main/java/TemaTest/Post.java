package TemaTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Post implements Likeable {
    //////////////////////////////////////// FIELDS ////////////////////////////////////////
    private final String text;
    private final User author;
    private static int idCount = 1;
    private final int currentId;
    private int likeCount;
    private final Date postDate;
    private final ArrayList<Comment> comments;
    /* to store a post's details, the fields I used were the ones listed above */
    //////////////////////////////////////// FIELDS ////////////////////////////////////////



    //////////////////////////////////////// CONSTRUCTOR, GETTERS AND SETTERS ////////////////////////////////////////
    public Post(String text, User author) {
        /* this is the post's constructor, which inititalises the array for the current post and
           sets the post's fields appropiately; */
        this.text = text;
        this.author = author;
        this.currentId = idCount;
        this.increaseId();
        this.likeCount = 0;
        this.comments = new ArrayList<>();
        this.postDate = new Date();
    }
    public String getText() {
        return this.text;
    }
    public User getAuthor() {
        return this.author;
    }
    public int getCurrentId() {
        return this.currentId;
    }
    public ArrayList<Comment> getComments() {
        return this.comments;
    }
    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(this.postDate);
    }
    public int getLikeCount() {
        return this.likeCount;
    }
    public int getNumberOfComments() {
        return this.getComments().size();
    }
    //////////////////////////////////////// CONSTRUCTOR, GETTERS AND SETTERS ////////////////////////////////////////



    //////////////////////////////////////// OTHERS ////////////////////////////////////////
    public void like() {
        likeCount++;
    }
    public void unlike() {
        likeCount--;
    }
    public void increaseId() {
        idCount++;
    }
    public static void resetIdCount() {
        idCount = 1;
    }
    public void addComment(Comment comment) {
        comments.add(comment);
    }
    public void removeComment(Comment comment) {
        comments.remove(comment);
    }
    //////////////////////////////////////// OTHERS ////////////////////////////////////////
}
