package org.example.Humans;

import org.example.Pets.Pet;

import java.util.*;

public class CollectionFamilyDao implements FamilyDao{
    private final List<Family> dataBase = new ArrayList<>();

    @Override
    public List<Family> getAllFamilies() {
        return dataBase;
    }

    @Override
    public Family getFamilyByIndex(int familyIndex) {
        if(dataBase.get(familyIndex) != null){
            dataBase.remove(dataBase.get(familyIndex));
            return dataBase.get(familyIndex);
        }
        return null;
    }

    @Override
    public boolean deleteFamily(int familyIndex) {
        if (familyIndex >= 0 && familyIndex < dataBase.size()) {
            dataBase.remove(familyIndex);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteFamily(Family family) {
        return dataBase.remove(family);
    }

    @Override
    public void saveFamily(Family family) {
        if(dataBase.contains(family)){
            dataBase.set(dataBase.indexOf(family) ,family);
        }
        else {
            dataBase.add(family);
        }

    }

    @Override
    public void displayAllFamilies() {
        for(Family family : dataBase){
            System.out.println(family.toString());
        }
    }

    @Override
    public List<Family> getFamiliesBiggerThan(int numberOfMember) {
        List<Family> result = new ArrayList<>();
        for(Family family : dataBase){
            if (family.countFamily() > numberOfMember){
                result.add(family);
                System.out.println(family.toString());
            }
        }
        if(!result.isEmpty()){
            return result;
        }
        else {
            return null;
        }
    }

    @Override
    public List<Family> getFamiliesLessThan(int numberOfMember) {
        List<Family> result = new ArrayList<>();
        for(Family family : dataBase){
            if (family.countFamily() < numberOfMember){
                result.add(family);
                System.out.println(family.toString());
            }
        }
        if(!result.isEmpty()){
            return result;
        }
        else {
            return null;
        }
    }

    @Override
    public int countFamiliesWithMemberNumber(int numberOfMember) {
        int result = 0 ;
        for(Family family : dataBase){
            if (family.countFamily() == numberOfMember){
                result++;
            }
        }
        return result;
    }

    @Override
    public void createNewFamily(Woman mother, Man father) {
        Family familyResult = new Family(mother,father);
        saveFamily(familyResult);
    }

    @Override
    public Family bornChild(Family family, String maleName, String femaleName) {
        Random random = new Random();
        Human children ;
        if( random.nextDouble() > 0.5){
            children = new Man(maleName , family.getFather().getSurname() , 0 );
        }
        else {
            children = new Woman(femaleName , family.getFather().getSurname() , 0 );
        }
        family.setChildren(children);
        saveFamily(family);
        return family;
    }

    @Override
    public Family adoptChild(Family family, Human child) {
        family.setChildren(child);
        saveFamily(family);
        return family;
    }

    @Override
    public void deleteAllChildrenOlderThen(int olderThenAge) {
        for(Family family : dataBase){
            for (int i = 0; i < family.children.size(); i++) {
                if (family.children.get(i).getYear() > olderThenAge){
                    family.deleteChild(i);
                }
            }
        }
    }

    @Override
    public int count() {
        return dataBase.size();
    }

    @Override
    public Family getFamilyById(int familyIndex) {
        return dataBase.get(familyIndex);
    }

    @Override
    public Set<Pet> getPets(int familyIndex) {

        if ( dataBase.get(familyIndex).getPet().isEmpty()){
            return null;
        }
        else {
               return dataBase.get(familyIndex).getPet();
        }
    }

    @Override
    public void addPet(int familyIndex, Pet pet) {
        dataBase.get(familyIndex).setPet(pet);
    }
}
