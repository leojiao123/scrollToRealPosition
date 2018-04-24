package test.zhmf.com;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private GridLayoutManager layoutManager;
    private EditText et_number;
    private Button btn_jump;
    private RecyclerView recyclerView;
    private boolean move;
    private int targetPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ///  LoadingView load_view = (LoadingView) findViewById(R.id.load_view);
        //load_view.start();
        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        TestAdapter testAdapter = new TestAdapter(list);
        recyclerView.setAdapter(testAdapter);

        et_number = findViewById(R.id.et_number);
        btn_jump = findViewById(R.id.btn_jump);
        btn_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String trim = et_number.getText().toString().trim();
                int position = Integer.parseInt(trim);
                moveToPosition(position);
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (move) {
                    move = false;
                    int realPos = targetPosition - layoutManager.findFirstVisibleItemPosition();
                    if ( 0 <= realPos && realPos < recyclerView.getChildCount()){
                        //获取要置顶的项顶部离RecyclerView顶部的距离
                        int top = recyclerView.getChildAt(realPos).getTop();
                        //最后的移动
                        recyclerView.scrollBy(0, top);
                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }
        });
    }


    private void moveToPosition(int position) {
        targetPosition = position;

        int firstPosition = layoutManager.findFirstVisibleItemPosition();
        int lastPosition = layoutManager.findLastVisibleItemPosition();
        //先从RecyclerView的LayoutManager中获取第一项和最后一项的Position
        //然后区分情况
        if (position <= firstPosition) { //
            //当要置顶的项在当前显示的第一个项的前面
            recyclerView.scrollToPosition(position);
        } else if (position <= lastPosition) {
//            //当要置顶的项已经在屏幕上显示时
            recyclerView.scrollToPosition(position);
            int top = layoutManager.getChildAt(position - firstPosition).getTop();
            recyclerView.scrollBy(0, top);
        } else {
            //当要置顶的项在当前显示的最后一项的后面时
            recyclerView.scrollToPosition(position); //
            move = true;
            //这里这个变量是用在RecyclerView滚动监听里面的
        }

    }

}