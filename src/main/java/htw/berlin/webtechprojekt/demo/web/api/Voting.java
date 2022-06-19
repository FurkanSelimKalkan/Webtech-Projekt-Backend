package htw.berlin.webtechprojekt.demo.web.api;

import java.util.ArrayList;

public class Voting {

    private Long id;
    private String title;
    private String image1;
    private String image2;
    private int votingsImage1;
    private int votingsImage2;
    private String user;
    private String userName;
    private ArrayList<String> votedUsers = new ArrayList<>();


    public Voting(Long id, String title, String image1, String image2, int votingsImage1, int votingsImage2, String user, String userName, ArrayList<String> votedUsers) {
        this.id = id;
        this.title = title;
        this.image1 = image1;
        this.image2 = image2;
        this.votingsImage1 = votingsImage1;
        this.votingsImage2 = votingsImage2;
        this.user = user;
        this.userName = userName;
        this.votedUsers = votedUsers;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public int getVotingsImage1() {
        return votingsImage1;
    }

    public void setVotingsImage1(int votingsImage1) {
        this.votingsImage1 = votingsImage1;
    }

    public int getVotingsImage2() {
        return votingsImage2;
    }

    public void setVotingsImage2(int votingsImage2) {
        this.votingsImage2 = votingsImage2;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<String> getVotedUsers() {
        return votedUsers;
    }

    public void setVotedUsers(ArrayList<String> votedUsers) {
        this.votedUsers = votedUsers;
    }
}

