package com.example.etners;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class SubActivity extends AppCompatActivity {

    ArrayList<Data> arrayList;
    MainAdapter mainAdapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;

    public Button btn_go1;
    public Button btn_del;
    public EditText et_name1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        btn_go1=findViewById(R.id.btn_go1);
        btn_del=findViewById(R.id.btn_del);
        et_name1=findViewById(R.id.et_name1);

        recyclerView=findViewById(R.id.recyclerview);
        arrayList= new ArrayList<>();



        linearLayoutManager = new LinearLayoutManager(SubActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        mainAdapter = new MainAdapter(arrayList);
        recyclerView.setAdapter(mainAdapter);


        btn_go1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(et_name1.getText().toString().contains("연구")) {
                    Data data1 = new Data("연구동", "동편", "3층", "ㅅㅅㅇㅇ", "ㅇㄴㄹ", "입구에서 왼쪽열 끝까지(임원석)");
                    Data data2 = new Data("연구동", "동편", "3층", "ㄱㅁㅌ", "ㅇㅇㅇ", "입구앞 오른쪽열(끝)");
                    Data data3 = new Data("연구동", "서편", "3층", "ㅇㅅㅌ", "ㅎㅇㅈ", "입구에서 오른쪽열 중간");
                    Data data4 = new Data("연구동", "서편", "3층", "ㅈㅇㅌ", "ㅎㅅㅈ", "입구쪽 왼쪽열 끝");
                    Data data5 = new Data("연구동", "서편", "3층", "ㅈㄱㅌ", "담당자없음", "중간재경그룸 수신함에 넣음");
                    Data data6 = new Data("연구동", "서편", "2층", "ㅅㄹ", "ㅇㅎㅇ", "입구에서 왼쪽열 중간(화재 스티커)");
                    Data data7 = new Data("연구동", "서편", "2층", "ㄷㄹ", "담당자 없음", "입구쪽 수신함에 넣어줌");
                    Data data8 = new Data("연구동", "동편", "2층", "ㅌㅅ", "ㅂㅇㅇ", "왼쪽열 끝까지(임원석)");
                    Data data9 = new Data("연구동", "동편", "2층", "ㅍㄹ", "ㄱㅌㅎ", "오른쪽열 중간(파란 듀얼모니터)");
                    Data data10 = new Data("연구동", "동편", "1층", "ㅍㅈ", "ㄱㅎㄹ", "입구쪽 오른쪽열 바로앞 ");
                    Data data11 = new Data("연구동", "서편", "1층", "ㅍㄱ", "ㅂㅅㅂ", "우측열 출구쪽(임원석)");



                    arrayList.add(data1);
                    arrayList.add(data2);
                    arrayList.add(data3);
                    arrayList.add(data4);
                    arrayList.add(data5);
                    arrayList.add(data6);
                    arrayList.add(data7);
                    arrayList.add(data8);
                    arrayList.add(data9);
                    arrayList.add(data10);
                    arrayList.add(data11);


                    mainAdapter.notifyDataSetChanged();


                }else if(et_name1.getText().toString().contains("C라")||et_name1.getText().toString().contains("c라")) {
                    Data data21 = new Data("C라인", "", "2층", " ㅎㅇ", "ㄱㄴㄹ", "박재성 팀장님앞(문바로옆)");
                    Data data22 = new Data("C라인", "", "2층", "ㅈㅈ", "ㄱㅇㅇ", "임정웅님 뒷자리");
                    Data data23 = new Data("C라인", "", "2층", "ㅇㅅ", "ㅅㅇㅈ", "손소독제 바로뒷자리");

                    arrayList.add(data21);
                    arrayList.add(data22);
                    arrayList.add(data23);

                    mainAdapter.notifyDataSetChanged();

                }else if(et_name1.getText().toString().contains("체리")){

                    Data data31 = new Data("체리홀", "", "3층", "ㅅㅇ", "ㄱㅎㅁ", "입구쪽 오른쪽열 (책상위 작은냉장고");
                    Data data32 = new Data("체리홀", "", "3층", "", "ㅇㅎㅈ", "오른쪽열 중간쯤");
                    Data data33 = new Data("체리홀", "", "3층", "ㅍㄱ", "ㅇㅈㅇ", "우측열 임원실앞");
                    Data data34 = new Data("체리홀", "", "2층", "ㅅㅇ", "ㅈㄱㅍ", "우측열 출구쪽 모니터옆(남자분)");

                    arrayList.add(data31);
                    arrayList.add(data32);
                    arrayList.add(data33);
                    arrayList.add(data34);

                    mainAdapter.notifyDataSetChanged();

                }else  if(et_name1.getText().toString().contains("4라")){

                    Data data41 = new Data("4라인", "", "6층", "ㅈㅎ", "ㅇㅇㅇ", "출구쪽 오른쪽열 복도쪽맨끝(기술혁신팀옆자리)");
                    Data data42 = new Data("4라인", "", "6층", "ㄳ", "ㅂㅇㅈ", "출구쪽 오른쪽열 복도쪽맨끝(제조혁신팀옆자리");
                    Data data43 = new Data("4라인", "", "5층", "ㅇㅇ", "ㅅㅁㅈ", "우측열 중간쯤");
                    Data data44 = new Data("4라인", "", "4층", "ㅇㅁ(4층)", "ㅇㅁ", "오른쪽옆 중간쯤(임원실앞)");
                    Data data45 = new Data("4라인", "", "3층", "ㅇㅁ(3층)", "ㅇㅇㄱ", "오른쪽열 중간쯤");
                    Data data46 = new Data("4라인", "", "1층", "ㅇㅁ(1층)", "ㅇㅇㅎ", "오른쪽열 중간쯤");

                    arrayList.add(data41);
                    arrayList.add(data42);
                    arrayList.add(data43);
                    arrayList.add(data44);
                    arrayList.add(data45);
                    arrayList.add(data46);

                    mainAdapter.notifyDataSetChanged();

                }else if(et_name1.getText().toString().contains("1라")){

                    Data data51 = new Data("1라인", "", "3층", "ㅈㅈ1", "ㅇㅇㅅ", "입구쪽 왼쪽 라인 구석");
                    Data data52 = new Data("1라인", "", "3층", "ㅈㅈ2", "ㅇㅁㅈ", "이은숙님 뒷편 자리");
                    Data data53 = new Data("1라인", "", "3층", "ㅈㅈ11", "ㅊㅇㅇ", "출구쪽 왼편라인");
                    Data data54 = new Data("1라인", "", "2층", "ㅈㅈ22", "ㅈㅎㅈ", "입구쪽 오른쪽 구석");
                    Data data55 = new Data("1라인", "", "2층", "ㅈㅈ66", "ㅎㅈㅇ", "중간쪽 구석(발신함 라인근처)");
                    Data data56 = new Data("1라인", "", "2층", "ㅈㅈ77", "ㅅㅁㅎ", "휴게실넘어서 구석자리");


                    arrayList.add(data51);
                    arrayList.add(data52);
                    arrayList.add(data53);
                    arrayList.add(data54);
                    arrayList.add(data55);
                    arrayList.add(data56);

                    mainAdapter.notifyDataSetChanged();



                }else if(et_name1.getText().toString().contains("2라")){
                    Data data61 = new Data("2라인", "", "3층", "ㅇㅅㅇ", "담당자x", "");

                    arrayList.add(data61);
                    mainAdapter.notifyDataSetChanged();
                }else if(et_name1.getText().toString().contains("3라")){
                    Data data71 = new Data("3라인", "", "2층", "ㅌㅅㅌ", "ㅎㅈ", "입구쪽 오른쪽 끝");
                    Data data72 = new Data("3라인", "", "2층", "ㅈㅈㄱ", "ㅇㅈㅁ", "중간쪽 끝자리(발신함 라인");
                    Data data73 = new Data("3라인", "", "2층", "ㅈㅈㄱ2", "ㅅㅎㅁ,ㅇㅁㅎ", "출구쪽 맨끝자리, 담당자2분임");

                    arrayList.add(data71);
                    arrayList.add(data72);
                    arrayList.add(data73);
                    mainAdapter.notifyDataSetChanged();

                }else{
                    et_name1.setText("수정중입니다.");
                }

                et_name1.setText("");

            }
        });

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                arrayList.clear();
                mainAdapter.notifyDataSetChanged();

            }
        });

    }
}