import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @program: tank
 * @description:
 * @author: Unuts
 * @create: 2020-08-04 06:34
 **/


public class TestImage {

    @Test
    public void test() throws IOException {
        BufferedImage image = ImageIO.read(TestImage.class.getClassLoader().getResourceAsStream("images/0.gif"));
        assertNotNull(image);
    }
}
