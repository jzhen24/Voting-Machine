import com.sun.deploy.security.SelectableSecurityManager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

class ElectionData {
    private LinkedList<String> ballot = new LinkedList<String>();
    private HashMap<Integer, LinkedList<String>> votes = new HashMap<>();
    private Scanner keyboard = new Scanner(System.in);

    ElectionData() {
        this.ballot.add("Gompei");
        this.ballot.add("Husky");
    }

    /**
     * Adds a vote to the HashMap based on Strings given
     * @param first
     * @param second
     * @param third
     * @throws DuplicateVotesException
     * @throws UnknownCandidateException
     */
    public void processVote(String first, String second, String third) throws DuplicateVotesException, UnknownCandidateException {
        LinkedList<String> vote = new LinkedList<>();
        vote.add(first);
        vote.add(second);
        vote.add(third);
        if (!(ballot.contains(first))) {
            throw new UnknownCandidateException(first);
        } else if (!(ballot.contains(second))) {
            throw new UnknownCandidateException(second);
        } else if (!(ballot.contains(third))) {
            throw new UnknownCandidateException(third);
        }
        if (vote.get(0).equals(vote.get(1))) {
            throw new DuplicateVotesException(first);
        } else if (vote.get(1).equals(vote.get(2))) {
            throw new DuplicateVotesException(second);
        } else if (vote.get(0).equals(vote.get(2))) {
            throw new DuplicateVotesException(first);
        }
        this.votes.put(votes.size() + 1, vote);
    }

    /**
     * Adds a candidate to the ballot
     * @param candidate
     * @throws CandidateExistsException
     */
    public void addCandidate(String candidate) throws CandidateExistsException {
        if(ballot.contains(candidate)){
            throw new CandidateExistsException(candidate);
        }
        else{
            this.ballot.add(candidate);
        }
    }

    /**
     * Prints out all the candidates
     */
    public void printBallot() {
        System.out.println("The candidates are ");
        for (String s : ballot) {
            System.out.println(s);
        }
    }

    public void screen() {
        this.printBallot();
        System.out.println("Who do you want to vote for?");
        String candidate = keyboard.next();
        System.out.println("You voted for " + candidate);
    }

    /**
     * Counts the first place votes of all the candidates to determine winner
     * @return candidate with the most first place votes
     */
    public String findWinnerMostFirstVotes(){
        HashMap<String, Integer> num = new HashMap<>();
        for(int i = 0; i < ballot.size(); i++){
            num.put(ballot.get(i),0);
        }
        for(int j = 1; j < votes.size(); j++) {
            for (int k = 0; k < ballot.size(); k++) {
                if (votes.get(j).get(0).equals(ballot.get(k)))
                    num.replace(ballot.get(k), (num.get(ballot.get(k))) + 1);
            }
        }
        for(int l = 0; l < num.size(); l++){
            if(num.get(ballot.get(l))>(votes.size()/2)){
                return ballot.get(l);
            }
        }
        return "Runoff required";
    }

    /**
     * Counts points for each candidate based on 3 points for first place vote, 2 points for second place vote
     * and 1 point for third place vote and returns winner
     * @return candidate with most points based on given point system
     */
    public String findWinnerMostPoints() {
        HashMap<String, Integer> points = new HashMap<>();
        for (int i = 0; i < ballot.size(); i++) {
            points.put(ballot.get(i), 0);
        }
        for (int j = 1; j < votes.size(); j++) {
            for (int k = 0; k < ballot.size(); k++) {
                if (votes.get(j).get(0).equals(ballot.get(k))) {
                    points.replace(ballot.get(k), (points.get(ballot.get(k))) + 3);
                } else if (votes.get(j).get(1).equals(ballot.get(k))) {
                    points.replace(ballot.get(k), (points.get(ballot.get(k))) + 2);
                } else if (votes.get(j).get(2).equals(ballot.get(k))) {
                    points.replace(ballot.get(k), (points.get(ballot.get(k))) + 1);
                }
            }
        }

        int max = 0;
        for(int m=0; m<ballot.size(); m++){
            if(points.get(ballot.get(m))>max){
                max = points.get(ballot.get(m));
            }
        }
        for(int m=0; m<ballot.size(); m++){
            if(max  == points.get(ballot.get(m))){
                return ballot.get(m);
            }
        }

        return "No winner";
    }

    /**
     * Gets ballot list
     * @return ballot list
     */
    public LinkedList<String> getBallot() {
        return this.ballot;
    }

    /**
     * Gets vote hashmap
     * @return vote hashmap
     */
    public HashMap<Integer, LinkedList<String>> getVotes() {
        return this.votes;
    }

    /**
     * Sets ballot to given list
     * @param ballot
     */
    public void setBallot(LinkedList<String> ballot) {
        this.ballot = ballot;
    }

    /**
     * Sets votes to given hashmap
     * @param votes
     */
    public void setVotes(HashMap<Integer, LinkedList<String>> votes) {
        this.votes = votes;
    }
}