import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataRetriever {
    public long countAllVotes() {
        String sql = """
                SELECT COUNT(*) AS total_votes
                FROM vote;
                """;

        try (Connection connection = new DBConnection().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getLong(1);
            } else {
                throw new RuntimeException("Failed to retrieve total votes count.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public List<VoteTypeCount> countVotesByType() {
        String sql = """
                SELECT vote_type, COUNT(*) AS count
                FROM vote
                GROUP BY vote_type
                ORDER BY vote_type;
                
                """;

        try (Connection connection = new DBConnection().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            List<VoteTypeCount> result = new java.util.ArrayList<>();

            while (rs.next()) {
                VoteType voteType = VoteType.valueOf(rs.getString("vote_type"));
                int count = rs.getInt("count");
                result.add(new VoteTypeCount(voteType, count));
            }

            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CandidateVoteCount> countValidVotesByCandidate() {

        DBConnection dbConnection = new DBConnection();

        String sql = """
        SELECT c.name, COUNT(v.id)
        FROM candidate c
        LEFT JOIN vote v
            ON v.candidate_id = c.id
            AND v.vote_type = 'VALID'
        GROUP BY c.name
        ORDER BY c.name
        """;

        List<CandidateVoteCount> results = new ArrayList<>();

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                results.add(
                        new CandidateVoteCount(
                                rs.getString(1),
                                rs.getLong(2)
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }

}
