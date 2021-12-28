package com.example.etners;

import java.util.Comparator;

public class TimeAscending implements Comparator<Timememo> {        //어레이리스트 정렬 시키는 클래스 comparator 임플리먼트 해야함.
    @Override

    public int compare(Timememo t, Timememo m) {

        String ttime=t.getTime().substring(0,2)+t.getTime().substring(3,4);
        String ttime2=m.getTime().substring(0,2)+m.getTime().substring(3,4);

        if(Integer.parseInt(ttime)>Integer.parseInt(ttime2) ){
            return 1;
        }else if(Integer.parseInt(ttime)<Integer.parseInt(ttime2)){
            return  -1;
        }else{
            return  0;
        }



//        if(Integer.parseInt(t.getTime())>Integer.parseInt(m.getTime())) {
//            return 1;
//        }else if(Integer.parseInt(t.getTime())<Integer.parseInt(m.getTime())){
//            return  -1;
//        }else{
//            return  0;
//        }
    }
}
