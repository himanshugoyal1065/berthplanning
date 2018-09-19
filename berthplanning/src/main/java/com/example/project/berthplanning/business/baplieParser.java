package com.example.project.berthplanning.business;

import com.example.project.berthplanning.model.container;

import java.io.File;
import java.util.*;

public class baplieParser {
    public File file;
    public String BAPLIE_EQUIPMENT;
    int count,reefer,dry;

    public baplieParser()
    {
        this.count=0;
        this.reefer=0;
        this.dry=0;
    }
    public String getDate()
    {
        String word;
        /*Date currentDate = new Date();
        Date getDatevar = new Date();
        Calendar mydate = new GregorianCalendar();*/
        String mydate="There is not Estimated time of Arrival";
        String[] getDTD;
        try{
            Scanner sc = new Scanner(file);
            while((word=sc.nextLine())!=null)
            {
                if(word.substring(0,7).equals("DTM+133"))
                {
                    //System.out.println("HERE1");
                    getDTD = word.split(":");
                    if(getDTD[2].substring(0,3).equals("201"))
                    {
                       System.out.println(getDTD[1]);
                        /*mydate.set(Calendar.YEAR,2000+Integer.parseInt(getDTD[1].substring(0,2)));
                        mydate.set(Calendar.MONTH,Integer.parseInt(getDTD[1].substring(2,4)));
                        mydate.set(Calendar.DATE,Integer.parseInt(getDTD[1].substring(4,6)));
                        mydate.set(Calendar.HOUR,Integer.parseInt(getDTD[1].substring(6,8)));
                        mydate.set(Calendar.MINUTE,Integer.parseInt(getDTD[1].substring(8,10)));*/
                       // System.out.println("HERE");
                       // System.out.println(mydate);
                        mydate = getDTD[1];
                    }
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("");
        }
        return mydate;
    }
    public Map<container,String> getAll()
    {
        Map<container,String> mappings = new HashMap<>();
        String word,key="";
        //int count=0;
        int flag=0;
        count=0;
        reefer=0;
        dry=0;
        try
        {
            Scanner sc = new Scanner(file);
            while((word = sc.nextLine())!=null)
            {
                if(word.substring(0,3).equals("LOC"))
                {
                    if(word.substring(4,6).equals("11") || word.substring(4,6).equals("12")) {
                        String[] setLoc = word.split("\\+");
                        int n = setLoc[2].length();
                        key = setLoc[2].substring(0,n-2);
                        word = sc.nextLine();
                        while (!word.substring(0, 3).equals("EQD")) {
                            word = sc.nextLine();
                        }
                        String[] setEqu = word.split("\\+");
                        if (setEqu[1].equals(BAPLIE_EQUIPMENT)) {
                            count++;
                            if(setEqu[3].substring(2,4).equals("30"))
                                reefer++;
                            else
                                dry++;
                            container cont = new container();
                            cont.setcId(setEqu[2]);
                            mappings.put(cont, key);
                        }
                    }
                }
            }

        }
        catch(Exception e)
        {
            System.out.println();
        }
        return mappings;
    }

    public List<container> getContainers(String PORT_OF_DISCHARGE)
    {
        List<container> containers = new ArrayList<>();
        String word;
        String EQD[];
        count=0;
        dry=0;
        reefer=0;
        int flag=0;
        try
        {
            Scanner sc = new Scanner(file);
            while((word=sc.nextLine())!=null)
            {
                if(word.substring(0,3).equals("LOC"))
                {
                    // System.out.println("Here");
                    if(word.substring(4,7).equals("147"))
                    {
                        // System.out.println("Here again");
                        word = sc.nextLine();
                        while(!word.substring(0,3).equals("EQD"))
                        {
                            //System.out.println(word);
                            if(word.substring(0,3).equals("LOC"))
                            {
                                if(word.substring(4,6).equals("11") || word.substring(4,6).equals("12"))
                                {
                                    if(word.substring(7,12).equals(PORT_OF_DISCHARGE))
                                        flag=1;
                                    else
                                        flag=0;
                                }
                            }
                            word=sc.nextLine();
                        }
                        if(word.substring(4,6).equals(BAPLIE_EQUIPMENT) && flag==1)
                        {
                            count++;
                            EQD = word.split("\\+");
                            if(EQD[3].substring(2,4).equals("30"))
                                reefer++;
                            else
                                dry++;
                            container cont = new container();
                            cont.setcId(word.substring(7,18));
                            containers.add(cont);
                            flag=0;
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println();
        }
        return containers;
    }
    public Map<String,Integer> getCount()
    {
        this.getAll();
        Map<String,Integer> totalCount = new HashMap<String,Integer>();
        totalCount.put("Total",count);
        totalCount.put("Dry",dry);
        totalCount.put("Refeer",reefer);
        return totalCount;
    }
    public Map<String,Integer> getPortCount(String POD)
    {
        this.getContainers(POD);
        Map<String,Integer> mappings = new HashMap<>();
        mappings.put("Total",count);
        mappings.put("Dry",dry);
        mappings.put("Reefer",reefer);
        return mappings;

    }
}
