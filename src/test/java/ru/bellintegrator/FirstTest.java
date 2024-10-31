//package ru.bellintegrator;
//
//import org.junit.jupiter.api.*;
//
//public class FirstTest {
//    @BeforeAll
//    public static void beforeAll() {
//        System.out.println("Перед всеми");
//    }
//
//    @BeforeEach
//    public void beforeEach() {
//        System.out.println("Перед каждым");
//    }
//
//    @Test
//    public void firstTest(){
//        System.out.println("Первый тест");
//        Assertions.assertTrue(1==1,"1 не равно 1");
//    }
//    @Test
//    public void secondTest(){
//        System.out.println("Второй тест");
//        Assertions.assertTrue(2==2,"1 не равно 2");
//    }
//    @AfterEach
//    public void afterEach() {
//        System.out.println("После каждого");
//    }
//    @AfterAll
//    public static void afterAll() {
//        System.out.println("После всех");
//    }
//}
