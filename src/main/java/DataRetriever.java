import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
