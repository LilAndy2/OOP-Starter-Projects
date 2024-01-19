package TemaTest;

import java.util.ArrayList;

public class User {
    //////////////////////////////////////// FIELDS ////////////////////////////////////////
    private final String username;
    private final String password;
    private int numberOfFollowers;
    private int totalLikes;
    private final ArrayList<Post> myPosts;
    private final ArrayList<String> usersIFollow;
    private final ArrayList<Post> likedPosts;
    private final ArrayList<Comment> likedComments;
    /* to store a user's details, the fields I used were the ones listed above */
    //////////////////////////////////////// FIELDS ////////////////////////////////////////



    //////////////////////////////////////// CONSTRUCTOR, GETTERS AND SETTERS ////////////////////////////////////////
    public User(String username, String password) {
        /* this is the user's constructor, which inititalises all the arrays for the current users and
           sets the user's fields appropiately; */
        this.username = username;
        this.password = password;
        this.numberOfFollowers = 0;
        this.totalLikes = 0;
        this.myPosts = new ArrayList<>();
        this.usersIFollow = new ArrayList<>();
        this.likedPosts = new ArrayList<>();
        this.likedComments = new ArrayList<>();
    }
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    public ArrayList<String> getUsersIFollow() {
        return this.usersIFollow;
    }
    public ArrayList<Post> getLikedPosts() {
        return this.likedPosts;
    }
    public ArrayList<Comment> getLikedComments() {
        return this.likedComments;
    }
    public int getNumberOfFollowers() {
        return this.numberOfFollowers;
    }
    public int getTotalLikes() {
        return this.totalLikes;
    }
    public ArrayList<Post> getMyPosts() {
        return this.myPosts;
    }
    //////////////////////////////////////// CONSTRUCTOR, GETTERS AND SETTERS ////////////////////////////////////////



    //////////////////////////////////////// OTHERS ////////////////////////////////////////
    public boolean checkIfIFollowThatUserAlready(String userToFollow) {
        /* this method checks if the user is already following the user given as a parameter; */
        if (userToFollow.equals(this.getUsername())) {
            return true;
        }
        for (String user : usersIFollow) {
            if (user.equals(userToFollow)) {
                return true;
            }
        }
        return false;
    }
    public void followThatUser(User userToFollow) {
        /* this method adds the user given as a parameter to the followed users array of the current
           user and increases the number of followers for the current user; */
        usersIFollow.add(userToFollow.getUsername());
        userToFollow.IGotFollowed();
    }
    public void unfollowThatUser(User userToUnfollow) {
        /* this method removes the user given as a parameter from the followed users array of the current
           user and decreases the number of followers for the current user; */
        for (String user : usersIFollow) {
            if (user.equals(userToUnfollow.getUsername())) {
                usersIFollow.remove(user);
                break;
            }
        }
        userToUnfollow.IGotUnfollowed();
    }
    public void LikeThatPost(Post post) {
        /* this method adds the post given as a parameter to the liked posts array of the current
           user and increases the number of likes for the post; */
        post.like();
        likedPosts.add(post);
    }
    public void UnlikeThatPost(Post post) {
        /* this method removes the post given as a parameter from the liked posts array of the current
           user and decreases the number of likes for the post; */
        post.unlike();
        likedPosts.remove(post);
    }
    public void LikeThatComment(Comment comment) {
        /* this method adds the comment given as a parameter to the liked comments array of the current
           user and increases the number of likes for the comment; */
        comment.like();
        likedComments.add(comment);
    }
    public void UnlikeThatComment(Comment comment) {
        /* this method removes the comment given as a parameter from the liked comments array of the current
           user and decreases the number of likes for the comment; */
        comment.unlike();
        likedComments.remove(comment);
    }
    public void IGotFollowed() {
        this.numberOfFollowers++;
    }
    public void IGotUnfollowed() {
        this.numberOfFollowers--;
    }
    public void AddPost(Post post) {
        myPosts.add(post);
    }
    public void DeletePost(Post post) {
        myPosts.remove(post);
    }
    public void AddToTotalLikes(int number) {
        this.totalLikes += number;
    }
    //////////////////////////////////////// OTHERS ////////////////////////////////////////
}