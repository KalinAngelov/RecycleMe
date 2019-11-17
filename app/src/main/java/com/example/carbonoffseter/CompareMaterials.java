package com.example.carbonoffseter;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CompareMaterials {

    private static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    public static String getStringFromFile (String filePath) throws Exception {
        File fl = new File(filePath);
        FileInputStream fin = new FileInputStream(fl);
        String ret = convertStreamToString(fin);
        fin.close();
        return ret;
    }

    public static String getMatching(String labels)
    {
        Collection listTwo = new ArrayList<String>(Arrays.asList(labels.toLowerCase().split("\n", -1)));
        String[] brownList = {"plastic bottle", "milk bottle", "soda bottle",
                "washing up liquid",
                "shampoo",
                "mouth wash",
                "lotion", "sun tan lotion",
                "bleach",
                "tin", "food tin",
                "tin can", "aluminum can",
                "can", "drink can",
                "foil", "tin foil", "foil tray",
                "aerosols",
                "glass"};
        String[] blueList = {"cardboard",
                "box",
                "carton",
                "magazine",
                "envelope",
                "catalogue",
                "receipt",
                "flier",
                "leaflet",
                "pamphlet",
                "greeting card",
                "tag",
                "roll"};
        String[] greenList = {"food",
                "bones",
                "tea bag", "tea",
                "twig",
                "branch",
                "flower",
                "grass",
                "hedge",
                "leaf", "leaves",
                "weed"};

        int maxIndex = -1;
        String name = "general waste";
        Collection listOne = new ArrayList<>(Arrays.asList(brownList));
        listOne.retainAll(listTwo);
        if(listOne.size() > 0)
            for (Object o : listOne) {
                int index = ((ArrayList) listTwo).indexOf(o);
                if(maxIndex < 0 || maxIndex > index)
                {
                    maxIndex = index;
                    name = "brown";
                }
            }

        listOne = new ArrayList<>(Arrays.asList(blueList));
        listOne.retainAll(listTwo);
        if(listOne.size() > 0)
            for (Object o : listOne) {
                int index = ((ArrayList) listTwo).indexOf(o);
                if(maxIndex < 0 || maxIndex > index)
                {
                    maxIndex = index;
                    name = "blue";
                }
            }
        listOne = new ArrayList<>(Arrays.asList(greenList));
        listOne.retainAll(listTwo);
        if(listOne.size() > 0)
            for (Object o : listOne) {
                int index = ((ArrayList) listTwo).indexOf(o);
                if(maxIndex < 0 || maxIndex > index)
                {
                    maxIndex = index;
                    name = "green";
                }
            }
        return ((ArrayList) listTwo).get(maxIndex != -1 ? maxIndex : 0) + " goes to " + name + " bin.";
    }

    private class Material
    {
        private List<String> brown;
        private List<String> blue;
        private List<String> green;



    }


}