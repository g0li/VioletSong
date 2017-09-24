package forevtechnologies.alegriauiux;

/**
 * Created by thisi on 24-05-2017.
 */

public class PostDetails {
    String userName,userTeam,timestamp,post;
    public PostDetails() {  }
    public PostDetails(String userName, String userTeam, String timestamp, String post) {
        this.userName = userName;
        this.userTeam = userTeam;
        this.timestamp = timestamp;
        this.post = post;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTeam() {
        return userTeam;
    }

    public void setUserTeam(String userTeam) {
        this.userTeam = userTeam;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

}
