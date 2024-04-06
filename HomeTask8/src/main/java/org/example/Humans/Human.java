package org.example.Humans;



import org.example.Pets.Pet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Human {
    private String name;
    private String surname;
    private int year;
    private int iq;
    private Family family;
    private Map<String,String> schedule = new HashMap<String,String>() {
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return year == human.year && iq == human.iq && Objects.equals(name, human.name) && Objects.equals(surname, human.surname) && Objects.equals(family, human.family) && Objects.equals(schedule, human.schedule);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, surname, year, iq, family);
        result = 31 * schedule.hashCode();
        return result;
    }

    public Human() {
    }

    public Human(String name, String surname, int year) {
        this.name = name;
        this.surname = surname;
        this.year = year;
    }

    public Human(String name, String surname, int year, int iq) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.iq = iq;

    }

    public Human(String name, String surname, int year, int iq, Map<String,String> schedule) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.iq = iq;
        this.schedule = schedule;
    }

    // Геттеры
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getYear() {
        return year;
    }

    public int getIq() {
        return iq;
    }

    public Family getFamily() {
        return family;
    }

    public Map<String, String> getSchedule() {
        return schedule;
    }

    // Сеттеры
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setIq(int iq) {
        this.iq = iq;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public void setSchedule(Map<String,String> schedule) {
        this.schedule = schedule;
    }

    public void greetPet() {
        for (Pet petInd : family.pet)  System.out.printf("Привіт, %s \n", petInd.getNickname());
    }

    public void describePet() {
        for (Pet petInd : family.pet) {
            if (petInd.getTrickLevel() > 50) {
                System.out.printf("У мене є %s, їй %d років, він дуже хитрий", petInd.getSpecies(), petInd.getAge());
            } else {
                System.out.printf("У мене є %s, їй %d років, він майже не хитрий", petInd.getSpecies(), petInd.getAge());
            }
        }
    }

    @Override
    public String toString() {
        return   getClass().getSimpleName() + "{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", year=" + year +
                ", iq=" + iq +
                ", schedule=" + schedule.toString() +
                '}';
    }

    @Override
    protected void finalize() {
        System.out.println(this);
    }
}
