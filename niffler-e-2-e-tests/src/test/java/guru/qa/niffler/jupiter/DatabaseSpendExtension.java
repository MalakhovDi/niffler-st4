package guru.qa.niffler.jupiter;

import guru.qa.niffler.db.model.CategoryEntity;
import guru.qa.niffler.db.model.SpendEntity;
import guru.qa.niffler.db.repository.SpendRepository;
import guru.qa.niffler.db.repository.SpendRepositorySJdbc;
import guru.qa.niffler.model.SpendJson;

public class DatabaseSpendExtension extends SpendWithCategoryExtension {

    private SpendRepository spendRepository = new SpendRepositorySJdbc();

    @Override
    protected SpendJson create(SpendJson spend) {
        SpendEntity spendEntity = mapSpendJsonToEntity(spend);
        spendRepository.createSpend(spendEntity);
        return spend;
    }

    private SpendEntity mapSpendJsonToEntity(SpendJson spendJson) {
        SpendEntity spendEntity = new SpendEntity();
        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setUsername(spendJson.username());
        categoryEntity.setCategory(spendJson.category());

        spendEntity.setSpendDate(spendJson.spendDate());
        spendEntity.setAmount(spendJson.amount());
        spendEntity.setCurrency(spendJson.currency());
        spendEntity.setDescription(spendJson.description());
        spendEntity.setUsername(spendJson.username());
        spendEntity.setCategory(categoryEntity);

        return spendEntity;
    }
}
