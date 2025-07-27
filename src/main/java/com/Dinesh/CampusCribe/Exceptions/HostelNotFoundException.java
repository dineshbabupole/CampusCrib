package com.Dinesh.CampusCribe.Exceptions;

import com.Dinesh.CampusCribe.models.Hostel;

public class HostelNotFoundException extends RuntimeException{
    public HostelNotFoundException(String message) {
        super(message);

    }
}
