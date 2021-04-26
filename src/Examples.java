import org.junit.Test;

import static org.junit.Assert.*;

public class Examples {
    // method to set up a ballot and cast votes

    ElectionData Setup1 () {

        ElectionData ED = new ElectionData();

        // put candidates on the ballot
        try {

            ED.addCandidate("gompei");
            ED.addCandidate("husky");
            ED.addCandidate("ziggy");

        } catch (Exception e) {}

        // cast votes

        try {

            ED.processVote("gompei", "husky", "ziggy");
            ED.processVote("gompei", "ziggy", "husky");
            ED.processVote("husky", "gompei", "ziggy");

        } catch (Exception e) {}

        return(ED);

    }
    ElectionData Setup2 () {

        ElectionData ED1 = new ElectionData();

        // put candidates on the ballot
        try {

            ED1.addCandidate("gompei");
            ED1.addCandidate("husky");
            ED1.addCandidate("ziggy");


        } catch (Exception e) {
        }

        // cast votes

        try {

            ED1.processVote("gompei", "husky", "ziggy");
            ED1.processVote("ziggy", "gompei", "husky");
            ED1.processVote("husky", "gompei", "ziggy");

        } catch (Exception e) {
        }

        return (ED1);

    }
    ElectionData Setup3 () {

        ElectionData ED2 = new ElectionData();

        // put candidates on the ballot
        try {

            ED2.addCandidate("gompei");
            ED2.addCandidate("husky");
            ED2.addCandidate("ziggy");
            ED2.addCandidate("goat");


        } catch (Exception e) {
        }

        // cast votes

        try {

            ED2.processVote("gompei", "husky", "ziggy");
            ED2.processVote("ziggy", "gompei", "husky");
            ED2.processVote("husky", "gompei", "ziggy");
            ED2.processVote("gompei","husky", "ziggy");
            ED2.processVote("husky", "goat","gompei");

        } catch (Exception e) {
        }

        return (ED2);

    }
    ElectionData Setup4 () {

        ElectionData ED3 = new ElectionData();

        // put candidates on the ballot
        try {

            ED3.addCandidate("gompei");
            ED3.addCandidate("husky");
            ED3.addCandidate("ziggy");
            ED3.addCandidate("goat");


        } catch (Exception e) {
        }

        // cast votes

        try {

            ED3.processVote("husky", "goat", "ziggy");
            ED3.processVote("ziggy", "gompei", "husky");
            ED3.processVote("husky", "gompei", "ziggy");
            ED3.processVote("gompei","husky", "ziggy");
            ED3.processVote("husky", "goat","gompei");

        } catch (Exception e) {
        }

        return (ED3);

    }
    String Setup5 () {

        ElectionData ED4 = new ElectionData();

        // put candidates on the ballot
        try {

            ED4.addCandidate("gompei");
            ED4.addCandidate("husky");
            ED4.addCandidate("ziggy");
            ED4.addCandidate("goat");


        } catch (Exception e) {
        }

        // cast votes

        try {

            ED4.processVote("husky", "unknown", "ziggy");
            ED4.processVote("ziggy", "gompei", "husky");
            ED4.processVote("husky", "gompei", "ziggy");
            ED4.processVote("gompei","husky", "ziggy");
            ED4.processVote("husky", "goat","gompei");

        } catch (UnknownCandidateException e) {
            return "unknown candidate";
        } catch(DuplicateVotesException e){
            return "duplicate candidate";
        }
        return "pass";
    }
    String Setup6 () {

        ElectionData ED5 = new ElectionData();

        // put candidates on the ballot
        try {

            ED5.addCandidate("gompei");
            ED5.addCandidate("husky");
            ED5.addCandidate("ziggy");
            ED5.addCandidate("goat");


        } catch (Exception e) {
        }

        // cast votes

        try {

            ED5.processVote("husky", "husky", "ziggy");
            ED5.processVote("ziggy", "gompei", "husky");
            ED5.processVote("husky", "gompei", "ziggy");
            ED5.processVote("gompei","husky", "ziggy");
            ED5.processVote("husky", "goat","gompei");

        } catch (UnknownCandidateException e) {
            return "unknown candidate";
        } catch(DuplicateVotesException e){
            return "duplicate candidate";
        }
        return "pass";
    }
    String Setup7 () {

        ElectionData ED6 = new ElectionData();

        // put candidates on the ballot
        try {

            ED6.addCandidate("gompei");
            ED6.addCandidate("husky");
            ED6.addCandidate("ziggy");
            ED6.addCandidate("goat");
            ED6.addCandidate("gompei");


        } catch (CandidateExistsException e) {
            return "candidate already exists";
        }

        // cast votes

        try {

            ED6.processVote("husky", "husky", "ziggy");
            ED6.processVote("ziggy", "gompei", "husky");
            ED6.processVote("husky", "gompei", "ziggy");
            ED6.processVote("gompei", "husky", "ziggy");
            ED6.processVote("husky", "goat", "gompei");

        } catch (Exception e) {

        }
        return "pass";
    }
    // now run a test on a specific election
    @Test
    public void testMostFirstWinner () {
        assertEquals ("gompei", Setup1().findWinnerMostFirstVotes());
    }
    @Test
    public void testFindWinnerMostFirstVotes_Clear(){
        assertEquals("gompei",Setup1().findWinnerMostFirstVotes());
    }
    @Test
    public void testFindWinnerMostFirstVotes_Tie(){
        assertEquals("Runoff required", Setup2().findWinnerMostFirstVotes());
    }
    @Test
    public void testFindWinnerMostPoints_Tie(){
        assertEquals("gompei",Setup3().findWinnerMostPoints());

    }
    @Test
    public void testFindWinnerMostPoints_Clear(){
        assertEquals("husky", Setup4().findWinnerMostPoints());
    }

    @Test
    public void testProcessVoteForUnknownCandidate_UnknownException(){
        assertEquals("unknown candidate", Setup5());
    }
    @Test
    public void testProcessVoteForDuplicateCandidate_DuplicateException(){
        assertEquals("duplicate candidate", Setup6());
    }
    @Test
    public void testAddCandidateForCandidateExists_CandidateExists(){
        assertEquals("candidate already exists", Setup7());
    }



}
