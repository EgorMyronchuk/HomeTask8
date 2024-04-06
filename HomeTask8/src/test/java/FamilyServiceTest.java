import org.example.Humans.*;
import org.example.Pets.Dog;
import org.example.Pets.DomesticCat;
import org.example.Pets.Pet;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class FamilyServiceTest {
    // Создаем экземпляр CollectionFamilyDao
    CollectionFamilyDao collectionFamilyDao = new CollectionFamilyDao();

    // Создаем экземпляр FamilyService, передавая ему CollectionFamilyDao
    FamilyService familyService = new FamilyService(collectionFamilyDao);
    Woman mother = new Woman("Anya","Tereshenko",33 );
    Man father = new Man("Vlad","Tereshenko",31);
    Woman mother1 = new Woman("Ylia","Rytchenko",22 );
    Man father1 = new Man("Anton","Rytchenko",21);
    Woman mother2 = new Woman("Lena","Lykash",23 );
    Man father2 = new Man("Maks","Lykash",26);

    Woman child = new Woman("Anya","Tereshenko",10 );
    Man child1 = new Man("Timur","Rytchenko",11);
    Man child2 = new Man("Timur","Rytchenko",12);
    Man child3 = new Man("Timur","Rytchenko",13);

    Pet pet1 = new Dog("sharik");
    Pet pet2 = new Dog("Gav");
    Pet pet3 = new DomesticCat("Meow");

    List<Human> children2 = new ArrayList<>();
    Family family1 = new Family(mother1,father1);
    Family family2 = new Family(mother2,father2);

    @Test
    public void getAllFamilies (){
        collectionFamilyDao.saveFamily(family1);
        assertEquals(1, familyService.getAllFamilies().size());
    }
    @Test
    public void displayAllFamilies(){
        String expected = "Family{\n" +
                "mother=Woman{name='Ylia', surname='Rytchenko', year=22, iq=0, schedule={}},\n" +
                "father=Man{name='Anton', surname='Rytchenko', year=21, iq=0, schedule={}},\n" +
                "children=[],\n" +
                "pet=[]}";
        collectionFamilyDao.saveFamily(family1);
        StringBuilder str = new StringBuilder();
        for(Family family : collectionFamilyDao.getAllFamilies()){
            str.append(family.toString());
        }
        assertEquals(expected, str.toString());
    }
    @Test
    public void getFamiliesBiggerThan(){
        collectionFamilyDao.saveFamily(family2);
        collectionFamilyDao.saveFamily(family1);
        collectionFamilyDao.addPet(0,pet1);
        assertEquals(1, familyService.getFamiliesBiggerThan(2).size());
    }
    @Test
    public void getFamiliesLessThan(){
        collectionFamilyDao.saveFamily(family2);
        collectionFamilyDao.saveFamily(family1);
        collectionFamilyDao.addPet(0,pet1);
        assertEquals(1, familyService.getFamiliesLessThan(3).size());
    }
    @Test
    public void countFamiliesWithMemberNumber(){
        collectionFamilyDao.saveFamily(family2);
        collectionFamilyDao.saveFamily(family1);
        collectionFamilyDao.createNewFamily(mother,father);
        collectionFamilyDao.addPet(0,pet1);
        assertEquals(1, familyService.countFamiliesWithMemberNumber(3));
    }
    @Test
    public void createNewFamily(){
        collectionFamilyDao.saveFamily(family2);
        collectionFamilyDao.saveFamily(family1);
        collectionFamilyDao.createNewFamily(mother,father);
        collectionFamilyDao.addPet(0,pet1);
        Woman mother4 = new Woman("Nikit","Mishenko",23 );
        Man father4 = new Man("Ilia","Mish",26);
        collectionFamilyDao.createNewFamily(mother4,father4);
        assertEquals(4, familyService.getAllFamilies().size());
    }
    @Test
    public void deleteFamilyByIndex(){
        collectionFamilyDao.saveFamily(family2);
        collectionFamilyDao.saveFamily(family1);
        collectionFamilyDao.createNewFamily(mother,father);
        collectionFamilyDao.addPet(0,pet1);
        familyService.deleteFamilyByIndex(0);
        assertEquals(2 , familyService.getAllFamilies().size());
    }
    @Test
    public void bornChild (){
        collectionFamilyDao.saveFamily(family2);
        collectionFamilyDao.saveFamily(family1);
        collectionFamilyDao.createNewFamily(mother,father);
        collectionFamilyDao.addPet(0,pet1);
        familyService.bornChild(familyService.getFamilyById(2),"Sergei" , "Alina");
        assertEquals(3,familyService.getFamilyByIndex(2).countFamily());
    }
    @Test
    public void adoptChild(){
        collectionFamilyDao.saveFamily(family2);
        collectionFamilyDao.saveFamily(family1);
        collectionFamilyDao.createNewFamily(mother,father);
        collectionFamilyDao.addPet(0,pet1);
        familyService.adoptChild(familyService.getFamilyById(0),child1);
        assertEquals(4,familyService.getFamilyByIndex(0).countFamily());
    }
    @Test
    public void deleteAllChildrenOlderThen (){
        collectionFamilyDao.saveFamily(family2);
        collectionFamilyDao.saveFamily(family1);
        collectionFamilyDao.createNewFamily(mother,father);
        collectionFamilyDao.addPet(0,pet1);
        familyService.adoptChild(familyService.getFamilyById(0),child);
        familyService.adoptChild(familyService.getFamilyById(1),child1);
        familyService.adoptChild(familyService.getFamilyById(2),child2);
        familyService.deleteAllChildrenOlderThen(11);
        int countMemberInFamily = 0 ;
        for(Family family : familyService.getAllFamilies()){
            countMemberInFamily += family.countFamily();
        }
        assertEquals(9 , countMemberInFamily);
    }
    @Test
    public void count (){
        collectionFamilyDao.saveFamily(family2);
        collectionFamilyDao.saveFamily(family1);
        collectionFamilyDao.createNewFamily(mother,father);
        assertEquals(3 , familyService.getAllFamilies().size());
    }
    @Test
    public void getFamilyById (){
        collectionFamilyDao.saveFamily(family2);
        collectionFamilyDao.saveFamily(family1);
        collectionFamilyDao.createNewFamily(mother,father);
        assertEquals(family1,familyService.getFamilyById(1));
    }
    @Test
    public void getPets (){
        collectionFamilyDao.saveFamily(family2);
        collectionFamilyDao.saveFamily(family1);
        collectionFamilyDao.createNewFamily(mother,father);
        collectionFamilyDao.addPet(0,pet1);
        assertEquals(1, familyService.getPets(0).size());
    }
    @Test
    public void addPet(){
        collectionFamilyDao.saveFamily(family2);
        collectionFamilyDao.saveFamily(family1);
        collectionFamilyDao.createNewFamily(mother,father);
        collectionFamilyDao.addPet(0,pet1);
        familyService.addPet(0,pet2);
        assertEquals(2,familyService.getPets(0).size());
    }
}
