package com.dreamteam.algorithm.analysis.config;

import org.modelmapper.ModelMapper;

import java.security.SecureRandom;

public class GlobalStaticConstants {
    public static final ModelMapper modelMapper = new ModelMapper();
    public static final SecureRandom secureRandom = new SecureRandom();
}