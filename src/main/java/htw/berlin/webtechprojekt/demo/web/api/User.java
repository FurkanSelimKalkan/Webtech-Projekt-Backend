package htw.berlin.webtechprojekt.demo.web.api;

import htw.berlin.webtechprojekt.demo.persistence.VotingEntity;

import java.util.ArrayList;
import java.util.List;

public class User {

    private long id;
    private String username;
    private String email;
    private String password;
    private List<Long> votingIds;

    public User(long id, String username, String email, String password, List<Long> votingIds) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.votingIds = votingIds;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Long> getVotingIds() {
        return votingIds;
    }

    public void setVotingIds(List<Long> votingIds) {
        this.votingIds = votingIds;
    }
}
