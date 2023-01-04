package pl.lodz.uni.edu.gin.repositories;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pl.lodz.uni.edu.gin.Stubs;

public abstract class StubsManager extends Stubs {
    @Autowired
    private TestEntityManager testEntityManager;

    @Before
    public void setUp() {
        testEntityManager.clear();

        testEntityManager.merge(rpg);
        testEntityManager.merge(shooter);
        testEntityManager.merge(fpp);
        testEntityManager.merge(tpp);
        testEntityManager.merge(witcher);
        testEntityManager.merge(callOfDuty);
        testEntityManager.merge(fortnite);

        testEntityManager.flush();
    }
}
