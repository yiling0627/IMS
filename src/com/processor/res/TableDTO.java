package com.processor.res;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Vector;

public class TableDTO {

    private  Vector<Vector<Object>> data;
    private int totalCount;

    public Vector<Vector<Object>> getData() {
        return data;
    }

    public void setData(Vector<Vector<Object>> data) {
        this.data = data;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }


    public TableDTO sort() {
        TableDTO result = new TableDTO();
        if(data.size() == 0){
            return null;
        }

        for (int i = 0; i < data.size(); i++){
            for(int j = 0; j < data.size() - i - 1; j++) {
                //switch, when A big than B, true
                if (compareDate(data.get(j).get(5).toString(), data.get(j + 1).get(5).toString())) {
                    Collections.swap(data,j,j+1);
                }
            }
        }
        result.setData(data);
        return result;
    }

    public Vector<Integer> getQua(){
        Vector<Integer> result = new Vector<>();
        int qtr1 = 0, qtr2 = 0, qtr3 = 0, qtr4 = 0;
        for (int i = 0; i < data.size(); i++) {
            if (Integer.parseInt(data.get(i).get(0).toString().substring(4,6)) >= 1 && Integer.parseInt(data.get(i).get(0).toString().substring(4,6)) <= 3) {
                qtr1++;
            } else if(Integer.parseInt(data.get(i).get(0).toString().substring(4,6)) >= 4 && Integer.parseInt(data.get(i).get(0).toString().substring(4,6)) <= 6){
                qtr2++;
            } else if(Integer.parseInt(data.get(i).get(0).toString().substring(4,6)) >= 7 && Integer.parseInt(data.get(i).get(0).toString().substring(4,6)) <= 9){
                qtr3++;
            } else if(Integer.parseInt(data.get(i).get(0).toString().substring(4,6)) >= 10 && Integer.parseInt(data.get(i).get(0).toString().substring(4,6)) <= 12){
                qtr4++;
            }
        }
        result.add(qtr1);
        result.add(qtr2);
        result.add(qtr3);
        result.add(qtr4);
        return result;
    }

    public boolean compareDate(String str, String str1){
        if (Integer.parseInt(str.substring(0, 4)) > Integer.parseInt(str1.substring(0, 4))){
            return true;
        } else if (Integer.parseInt(str.substring(0, 4)) < Integer.parseInt(str1.substring(0, 4))){
            return false;
        } else if( Integer.parseInt(str.substring(5, 7)) > Integer.parseInt(str1.substring(5, 7))){
            return true;
        } else if( Integer.parseInt(str.substring(5, 7)) < Integer.parseInt(str1.substring(5, 7))){
            return false;
        } else if( Integer.parseInt(str.substring(8)) > Integer.parseInt(str1.substring(8))){
            return true;
        } else if( Integer.parseInt(str.substring(8)) < Integer.parseInt(str1.substring(8))){
            return false;
        }
        return false;
    }
}
