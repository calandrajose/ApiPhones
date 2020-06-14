package com.phones.phones;

import com.phones.phones.dto.InfrastructureCallDto;
import com.phones.phones.dto.RateDto;
import com.phones.phones.model.*;

import java.util.ArrayList;
import java.util.List;

import static com.phones.phones.model.LineStatus.ENABLED;

public class TestFixture {


    public static List<RateDto> testListOfRates(){
        List<RateDto> rates = new ArrayList<>();
        RateDto newRate = RateDto.builder()
                .id(1L)
                .originCity("Mar del plata")
                .destinationCity("Buenos Aires")
                .priceMinute((float) 5)
                .build();

        RateDto newRate2 = RateDto.builder()
                .id(2L)
                .originCity("Mar del plata")
                .destinationCity("Miramar")
                .priceMinute((float) 2.5)
                .build();
        rates.add(newRate);
        rates.add(newRate2);

        return rates;
    }

    public static Province testProvince(){
        Province newProvince = Province.builder()
                .id(1L)
                .name("Buenos Aires")
                .cities(new ArrayList<City>())
                .build();
        return newProvince;
    }


    /*********** Lines objects ***********/

    public static Line testLine(String number){
        Line newLine = Line.builder()
                .id(1L)
                .number(number)
                .creationDate(new java.sql.Date(2020,06,10))
                .status(ENABLED)
                .lineType(new LineType(1L, "Mobile", null))
                .user(testUser())
                .invoices(new ArrayList<>())
                .originCalls(new ArrayList<>())
                .destinationCalls(new ArrayList<>())
                .build();
        return newLine;
    }



    /*********** Calls Objects ***********/

    public static Call testCall(){
        Call newCall = Call.builder()
                .id(1L)
                .duration(5)
                .totalPrice((float) 15)
                .creationDate(new java.sql.Date(2020,06,10))
                .originNumber("2235472861")
                .destinationNumber("22322222")
                .originLine(new Line())
                .destinationLine((new Line()))
                .rate(new Rate())
                .invoice(new Invoice())
                .build();
        return newCall;
    }

    public static List<Call> testListOfCalls(){
        List<Call> calls = new ArrayList<>();
        Call newCall = Call.builder()
                .id(1L)
                .duration(5)
                .totalPrice((float) 15)
                .creationDate(new java.sql.Date(2020,06,10))
                .originNumber("2235472861")
                .destinationNumber("22322222")
                .originLine(new Line())
                .destinationLine((new Line()))
                .rate(new Rate())
                .invoice(new Invoice())
                .build();

        Call newCall2 = Call.builder()
                .id(2L)
                .duration(10)
                .totalPrice((float) 30)
                .creationDate(new java.sql.Date(2020,06,14))
                .originNumber("22322222")
                .destinationNumber("2235472861")
                .originLine(new Line())
                .destinationLine((new Line()))
                .rate(new Rate())
                .invoice(new Invoice())
                .build();

        calls.add(newCall);
        calls.add(newCall2);

        return calls;
    }

    public static InfrastructureCallDto testInfrastructureCallDto(){
        InfrastructureCallDto newCall = InfrastructureCallDto.builder()
                .user("rl")
                .password("123")
                .originNumber("2235472861")
                .destinationNumber("22322222")
                .duration(5)
                .creationDate(new java.sql.Date(2020,06,10))
                .build();
        return newCall;
    }




        /*********** User objects ***********/

    public static User testDisabledUser(){
        User newUser = User.builder()
                .id(1L)
                .name("Rodrigo")
                .surname("Leon")
                .dni("404040")
                .username("rl")
                .password("123")
                .isActive(false)
                .city(new City())
                .build();
        return newUser;
    }

    public static User testUser(){
        User newUser = User.builder()
                .id(1L)
                .name("Rodrigo")
                .surname("Leon")
                .dni("404040")
                .username("rl")
                .password("123")
                .isActive(true)
                .city(new City())
                .build();
        return newUser;
    }

    public static List<User> testListOfUsers(){
        List<User> users = new ArrayList<>();
        User newUser = User.builder()
                .id(1L)
                .name("Rodrigo")
                .surname("Leon")
                .dni("404040")
                .username("rl")
                .password("123")
                .isActive(true)
                .city(new City())
                .build();

        User newUser2 = User.builder()
                .id(2L)
                .name("Jose")
                .surname("Calandra")
                .dni("373737")
                .username("jc")
                .password("123")
                .isActive(true)
                .city(new City())
                .build();
        users.add(newUser);
        users.add(newUser2);

        return users;
    }

}
