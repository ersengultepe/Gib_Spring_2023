package com.works.configs;

import com.works.utils.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValid( MethodArgumentNotValidException ex ) {
        List<FieldError> fieldErrors = ex.getFieldErrors();
        List ls = parseError(fieldErrors);
        return Util.fail(ls, HttpStatus.BAD_REQUEST);
    }

    private List parseError(List<FieldError> fieldErrors) {
        List ls = new ArrayList();
        for ( FieldError item : fieldErrors ) {
            Map<String, Object> hm = new LinkedHashMap<>();
            hm.put("field", item.getField());
            hm.put("defaultMessage", item.getDefaultMessage());
            hm.put("rejectedValue", item.getRejectedValue());
            ls.add(hm);
        }
        return ls;
    }


}
