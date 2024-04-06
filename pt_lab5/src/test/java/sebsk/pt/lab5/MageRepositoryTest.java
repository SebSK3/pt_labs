package sebsk.pt.lab5;

import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MageRepositoryTest {

    private MageRepository mageRepository;


    @Test
    public void delete_notfoundTest() {
        mageRepository = new MageRepository();
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> mageRepository.delete("mag1"));
    }

    @Test
    public void find_emptyTest() {
        mageRepository = new MageRepository();
        Optional<Mage> foundMage = mageRepository.find("mag1");
        assertTrue(foundMage.isEmpty());
    }

    @Test
    public void find_foundTest() {
        mageRepository = new MageRepository();
        Mage mage1 = new Mage("mag1", 1);
        mageRepository.save(mage1);

        Optional<Mage> foundMageOptional = mageRepository.find("mag1");

        assertTrue(foundMageOptional.isPresent());

        Mage foundMage = foundMageOptional.get();
        assertEquals(mage1.getName(), foundMage.getName());
        assertEquals(mage1.getLevel(), foundMage.getLevel());
    }

    @Test
    public void save_duplicateTest() {
        mageRepository = new MageRepository();
        Mage mage1 = new Mage("mag2", 2);
        Mage mage2 = new Mage("mag2", 3);
        mageRepository.save(mage1);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> mageRepository.save(mage2))
                .withMessage("Mage already exists. Name: mag2");
    }
}
