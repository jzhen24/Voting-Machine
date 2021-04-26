public class UnknownCandidateException extends Exception {
    private String candidate;

    public UnknownCandidateException(String candidate){
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
