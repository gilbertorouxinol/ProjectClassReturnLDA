package project;

import inout.Keyboard;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 *  IntelliJ IDEA 2021.2.2 (Ultimate Edition)<br>
 *  Licensed to Gilberto Rouxinol<br>
 *  For educational use only.<br><br>
 *
 *  Polytechnic Institute of Viseu<br>
 *  School of Technology and Management of Viseu<br><br>
 *
 *  Class created by Gilberto Rouxinol on 2022<br>
 *  Copyright © 2022 Gilberto Rouxinol<br>
 *  All rights reserved<br><br>
 *
 *  The class implements a project for managing store loyalty cards. The use of JAVA classes is not allowed, except for
 *  the Keyboard class and the classes inside the library java.util. The project has an educational purpose and was
 *  developed at the level of the first year of the first semester of the IPV ESTGV DI DWDM course on the Introduction
 *  to Programming curricular unit.<br><br>
 *
 *  The ClassReturnLda class has the following methods:<br>
 *    - System methods:<br>
 *          (1) main() <br>
 *    - Profession methods:<br>
 *          (1) createCard()<br>
 *          (2) deleteCard()<br>
 *          (3) readCard()<br>
 *          (4) store1()<br>
 *          (5) store2()<br>
 *          (6) msg()<br>
 *    - Specialized profession methods<br>
 *          (1) putItemsInShoppingCart()<br>
 *          (2) customerShowCartAndSystemWritesReceipt()<br>
 *    - Task methods:<br>
 *          (1) generateID()<br>
 *          (2) findID()<br>
 *          (3) findIndexIntArray()<br>
 *          (4) findIndexDoubleArray()<br>
 *          (5) readDatabase1()<br>
 *          (6) readDatabase2()<br>
 *          (7) readDatabase3()<br>
 *          (8) readDatabase7Names()<br>
 *          (9) readDatabase7Prices()<br>
 *          (10) readDatabaseLimits1And2()<br>
 *          (11) readDatabaseLimits3()<br>
 *          (12) readDatabase4()<br>
 *          (13) readDatabase5()<br>
 *          (14) readDatabase6()<br>
 *          (15) readDatabase8Names()<br>
 *          (16) readDatabase8Prices()<br>
 *          (17) readDatabaseLimits4And5()<br>
 *          (18) readDatabaseLimits6()<br>
 *          (19) readDatabaseCard()
 *
 * @author Gilberto Rouxinol
 * @version 2022.11.15
 *
 */
public class ClassReturnLda {

    public static final double IVA = 23;
    public static final String TODAY = "2022-11-08 15H15";

    public static void main(String[] args) {

        HashMap<Integer, ArrayList<String>> mapCard = new HashMap<>();
        mapCard = readDatabaseCard();

        for (ArrayList<String> list : mapCard.values())
            System.out.println(list.get(0) + " " + list.get(1));
        System.out.println("\nRunning classe projeto.ClassReturnLda\n");

        char op;
        do{
            System.out.println("INDICAR UMA OPÇÃO-----------------------------");
            System.out.println(" 1 - Criar CF");
            System.out.println(" 2 - Apagar CF (indicar nome e depois apelido)");
            System.out.println(" 3 - Ler CF (indicar nome e depois apelido)");
            System.out.println(" 4 - Loja - Produtos Frescos");
            System.out.println(" 5 - Loja - Oficina na Hora");
            System.out.println(" * - Sair");
            System.out.println("-----------------------------------------------");

            op = Keyboard.readChar();

            switch (op){
                case '1': createCard(mapCard); break;
                case '2': deleteCard(mapCard); break;
                case '3': readCard(mapCard); break;
                case '4': store1(mapCard); break;
                case '5': store2(mapCard); break;
                case '*': msg(); System.exit(1);
            }

        }while( op!='*' );
    }

    public static void createCard(HashMap<Integer, ArrayList<String>> mapCard){
        ArrayList<String> newCard = new ArrayList<>();
        String newString = "";
        int newID = generateID(mapCard);

        System.out.println("Nome");
        newString = Keyboard.readString();
        newCard.add(newString);

        System.out.println("Apelido");
        newString = Keyboard.readString();
        newCard.add(newString);

        System.out.println("NIF");
        newString = Keyboard.readString();
        newCard.add(newString);

        System.out.println("Aniversario - Dia");
        newString = Keyboard.readString();
        newCard.add(newString);

        System.out.println("Aniversario - Mes");
        newString = Keyboard.readString();
        newCard.add(newString);

        System.out.println("Aniversario - Ano");
        newString = Keyboard.readString();
        newCard.add(newString);

        newCard.add("100");

        newCard.add("1");

        mapCard.put(newID, newCard);

        System.out.println("CF n.o  " + newID + "  CRIADO COM SUCESSO.");
    }

    public static int generateID(HashMap<Integer, ArrayList<String>> mapCard){
        double auxID;
        int newID;
        while (true){
            auxID = Math.random()*(1000-100) + 100;
            newID = (int) auxID;
            if( !mapCard.containsKey(newID) )
                return newID;
        }
    }

    public static void deleteCard(HashMap<Integer, ArrayList<String>> mapCard){
        int findID = findID(mapCard);
        if(findID != -1){
            mapCard.get(findID).set(7, "0");
            System.out.println("CF APAGADO COM SUCESSO.");
        }else{
            System.out.println("CF NAO ENCONTRADO. CF NAO APAGADO.");
        }
    }

    public static int findID(HashMap<Integer, ArrayList<String>> mapCard){
        System.out.println("Indicar nome");
        String name = Keyboard.readString();

        System.out.println("Indicar apelido");
        String surname = Keyboard.readString();

        for (int i : mapCard.keySet())
            if( mapCard.get(i).get(0).equals(name) && mapCard.get(i).get(1).equals(surname) && mapCard.get(i).get(7).equals("1"))
                return i;
        return -1;
    }

    public static void readCard(HashMap<Integer, ArrayList<String>> mapCard){
        int findID = findID(mapCard);
        if(findID != -1){
            for (String item : mapCard.get(findID))
                System.out.println(item);
            System.out.println("ID: " + findID);
        }else{
            System.out.println("CF NAO ENCONTRADO.");
        }
    }

    public static void store1(HashMap<Integer, ArrayList<String>> mapCard){
        // OWNER BUILD THE STORE AND DEFINES THE RULES TO THE PRICES
        HashMap<Integer, ArrayList<Double>> map1 = new HashMap<>();         // Load table 1
        map1 = readDatabase1();

        HashMap<Integer, ArrayList<Integer>> map2 = new HashMap<>();        // Load table 2
        map2 = readDatabase2();

        HashMap<Integer, ArrayList<Integer>> map3 = new HashMap<>();        // Load table 3
        map3 = readDatabase3();

        HashMap<Integer, String> map7Names = new HashMap<>();               // Load table 7 - Names
        map7Names = readDatabase7Names();

        HashMap<Integer, Double> map7Prices = new HashMap<>();              // Load table 7 - Prices
        map7Prices = readDatabase7Prices();

        int limitsMap1Map2 [] = readDatabaseLimits1And2();                  // Load limits table 1 and 2

        double limitsMap3 [] = readDatabaseLimits3();                       // Load limits table 3

        // CUSTOMER PUT ITEMS IN THE SHOPPING CART
        ArrayList<Integer> shoppingCart = new ArrayList<>(); // { productID1, quantity1, productID2, quantity2, ...}
        putItemsInShoppingCart(shoppingCart);

        // CUSTOMER SHOW THE CARD
        System.out.println("Tem CF [1 ou 0]?");
        byte haveCard = Keyboard.readByte();
        if (haveCard == 0)
            createCard(mapCard);

        System.out.println("Indicar o ID.");
        int iD = Keyboard.readInteger();

        System.out.println("Descontar os pontos [1 ou 0]?");
        byte applyDiscounts = Keyboard.readByte();

        // CUSTOMER SHOW THE CART AND THE SYSTEM WRITES THE RECEIPT
        ArrayList<String> receipt = new ArrayList<>();
        customerShowCartAndSystemWritesReceipt( iD, applyDiscounts, shoppingCart, mapCard, map1, map2, map3, map7Names,
                map7Prices, limitsMap1Map2, limitsMap3, receipt);

        // SHOW RECEIPT
        for (String item : receipt)
            System.out.println(item);

    }

    public static void store2(HashMap<Integer, ArrayList<String>> mapCard){
        // OWNER BUILD THE STORE AND DEFINES THE RULES TO THE PRICES
        HashMap<Integer, ArrayList<Double>> map4 = new HashMap<>();         // Load table 4
        map4 = readDatabase4();

        HashMap<Integer, ArrayList<Integer>> map5 = new HashMap<>();        // Load table 5
        map5 = readDatabase5();

        HashMap<Integer, ArrayList<Integer>> map6 = new HashMap<>();        // Load table 6
        map6 = readDatabase6();

        HashMap<Integer, String> map8Names = new HashMap<>();               // Load table 8 - Names
        map8Names = readDatabase8Names();

        HashMap<Integer, Double> map8Prices = new HashMap<>();              // Load table 8 - Prices
        map8Prices = readDatabase8Prices();

        int limitsMap4Map5 [] = readDatabaseLimits4And5();                  // Load limits table 4 and 5

        double limitsMap6 [] = readDatabaseLimits6();                       // Load limits table 6

        // CUSTOMER PUT ITEMS IN THE SHOPPING CART
        ArrayList<Integer> shoppingCart = new ArrayList<>(); // { productID1, quantity1, productID2, quantity2, ...}
        putItemsInShoppingCart(shoppingCart);

        // CUSTOMER SHOW THE CARD
        System.out.println("Tem CF [1 ou 0]?");
        byte haveCard = Keyboard.readByte();
        if (haveCard == 0)
            createCard(mapCard);

        System.out.println("Indicar o ID.");
        int iD = Keyboard.readInteger();

        System.out.println("Descontar os pontos [1 ou 0]?");
        byte applyDiscounts = Keyboard.readByte();

        // CUSTOMER SHOW THE CART AND THE SYSTEM WRITES THE RECEIPT
        ArrayList<String> receipt = new ArrayList<>();
        customerShowCartAndSystemWritesReceipt( iD, applyDiscounts, shoppingCart, mapCard, map4, map5, map6, map8Names,
                map8Prices, limitsMap4Map5, limitsMap6, receipt);

        // SHOW RECEIPT
        for (String item : receipt)
            System.out.println(item);
    }

    public static void putItemsInShoppingCart(ArrayList<Integer> shoppingCart){
        int productID = 0;
        int quantity = 0;

        Boolean flag = true;
        while(flag){
            System.out.println("Ler o ID do produto [0 para terminar a leitura]."); // End of list indicated with product ID zero
            productID = Keyboard.readInteger();
            if( productID != 0){
                System.out.println("Indicar a quantidade.");
                quantity = Keyboard.readInteger();
                shoppingCart.add(productID);
                shoppingCart.add(quantity);
            } else {
                flag = false;
                shoppingCart.add(0);
            }
        }
    }

    public static void customerShowCartAndSystemWritesReceipt( int iD, byte applyDiscounts, ArrayList<Integer> shoppingCart,
            HashMap<Integer, ArrayList<String>> mapCard, HashMap<Integer, ArrayList<Double>> mapA, HashMap<Integer, ArrayList<Integer>> mapB,
            HashMap<Integer, ArrayList<Integer>> mapC, HashMap<Integer, String> mapDNames, HashMap<Integer, Double> mapDPrices,
            int [] limitsMapAMapB, double [] limitsMapC, ArrayList<String> receipt){

        int ptsCard = Integer.parseInt(mapCard.get(iD).get(6)); // Number of points on the card

        int indexAAndB = findIndexIntArray(limitsMapAMapB, ptsCard); // Mapping table A and B
        int indexC = 0;  // Mapping table C (It is calculated based on the amount payable)

        Boolean flag = true; // It is true until empty the cart

        int indexShoppingCart = 0;
        int pointsSubtract = 0;
        int pointsAdd = 0;
        int counterPointsSubtract = 0;
        int counterPointsAdd = 0;
        int counterPointsTotal = 0;
        int updatePointsCard = 0;
        int productID = 0;
        int quantity = 0;
        int rowShop = 0;

        double price = 0;
        double priceWithoutDiscount = 0;
        double discount = 0;
        double percentage = 0;
        double priceWithDiscount = 0;
        double sumPricesWithoutDiscount = 0;
        double sumDiscounts = 0;
        double sumPricesWithDiscounts = 0;
        double priceWithTax = 0;

        String productName = "";
        String info = "";

        receipt.add(TODAY);
        receipt.add(mapCard.get(iD).get(0) + " " + mapCard.get(iD).get(1));
        receipt.add(mapCard.get(iD).get(2));

                     //  indexShoppingCart --->   0          1           2          3
        while(flag){ // shoppingCart = { productID1, quantity1, productID2, quantity2, ...}

            productID = shoppingCart.get(indexShoppingCart);
            quantity = shoppingCart.get(++indexShoppingCart);

            productName = mapDNames.get(productID);
            price = mapDPrices.get(productID);

            priceWithoutDiscount = price*quantity;
            sumPricesWithoutDiscount += priceWithoutDiscount;

            rowShop = (int) productID/100; //  Divided by 100 to get only the first two digits

            if (indexAAndB != -1)
                percentage = mapA.get(rowShop).get(indexAAndB);
            else
                percentage = 0;

            discount = applyDiscounts*priceWithoutDiscount*(percentage/100.0);
            sumDiscounts += discount;

            priceWithDiscount = priceWithoutDiscount - discount;
            sumPricesWithDiscounts += priceWithDiscount;

            if (indexAAndB != -1)
                pointsSubtract = mapB.get(rowShop).get(indexAAndB);
            else
                pointsSubtract = 0;

            counterPointsSubtract += pointsSubtract;

            indexC = findIndexDoubleArray(limitsMapC, priceWithDiscount);
            if (indexC != -1)
                pointsAdd = mapC.get(rowShop).get(indexC);
            else
                pointsAdd = 0;
            counterPointsAdd += pointsAdd;

            receipt.add("............... " + productName);
            receipt.add(priceWithoutDiscount + " euro");
            receipt.add(discount + " euro (" + percentage + " %)");
            receipt.add(priceWithDiscount + " euro");
            receipt.add(pointsSubtract + " pontos (-)");
            receipt.add(pointsAdd + " pontos (+)");

            ++indexShoppingCart;

            if (shoppingCart.get(indexShoppingCart) == 0) { // The cart is empty
                receipt.add("............... " + "Totais");

                receipt.add(sumPricesWithoutDiscount + " euros");
                receipt.add(sumDiscounts + " euros");
                receipt.add(sumPricesWithDiscounts + " euros");

                priceWithTax = sumPricesWithDiscounts*(1 + IVA/100.0);

                receipt.add(priceWithTax + " euros");

                receipt.add(counterPointsSubtract + " pontos (-)");
                receipt.add(counterPointsAdd + " pontos (+)");

                counterPointsTotal = counterPointsAdd - counterPointsSubtract;

                if (counterPointsTotal < 0)
                    info = " (negativos)";
                receipt.add(counterPointsTotal + " pontos acumulados" + info);

                updatePointsCard = ptsCard + counterPointsTotal;

                receipt.add(updatePointsCard + " pontos (CF)");

                mapCard.get(iD).set(6, String.valueOf(updatePointsCard));
                flag = false;
            }
        }
    }

    public static int findIndexIntArray(int arr[], int pts){
        int leng = arr.length;

        for(int i = 0; i < leng-1; ++i)
            if(pts >= arr[i] && pts < arr[i+1])
                return i;

        if(pts >= arr[leng-1])
            return leng-1;

        return -1;
    }

    public static int findIndexDoubleArray(double arr[], double euros){
        int leng = arr.length;

        for(int i = 0; i < leng-1; ++i)
            if(euros >= arr[i] && euros < arr[i+1])
                return i;

        if(euros >= arr[leng-1])
            return leng-1;

        return -1;
    }

    public static void msg(){
        System.out.println("Adeus - Volte Sempre!");
    }

    public static int [] readDatabaseLimits1And2(){
        int lim [] = {50, 126, 501, 751, 1001};
        return lim;
    }

    public static double [] readDatabaseLimits3(){
        double lim [] = {2.0, 5.01, 20.01, 50.01};
        return lim;
    }

    public static int [] readDatabaseLimits4And5(){
        int lim [] = {201, 301, 501, 801, 1201};
        return lim;
    }

    public static double [] readDatabaseLimits6(){
        double lim [] = {100.0, 150.01, 300.01};
        return lim;
    }

    public static HashMap<Integer, ArrayList<Double>> readDatabase1(){
        HashMap<Integer, ArrayList<Double>> database1 = new HashMap<>();

        ArrayList<Double> row1 = new ArrayList<>();
        row1.add(0.5); row1.add(0.75); row1.add(1.0); row1.add(1.1); row1.add(1.2);
        database1.put(11, row1);
        database1.put(12, row1);

        ArrayList<Double> row2 = new ArrayList<>();
        row2.add(1.25); row2.add(2.75); row2.add(3.0); row2.add(3.25); row2.add(3.5);
        database1.put(13, row2);
        database1.put(14, row2);

        ArrayList<Double> row3 = new ArrayList<>();
        row3.add(2.5); row3.add(2.6); row3.add(2.7); row3.add(3.95); row3.add(4.05);
        database1.put(15, row3);

        ArrayList<Double> row4 = new ArrayList<>();
        row4.add(5.0); row4.add(5.9); row4.add(6.3); row4.add(8.1); row4.add(10.3);
        database1.put(16, row4);

        ArrayList<Double> row5 = new ArrayList<>();
        row5.add(2.45); row5.add(3.2); row5.add(3.8); row5.add(5.6); row5.add(8.7);
        database1.put(17, row5);
        database1.put(18, row5);

        return database1;
    }

    public static HashMap<Integer, ArrayList<Integer>> readDatabase2(){
        HashMap<Integer, ArrayList<Integer>> database2 = new HashMap<>();

        ArrayList<Integer> row1 = new ArrayList<>();
        row1.add(5); row1.add(6); row1.add(6); row1.add(9); row1.add(12);
        database2.put(11, row1);
        database2.put(12, row1);

        ArrayList<Integer> row2 = new ArrayList<>();
        row2.add(3); row2.add(5); row2.add(8); row2.add(12); row2.add(13);
        database2.put(13, row2);
        database2.put(14, row2);

        ArrayList<Integer> row3 = new ArrayList<>();
        row3.add(5); row3.add(11); row3.add(16); row3.add(21); row3.add(30);
        database2.put(15, row3);

        ArrayList<Integer> row4 = new ArrayList<>();
        row4.add(10); row4.add(12); row4.add(14); row4.add(19); row4.add(23);
        database2.put(16, row4);

        ArrayList<Integer> row5 = new ArrayList<>();
        row5.add(13); row5.add(15); row5.add(30); row5.add(32); row5.add(33);
        database2.put(17, row5);
        database2.put(18, row5);

        return database2;
    }

    public static HashMap<Integer, ArrayList<Integer>> readDatabase3(){
        HashMap<Integer, ArrayList<Integer>> database3 = new HashMap<>();

        ArrayList<Integer> row1 = new ArrayList<>();
        row1.add(13); row1.add(15); row1.add(32); row1.add(30);
        database3.put(11, row1);
        database3.put(12, row1);

        ArrayList<Integer> row2 = new ArrayList<>();
        row2.add(10); row2.add(12); row2.add(19); row2.add(14);
        database3.put(13, row2);
        database3.put(14, row2);

        ArrayList<Integer> row3 = new ArrayList<>();
        row3.add(5); row3.add(11); row3.add(21); row3.add(23);
        database3.put(15, row3);

        ArrayList<Integer> row4 = new ArrayList<>();
        row4.add(3); row4.add(5); row4.add(12); row4.add(33);
        database3.put(16, row4);

        ArrayList<Integer> row5 = new ArrayList<>();
        row5.add(5); row5.add(6); row5.add(9); row5.add(12);
        database3.put(17, row5);
        database3.put(18, row5);

        return database3;
    }

    public static HashMap<Integer, ArrayList<Double>> readDatabase4(){
        HashMap<Integer, ArrayList<Double>> database4 = new HashMap<>();

        ArrayList<Double> row1 = new ArrayList<>();
        row1.add(10.0); row1.add(15.0); row1.add(20.0); row1.add(22.5); row1.add(25.0);
        database4.put(19, row1);

        ArrayList<Double> row2 = new ArrayList<>();
        row2.add(5.0); row2.add(6.0); row2.add(8.0); row2.add(11.0); row2.add(12.5);
        database4.put(20, row2);

        ArrayList<Double> row3 = new ArrayList<>();
        row3.add(20.0); row3.add(22.75); row3.add(30.0); row3.add(32.5); row3.add(50.0);
        database4.put(21, row3);

        return database4;
    }

    public static HashMap<Integer, ArrayList<Integer>> readDatabase5(){
        HashMap<Integer, ArrayList<Integer>> database5 = new HashMap<>();

        ArrayList<Integer> row1 = new ArrayList<>();
        row1.add(20); row1.add(30); row1.add(40); row1.add(45); row1.add(50);
        database5.put(19, row1);

        ArrayList<Integer> row2 = new ArrayList<>();
        row2.add(2); row2.add(3); row2.add(4); row2.add(5); row2.add(6);
        database5.put(20, row2);

        ArrayList<Integer> row3 = new ArrayList<>();
        row3.add(20); row3.add(23); row3.add(30); row3.add(33); row3.add(50);
        database5.put(21, row3);

        return database5;
    }

    public static HashMap<Integer, ArrayList<Integer>> readDatabase6(){
        HashMap<Integer, ArrayList<Integer>> database6 = new HashMap<>();

        ArrayList<Integer> row1 = new ArrayList<>();
        row1.add(5); row1.add(6); row1.add(9);
        database6.put(19, row1);

        ArrayList<Integer> row2 = new ArrayList<>();
        row2.add(3); row2.add(5); row2.add(12);
        database6.put(20, row2);

        ArrayList<Integer> row3 = new ArrayList<>();
        row3.add(5); row3.add(11); row3.add(21);
        database6.put(21, row3);

        return database6;
    }

    public static HashMap<Integer, String> readDatabase7Names(){
        HashMap<Integer, String> database7Names = new HashMap<>();
        database7Names.clear();
        database7Names.put(1101, "padaria1"); database7Names.put(1102, "padaria2"); database7Names.put(1103, "padaria3"); database7Names.put(1104, "padaria4"); database7Names.put(1105, "padaria5");
        database7Names.put(1201, "pastelaria1"); database7Names.put(1202, "pastelaria2"); database7Names.put(1203, "pastelaria3"); database7Names.put(1204, "pastelaria4"); database7Names.put(1205, "pastelaria5");
        database7Names.put(1301, "charcutaria1"); database7Names.put(1302, "charcutaria2"); database7Names.put(1303, "charcutaria3"); database7Names.put(1304, "charcutaria4"); database7Names.put(1305, "charcutaria5");
        database7Names.put(1401, "queijo1"); database7Names.put(1402, "queijo2"); database7Names.put(1403, "queijo3"); database7Names.put(1404, "queijo4"); database7Names.put(1405, "queijo5");
        database7Names.put(1501, "peixaria1"); database7Names.put(1502, "peixaria2"); database7Names.put(1503, "peixaria3"); database7Names.put(1504, "peixaria4"); database7Names.put(1505, "peixaria5");
        database7Names.put(1601, "talho1"); database7Names.put(1602, "talho2"); database7Names.put(1603, "talho3"); database7Names.put(1604, "talho4"); database7Names.put(1605, "talho5");
        database7Names.put(1701, "fruta1"); database7Names.put(1702, "fruta2"); database7Names.put(1703, "fruta3"); database7Names.put(1704, "fruta4"); database7Names.put(1705, "fruta5");
        database7Names.put(1801, "legume1"); database7Names.put(1802, "legume2"); database7Names.put(1803, "legume3"); database7Names.put(1804, "legume4"); database7Names.put(1805, "legume5");
        return database7Names;
    }

    public static HashMap<Integer, Double> readDatabase7Prices(){
        HashMap<Integer, Double> database7Prices = new HashMap<>();
        database7Prices.clear();
        database7Prices.put(1101, 0.10);database7Prices.put(1102, 0.30);database7Prices.put(1103, 0.70);database7Prices.put(1104, 1.00); database7Prices.put(1105, 3.50);
        database7Prices.put(1201, 1.25); database7Prices.put(1202, 35.55); database7Prices.put(1203, 4.00); database7Prices.put(1204, 5.50); database7Prices.put(1205, 10.00);
        database7Prices.put(1301, 0.75); database7Prices.put(1302, 10.00); database7Prices.put(1303, 12.00); database7Prices.put(1304, 8.00); database7Prices.put(1305, 4.75);
        database7Prices.put(1401, 1.05); database7Prices.put(1402, 2.00); database7Prices.put(1403, 4.00); database7Prices.put(1404, 5.00); database7Prices.put(1405, 9.00);
        database7Prices.put(1501, 20.00); database7Prices.put(1502, 25.00); database7Prices.put(1503, 22.50); database7Prices.put(1504, 30.00); database7Prices.put(1505, 32.75);
        database7Prices.put(1601, 15.75); database7Prices.put(1602, 16.75); database7Prices.put(1603, 26.75); database7Prices.put(1604, 36.25); database7Prices.put(1605, 65.50);
        database7Prices.put(1701, 3.00); database7Prices.put(1702, 1.50); database7Prices.put(1703, 2.00); database7Prices.put(1704, 2.50); database7Prices.put(1705, 1.00);
        database7Prices.put(1801, 0.25); database7Prices.put(1802, 3.00); database7Prices.put(1803, 2.25); database7Prices.put(1804, 3.25); database7Prices.put(1805, 1.75);
        return database7Prices;
    }

    public static HashMap<Integer, String> readDatabase8Names(){
        HashMap<Integer, String> database8Names = new HashMap<>();
        database8Names.clear();
        database8Names.put(1901, "trocaPneu1"); database8Names.put(1902, "trocaPneu2"); database8Names.put(1903, "trocaPneu3"); database8Names.put(1904, "trocaPneu4"); database8Names.put(1905, "trocaPneu5");
        database8Names.put(2001, "mudancaOleo1"); database8Names.put(2002, "mudancaOleo2"); database8Names.put(2003, "mudancaOleo3"); database8Names.put(2004, "mudancaOleo4"); database8Names.put(2005, "mudancaOleo5");
        database8Names.put(2101, "mudancaTravoes1"); database8Names.put(2102, "mudancaTravoes2"); database8Names.put(2103, "mudancaTravoes3"); database8Names.put(2104, "mudancaTravoes4"); database8Names.put(2105, "mudancaTravoes5");
        return database8Names;
    }

    public static HashMap<Integer, Double> readDatabase8Prices(){
        HashMap<Integer, Double> database8Prices = new HashMap<>();
        database8Prices.clear();
        database8Prices.put(1901, 200.00); database8Prices.put(1902, 150.0); database8Prices.put(1903, 100.75); database8Prices.put(1904, 50.50); database8Prices.put(1905, 20.15);
        database8Prices.put(2001, 300.0); database8Prices.put(2002, 400.0); database8Prices.put(2003, 125.50); database8Prices.put(2004, 350.75); database8Prices.put(2005, 255.55);
        database8Prices.put(2101, 90.00); database8Prices.put(2102, 10.00); database8Prices.put(2103, 25.90); database8Prices.put(2104, 120.50); database8Prices.put(2105, 100.00);
        return database8Prices;
    }

    public static HashMap<Integer, ArrayList<String>> readDatabaseCard(){
        HashMap<Integer, ArrayList<String>> databaseLoyaltyCard = new HashMap<>();

        ArrayList<String> listCard1 = new ArrayList<>();
        listCard1.add("Barack");   // 0 - name
        listCard1.add("Obama");    // 1 - surname
        listCard1.add("657657657");// 2 - Tax Identification Numbers - TIN - NIF
        listCard1.add("4");        // 3 - birthday day
        listCard1.add("8");        // 4 - birthday month
        listCard1.add("1961");     // 5 - birthday year
        listCard1.add("0");        // 6 - points
        listCard1.add("1");        // 7 - active / inactive
        databaseLoyaltyCard.put(100, listCard1);

        ArrayList<String> listCard2 = new ArrayList<>();
        listCard2.add("Angela"); listCard2.add("Merkel"); listCard2.add("987987987"); listCard2.add("17"); listCard2.add("7"); listCard2.add("1954"); listCard2.add("400"); listCard2.add("1");
        databaseLoyaltyCard.put(200, listCard2);

        ArrayList<String> listCard3 = new ArrayList<>();
        listCard3.add("Emmanuel"); listCard3.add("Macron"); listCard3.add("321321321"); listCard3.add("21"); listCard3.add("12"); listCard3.add("1977"); listCard3.add("300"); listCard3.add("1");
        databaseLoyaltyCard.put(300, listCard3);

        ArrayList<String> listCard4 = new ArrayList<>();
        listCard4.add("Marcelo"); listCard4.add("Rebelo"); listCard4.add("123123123"); listCard4.add("12"); listCard4.add("12"); listCard4.add("1948"); listCard4.add("200"); listCard4.add("1");
        databaseLoyaltyCard.put(400, listCard4);

        ArrayList<String> listCard7 = new ArrayList<>();
        listCard7.add("Cristiano"); listCard7.add("Ronaldo"); listCard7.add("123456789"); listCard7.add("5"); listCard7.add("2"); listCard7.add("1985"); listCard7.add("1001"); listCard7.add("1");
        databaseLoyaltyCard.put(7, listCard7);

        return databaseLoyaltyCard;
    }
}
