import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
