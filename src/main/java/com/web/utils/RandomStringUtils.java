package com.web.utils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class RandomStringUtils {
    public static String generateCustomCode(int randomLength) {

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = currentDate.format(formatter);

        String randomString = generateRandomString(randomLength);

        // Trả về mã theo định dạng "HD + ngày tháng năm + chuỗi ngẫu nhiên"
        return "HD" + formattedDate + randomString;
    }

    private static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        // Sinh chuỗi ngẫu nhiên
        return new Random().ints(length, 0, characters.length())
                .mapToObj(i -> String.valueOf(characters.charAt(i)))
                .reduce("", (s1, s2) -> s1 + s2);
    }
}

