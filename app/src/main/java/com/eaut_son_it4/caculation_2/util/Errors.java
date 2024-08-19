package com.eaut_son_it4.caculation_2.util;

import java.util.regex.Pattern;

public class Errors {
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9\\s]{1,50}$");
    private static final Pattern PRICE_PATTERN = Pattern.compile("^\\d+(\\.\\d{1,2})?$");
    private static final Pattern QUANTITY_PATTERN = Pattern.compile("^[1-9]\\d*$");

    public static class InvalidInputException extends Exception {
        public InvalidInputException(String message) {
            super(message);
        }
    }

    public static void validateName(String name) throws InvalidInputException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidInputException("Tên sản phẩm không được để trống.");
        }
        if (!NAME_PATTERN.matcher(name).matches()) {
            throw new InvalidInputException("Tên sản phẩm chỉ được chứa chữ cái, số và khoảng trắng (tối đa 50 ký tự).");
        }
    }

    public static void validatePrice(String price) throws InvalidInputException {
        if (price == null || price.trim().isEmpty()) {
            throw new InvalidInputException("Đơn giá không được để trống.");
        }
        if (!PRICE_PATTERN.matcher(price).matches()) {
            throw new InvalidInputException("Đơn giá phải là số dương (tối đa 2 chữ số thập phân).");
        }
    }

    public static void validateQuantity(String quantity) throws InvalidInputException {
        if (quantity == null || quantity.trim().isEmpty()) {
            throw new InvalidInputException("Số lượng không được để trống.");
        }
        if (!QUANTITY_PATTERN.matcher(quantity).matches()) {
            throw new InvalidInputException("Số lượng phải là số nguyên dương.");
        }
    }

    public static void handleException(Exception e) {
        // Log the exception or perform any other necessary actions
        e.printStackTrace();
    }
}