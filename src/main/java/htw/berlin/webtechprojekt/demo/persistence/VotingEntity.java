package htw.berlin.webtechprojekt.demo.persistence;

import javax.persistence.*;

@Entity(name = "votings")
public class VotingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)                  //DB erstellt einen eigenen Numerischen Wert für den Primary Key
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "image_1", nullable = false)
    private String image1;

    @Column(name = "image_2", nullable = false)
    private String image2;

    @Column(name = "votings_image_1")
    private int votingsImage1;

    @Column(name = "votings_image_2")
    private int votingsImage2;

    protected VotingEntity(){}

    public VotingEntity(String title, String image1, String image2, int votingsImage1, int votingsImage2) {
        this.title = title;
        this.image1 = image1;
        this.image2 = image2;
        this.votingsImage1 = votingsImage1;
        this.votingsImage2 = votingsImage2;
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
}
