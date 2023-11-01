package com.prueba.hernando.util;

import java.util.List;

public class ValidationCustomer extends RuntimeException{
    public ValidationCustomer (List<String>violationsMessages){
        super(formatViolationMessages(violationsMessages));
    }
    private static String formatViolationMessages(List<String> violationMessages) {
        return String.join(", ", violationMessages);
    }
}
