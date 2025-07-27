package com.Dinesh.CampusCribe.Exceptions;

public class FileAlreadyExistsException extends java.nio.file.FileAlreadyExistsException {
    public FileAlreadyExistsException(String message) {

        super(message);
    }
}
