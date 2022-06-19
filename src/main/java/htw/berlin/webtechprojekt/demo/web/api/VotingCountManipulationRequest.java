package htw.berlin.webtechprojekt.demo.web.api;


public class VotingCountManipulationRequest {

    private int votingsImage1;
    private int votingsImage2;
    private String votingUser;

    public VotingCountManipulationRequest(int votingsImage1, int votingsImage2, String votingUser) {
        this.votingsImage1 = votingsImage1;
        this.votingsImage2 = votingsImage2;
        this.votingUser = votingUser;
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

    public String getVotingUser() {
        return votingUser;
    }

    public void setVotingUser(String votingUser) {
        this.votingUser = votingUser;
    }
}
