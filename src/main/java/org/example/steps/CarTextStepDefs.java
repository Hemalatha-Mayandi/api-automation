package org.example.steps;

import io.qameta.allure.Step;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CarTextStepDefs {

    String carInput = "src/main/resources/template/text/car_input.txt";
    String carOutput = "src/main/resources/template/text/car_output.txt";

    @Step
    @SneakyThrows
    public void extractCarInputWithContains() {
        List<String> reg = Files.lines(Paths.get(carInput))
                .filter(l -> l.contains("registration"))
                .flatMap(l -> Arrays.stream(l.split("\n")))
                .collect(Collectors.toList());


        int size = reg.size();
        for (int i = 0; i < size; i++) {
            int regPosition = reg.get(i).indexOf("registration");
            int regCount = "registration".length();
            int carRegPosition = regPosition + regCount + 1;
            String carReg = reg.get(i).substring(carRegPosition, carRegPosition + 9);
            System.out.println(" Car Reg " + carReg);
        }
    }

    @SneakyThrows
    @Step
    public void extractCarInputWithRegix() {
        List<String> regLines = Files.lines(Paths.get(carInput))
                .filter(l -> l.contains("registration"))
                .flatMap(l -> Arrays.stream(l.split("\n")))
                .collect(Collectors.toList());

        System.out.println("Lines :: " + regLines);
        int size = regLines.size();
        for (int i = 0; i < size; i++) {
        }

    }

    @SneakyThrows
    @Step
    public String extractRandomCarInputWithPattern() {
        List<String> regLines = Files.lines(Paths.get(carInput))
                .filter(l -> l.contains("registration"))
                .collect(Collectors.toList());

        long countLines = regLines.stream().count();
        System.out.println("CountLines  " + countLines);

        List<String> carReg = new ArrayList<String>();

        for (int i = 0; i < countLines; i++) {

            String s = regLines.get(i);
            /** Read a line to find regex pattern
             * eg :AD58 VNF and SG18 HTN**/
            Pattern p = Pattern.compile("\\b[A-Z]{1,}[0-9]{2}\s[A-Z]{1,}\\b");
            Matcher m = p.matcher(s);
            while (m.find()) {
                String word = m.group();
                carReg.add(word);
            }
        }
        List<String> carRegls = carReg;
        Random r = new Random();
        String randomCarReg = carRegls.get(r.nextInt(carRegls.size()));

        return randomCarReg;
    }

    @SneakyThrows
    @Step
    public String extractCarOutput(String carRegNo) {
        List<String> collect = Files.lines(Paths.get(carOutput))
                .skip(1)
                .collect(Collectors.toList());

        int size = collect.size();
        String carRegls = null;

        for (int i = 0; i < size; i++) {

            while (collect.get(i).contains(carRegNo)) {
                carRegls = collect.get(i);
                break;
            }
        }
        return carRegls;
    }

}
