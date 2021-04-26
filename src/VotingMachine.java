import java.util.Scanner;

public class VotingMachine {
    private static ElectionData election;
    private Scanner keyboard = new Scanner(System.in);
    public VotingMachine(ElectionData election){
        this.election = election;
    }

    /**
     * Catches and trys exceptions for voting process
     */
    public void loginScreen() {
        System.out.println("Type your candidates in: ");
        String first = keyboard.next();
        String second = keyboard.next();
        String third = keyboard.next();
        try {
            election.processVote(first,second,third);
        }catch (UnknownCandidateException e){
            System.out.println("Would you like to add a candidate?");
            String answer = keyboard.next();
            if(answer.equals("y") || answer.equals("Y")) {
                System.out.println("What candidate would you like to add?");
                String candidate = keyboard.next();
                try {
                    this.addWriteIn(candidate);
                } catch (CandidateExistsException ex){
                    System.out.println("The candidate already exists.");
                }
                System.out.println("Candidate was added successfully");
                this.loginScreen();
            }
        }catch (DuplicateVotesException ez){
            System.out.println("You cannot vote for the same person twice.");
            this.loginScreen();
        }
        System.out.println("Thank you.");
    }

    /**
     * Adds candidate to ballot based on given string
     * @param candidate
     * @throws CandidateExistsException
     */
    public void addWriteIn(String candidate) throws CandidateExistsException {
        election.addCandidate(candidate);
    }
}
