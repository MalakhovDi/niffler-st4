package guru.qa.niffler.db.repository;

import guru.qa.niffler.db.DataSourceProvider;
import guru.qa.niffler.db.Database;
import guru.qa.niffler.db.model.CategoryEntity;
import guru.qa.niffler.db.model.SpendEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.UUID;

public class SpendRepositorySJdbc implements SpendRepository {

    private final TransactionTemplate spendTxt;
    private final JdbcTemplate spendTemplate;

    public SpendRepositorySJdbc() {
        JdbcTransactionManager spendTm = new JdbcTransactionManager(
                DataSourceProvider.INSTANCE.dataSource(Database.SPEND)
        );

        this.spendTxt = new TransactionTemplate(spendTm);
        this.spendTemplate = new JdbcTemplate(spendTm.getDataSource());
    }

    @Override
    public SpendEntity createSpend(SpendEntity spend) {
        KeyHolder kh = new GeneratedKeyHolder();
        return spendTxt.execute(status -> {
            spendTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement("INSERT INTO \"category\" (category, username) VALUES (?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, spend.getCategory().getCategory());
                ps.setString(2, spend.getCategory().getUsername());
                return ps;
            }, kh);
            UUID categoryId = (UUID) kh.getKeys().get("id");
            CategoryEntity category = new CategoryEntity();
            category.setId(categoryId);
            category.setCategory(spend.getCategory().getCategory());
            category.setUsername(spend.getCategory().getUsername());

            spendTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement("INSERT INTO \"spend\" " +
                        "(username, spend_date, currency, amount, description, category_id) " +
                        "VALUES (?, ?, ?, ?, ?, ?)");
                ps.setString(1, spend.getUsername());
                ps.setDate(2, new Date(spend.getSpendDate().getTime()));
                ps.setString(3, String.valueOf(spend.getCurrency()));
                ps.setDouble(4, spend.getAmount());
                ps.setString(5, spend.getDescription());
                ps.setObject(6, categoryId);
                return ps;
            }, kh);
            return spend;
        });
    }

    @Override
    public void deleteAllSpendsInDb() {
        spendTxt.execute(status -> {
            spendTemplate.update("DELETE FROM \"spend\"");
            spendTemplate.update("DELETE FROM \"category\"");
            return null;
        });
    }
}
