public class DuplicateVotesException extends Exception {
    private String candidate;

    public DuplicateVotesException(String candidate){
        this.candidate = candidate;
    }

    /**
     * Gets candidate
     * @return candidate String
     */
    public String getCandidate(){
        return this.candidate;
    }

}
