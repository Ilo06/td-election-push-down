public class ElectionResult {
    private String candidateName;
    private Long validVotesCount;

    public ElectionResult(String candidateName, Long validVotesCount) {
        this.candidateName = candidateName;
        this.validVotesCount = validVotesCount;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public Long getValidVotes() {
        return validVotesCount;
    }

    public void setValidVotes(Long validVotesCount) {
        this.validVotesCount = validVotesCount;
    }

    @Override
    public String toString() {
        return "ElectionResult{" +
                "candidateName='" + candidateName + '\'' +
                ", validVotesCount=" + validVotesCount +
                '}';
    }
}
