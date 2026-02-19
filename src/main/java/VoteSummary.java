import java.util.Objects;

public class VoteSummary {
    private long validCount;
    private long blankCount;
    private long nullCount;

    public VoteSummary(long validCount, long blankCount, long nullCount) {
        this.validCount = validCount;
        this.blankCount = blankCount;
        this.nullCount = nullCount;
    }

    public long getValidCount() {
        return validCount;
    }

    public void setValidCount(long validCount) {
        this.validCount = validCount;
    }

    public long getBlankCount() {
        return blankCount;
    }

    public void setBlankCount(long blankCount) {
        this.blankCount = blankCount;
    }

    public long getNullCount() {
        return nullCount;
    }

    public void setNullCount(long nullCount) {
        this.nullCount = nullCount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        VoteSummary that = (VoteSummary) o;
        return validCount == that.validCount && blankCount == that.blankCount && nullCount == that.nullCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(validCount, blankCount, nullCount);
    }

    @Override
    public String toString() {
        return "VoteSummary{" +
                "validCount=" + validCount +
                ", blankCount=" + blankCount +
                ", nullCount=" + nullCount +
                '}';
    }
}
