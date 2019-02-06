package com.jflavio1.daggerexample.domain.model;

import java.util.Arrays;
import java.util.Random;

public class OTPToken{
        public String getToken(int len)
        {
            String numbers = "0123456789";
            Random rndm = new Random();
            char[] otp = new char[len];
            for (int i = 0; i < len; i++)
            {
                otp[i] = numbers.charAt(rndm.nextInt(numbers.length()));
            }
            String data =Arrays.toString(otp);
            return data.substring(1,data.length()-1).replace(",","");
        }
}
