public class CandidateExistsException extends Exception {
    private String candidate;

    public CandidateExistsException(String candidate){
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
