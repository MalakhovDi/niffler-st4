package guru.qa.niffler.db.repository;

import guru.qa.niffler.db.DataSourceProvider;
import guru.qa.niffler.db.Database;
import guru.qa.niffler.db.model.CategoryEntity;
import guru.qa.niffler.db.model.SpendEntity;

import javax.sql.DataSource;
import java.sql.*;
import java.util.UUID;

public class SpendRepositoryJdbc implements SpendRepository {

    private final DataSource spendDs = DataSourceProvider.INSTANCE.dataSource(Database.SPEND);

    @Override
    public SpendEntity createSpend(SpendEntity spend) {
        try (Connection conn = spendDs.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement categoryPs = conn.prepareStatement(
                    "INSERT INTO \"category\" (category, username) VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
                 PreparedStatement spendPs = conn.prepareStatement(
                         "INSERT INTO \"spend\" " +
                                 "(username, spend_date, currency, amount, description, category_id) " +
                                 "VALUES (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)
            ) {

                categoryPs.setString(1, spend.getCategory().getCategory());
                categoryPs.setString(2, spend.getCategory().getUsername());
                categoryPs.executeUpdate();

                UUID categoryId;
                try (ResultSet keys = categoryPs.getGeneratedKeys()) {
                    if (keys.next()) {
                        categoryId = UUID.fromString(keys.getString("id"));
                    } else {
                        throw new IllegalStateException("Can`t find id");
                    }
                }

                CategoryEntity category = new CategoryEntity();
                category.setId(categoryId);
                category.setCategory(spend.getCategory().getCategory());
                category.setUsername(spend.getCategory().getUsername());

                spendPs.setString(1, spend.getUsername());
                spendPs.setDate(2, new Date(spend.getSpendDate().getTime()));
                spendPs.setString(3, String.valueOf(spend.getCurrency()));
                spendPs.setDouble(4, spend.getAmount());
                spendPs.setString(5, spend.getDescription());
                spendPs.setObject(6, categoryId);
                spendPs.executeUpdate();

                UUID spendId;

                try (ResultSet keys = spendPs.getGeneratedKeys()) {
                    if (keys.next()) {
                        spendId = UUID.fromString(keys.getString("id"));
                    } else {
                        throw new IllegalStateException("Can`t find id");
                    }
                }

                conn.commit();
                spend.setId(spendId);
            } catch (Exception e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return spend;
    }

    @Override
    public void deleteAllSpendsInDb() {
        try (Connection conn = spendDs.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement spendPs = conn.prepareStatement(
                    "DELETE FROM \"spend\"");
                 PreparedStatement categoryPs = conn.prepareStatement(
                         "DELETE FROM \"category\"")
            ) {

                spendPs.executeUpdate();
                categoryPs.executeUpdate();

                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
