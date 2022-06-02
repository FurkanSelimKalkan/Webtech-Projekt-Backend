package htw.berlin.webtechprojekt.demo.persistence;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name= "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;


    @OneToMany(mappedBy= "owner", fetch = FetchType.EAGER)
    private List<VotingEntity> votings = new ArrayList<>();

    public UserEntity(){}

    public UserEntity(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
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

    public List<VotingEntity> getVotings() {
        return votings;
    }

    public void setVotings(List<VotingEntity> votings) {
        this.votings = votings;
    }
}
