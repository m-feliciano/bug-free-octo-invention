package br.com.feliciano.forum.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter

public class ErrorDTO implements Serializable {

    private String camp;
    private String error;
}
