package sebsk.pt.lab5;


import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class MageControllerTest {

    private MageController mageController;
    private MageRepository mockedRepository;

    @BeforeEach
    public void setUp() {
        mockedRepository = mock(MageRepository.class);
        mageController = new MageController(mockedRepository);
    }

    @Test
    public void delete_doneTest() {
        when(mockedRepository.find("mag1")).thenReturn(Optional.of(new Mage("mag1", 10)));

        assertThat(mageController.delete("mag1")).isEqualTo("done");
    }

    @Test
    public void delete_notfoundTest() {
        doThrow(new IllegalArgumentException()).when(mockedRepository).delete("mag1");
        assertThat(mageController.delete("mag1")).isEqualTo("not found");
    }

    @Test
    public void find_notfoundTest() {
        when(mockedRepository.find("mag1")).thenReturn(Optional.empty());
        assertThat(mageController.find("mag1")).isEqualTo("not found");
    }

    @Test
    public void find_foundTest() {
        Mage mage = new Mage("mag2", 10);
        when(mockedRepository.find("mag2")).thenReturn(Optional.of(mage));
        assertThat(mageController.find("mag2")).isEqualTo(mage.toString());
    }

    @Test
    public void save_validTest() {
        assertThat(mageController.save("mag1", 10)).isEqualTo("done");
    }

    @Test
    public void save_duplicateTest() {
        doThrow(new IllegalArgumentException()).when(mockedRepository).save(new Mage("mag1", 10));
        assertThat(mageController.save("mag1", 10)).isEqualTo("bad request");
    }
}
