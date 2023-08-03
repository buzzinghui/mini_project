package com.example.assignment.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class FileConverterUtil {

    private static final int TARGET_WIDTH = 45;
    private static final int TARGET_HEIGHT = 45;

    public static String generateThumbnailBase64EncodedString(InputStream file) throws IOException {
        BufferedImage originalImage = ImageIO.read(file);
        BufferedImage resizedImage = new BufferedImage(TARGET_WIDTH, TARGET_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, TARGET_WIDTH, TARGET_HEIGHT, null);
        graphics2D.dispose();

        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, "jpg", bao);
        return Base64.getEncoder().encodeToString(bao.toByteArray());
    }

}