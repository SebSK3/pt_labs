package sebsk.pt.lab6;

import org.apache.commons.lang3.tuple.Pair;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        int threads = 1;
        if (args.length == 0) {
            System.out.println("no number of threads provided");
        } else {
            threads = Integer.parseInt(args[0]);
        }


        List<Path> files;
        Path source = Path.of("pt_lab6/src/main/resources/photos");
        System.out.println(source);
        try {
            Stream<Path> stream = Files.list(source);
            files = stream.toList();
            stream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ForkJoinPool threadPool = new ForkJoinPool(threads);

        long time = System.currentTimeMillis();

        try {

            threadPool.submit(() ->
                    files.parallelStream()
                            .map(path -> {
                                try {
                                    BufferedImage image = ImageIO.read(path.toFile());
                                    return Pair.of(path.getFileName().toString(), image);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            })
                            .map(pair -> {
                                BufferedImage original = pair.getRight();
                                BufferedImage transformedImage = transform_image(original);


                                return Pair.of(pair.getLeft(), transformedImage);
                            })
                            .forEach(pair -> {
                                try {
                                    ImageIO.write(pair.getRight(), "jpg", Path.of("pt_lab6/src/main/resources/out", pair.getLeft()).toFile());
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            })).get();
        } catch (InterruptedException | ExecutionException e) {
            threadPool.close();
            throw new RuntimeException(e);
        }
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + "s");
        threadPool.shutdown();
        threadPool.close();
    }
    public static BufferedImage transform_image(BufferedImage original) {

        BufferedImage transformedImage = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
        for (int i = 0; i < original.getWidth(); i++) {
            for (int j = 0; j < original.getHeight(); j++) {
                int rgb = original.getRGB(i, j);
                Color color = new Color(rgb);

                int red = color.getBlue();
                int green = color.getGreen();
                int blue = color.getRed();
                int finalColor = (red+green+blue)/3;
                if (finalColor > 255) { 
                    finalColor = 255;
                }
                Color outColor = new Color(finalColor, finalColor, finalColor);
                transformedImage.setRGB(i, j, outColor.getRGB());
            }
        }
        return transformedImage;
    }
}
