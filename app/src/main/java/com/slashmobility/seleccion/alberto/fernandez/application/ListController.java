package com.slashmobility.seleccion.alberto.fernandez.application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListController {

    private List <Integer> listNums;

    public ListController(){
        listNums = new ArrayList<>();
    }

    public List<Integer> getListNums() {
        return listNums;
    }

    public void delNumList(int pos){
        listNums.remove(pos);
    }

    public void clearList(){
        listNums.clear();
    }

    //Ordena los elementos de la lista
    public List<Integer> getSortAscList(){
        Collections.sort(listNums);
        return listNums;
    }

    //Añade tantos números como el parámetro indica
    public void addNumToList( int addnums){
        for (int i=0;i<addnums;i++) {
            listNums.add(randomNumber());
        }
    }

    //Genera un número aleatorio entre 1 y 1000
    public int randomNumber(){
        return (int) (Math.random() * 999) + 1;
    }
}
