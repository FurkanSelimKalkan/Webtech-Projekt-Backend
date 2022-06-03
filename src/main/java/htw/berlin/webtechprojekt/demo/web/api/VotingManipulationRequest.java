package htw.berlin.webtechprojekt.demo.web.api;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class VotingManipulationRequest {

    @NotBlank(message = "Title Field cannot be empty")
    private String title;
    @NotBlank(message = "Image Field cannot be empty")
    private String image1;
    @NotBlank(message = "Image Field cannot be empty")
    private String image2;

    private int votingsImage1;
    private int votingsImage2;
    private Long ownerId;

    public VotingManipulationRequest(String title, String image1, String image2, int votingsImage1, int votingsImage2, Long ownerId) {
        this.title = title;
        this.image1 = image1;
        this.image2 = image2;
        this.votingsImage1 = votingsImage1;
        this.votingsImage2 = votingsImage2;
        this.ownerId = ownerId;
    }

    public VotingManipulationRequest(){}

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

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}

