package htw.berlin.webtechprojekt.demo.web.api;

import java.util.ArrayList;

public class VotingVotesManipulationRequest {

    private ArrayList<String> votedUsers;

    public VotingVotesManipulationRequest(ArrayList<String> votedUsers) {
        this.votedUsers = votedUsers;
    }

    public ArrayList<String> getVotedUsers() {
        return votedUsers;
    }

    public void setVotedUsers(ArrayList<String> votedUsers) {
        this.votedUsers = votedUsers;
    }
}
