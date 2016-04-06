package com.example.daniel.techtest1;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    float posx=0,posy=0;
    ImageView img;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.img1).setOnLongClickListener(new MyOnLongClickListener(false));
        findViewById(R.id.img2).setOnLongClickListener(new MyOnLongClickListener(false));
        findViewById(R.id.img3).setOnLongClickListener(new MyOnLongClickListener(false));
        findViewById(R.id.img4).setOnLongClickListener(new MyOnLongClickListener(false));
        findViewById(R.id.img5).setOnLongClickListener(new MyOnLongClickListener(false));
        findViewById(R.id.img6).setOnLongClickListener(new MyOnLongClickListener(false));

        findViewById(R.id.top).setOnDragListener(new MyOnDragListener(1, false));
        findViewById(R.id.top2).setOnDragListener(new MyOnDragListener(2, false));
        findViewById(R.id.top3).setOnDragListener(new MyOnDragListener(5, false));
        findViewById(R.id.top4).setOnDragListener(new MyOnDragListener(6, false));
        findViewById(R.id.top5).setOnDragListener(new MyOnDragListener(7, false));
        findViewById(R.id.top6).setOnDragListener(new MyOnDragListener(8, false));
        findViewById(R.id.item1).setOnDragListener(new MyOnDragListener(9, false));
        findViewById(R.id.item2).setOnDragListener(new MyOnDragListener(10, false));
        findViewById(R.id.item3).setOnDragListener(new MyOnDragListener(11, false));
        findViewById(R.id.item4).setOnDragListener(new MyOnDragListener(12, false));
        findViewById(R.id.item5).setOnDragListener(new MyOnDragListener(13, false));
        findViewById(R.id.item6).setOnDragListener(new MyOnDragListener(14, false));
        findViewById(R.id.item7).setOnDragListener(new MyOnDragListener(15, false));
        findViewById(R.id.item8).setOnDragListener(new MyOnDragListener(16, false));
        findViewById(R.id.item9).setOnDragListener(new MyOnDragListener(17, false));
        findViewById(R.id.item10).setOnDragListener(new MyOnDragListener(18, false));
        findViewById(R.id.mesa).setOnDragListener(new MyOnDragListener(3,false));
        findViewById(R.id.contenedor).setOnDragListener(new MyOnDragListener(4,false));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class MyOnLongClickListener implements OnLongClickListener{
        boolean visible;
        public MyOnLongClickListener(boolean visible) {
            this.visible=visible;
        }

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
        public boolean onLongClick(View v) {
            img=(ImageView) findViewById(v.getId());
            posx=v.getX(); posy=v.getY(); Log.i("posxy",posx+" "+posy);
            ClipData data = ClipData.newPlainText("simple_text","text");
            View.DragShadowBuilder sb = new View.DragShadowBuilder(v);

            v.startDrag(data, sb, v, 0);
            if(visible==false){
            v.setVisibility(View.INVISIBLE);}else{v.setVisibility(View.VISIBLE);}

            return true;
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    class MyOnDragListener implements View.OnDragListener{
        private int num;
         boolean VerifDrop,dropp=false;
        int ActualNum=0;
        public MyOnDragListener(int num, boolean VerifDrop) {
            super();
            this.num=num;
            this.VerifDrop=VerifDrop;
        }

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();


            switch (action){
                case DragEvent.ACTION_DRAG_STARTED:
                    Log.i("Script",num+" - ACTION_DRAG_STARTED");
                    if(event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)){return true;}else{return false;}

                case DragEvent.ACTION_DRAG_ENTERED:
                    Log.i("Script",num+" - ACTION_DRAG_ENTERED");
                    ActualNum=num;
                    if(num!=3&&num!=4){v.setBackgroundColor(Color.BLUE);}
                    break;
                case DragEvent.ACTION_DRAG_LOCATION:
                    Log.i("Script", num + " - ACTION_DRAG_LOCATION " + event.getX() + " " + event.getY());
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    Log.i("Script",num+" - ACTION_DRAG_EXITED");
                    if(num==4){
                        Log.i("Script", num + " - drop fuera de limite");
                       img.setVisibility(View.VISIBLE);

                    }
                    if(num!=3&&num!=4){v.setBackgroundColor(Color.TRANSPARENT);}
                    break;
                case DragEvent.ACTION_DROP:
                    Log.i("Script",num+" - ACTION_DRAG_DROP");
                    VerifDrop=true;
                    if(num!=3&&num!=4){
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    LinearLayout container = (LinearLayout) v;
                    container.addView(view);
                    view.setVisibility(View.VISIBLE);
                        if(num!=3&&num!=4){v.setBackgroundColor(Color.TRANSPARENT);}
                    }else{
                        img.setVisibility(View.VISIBLE);
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    Log.i("Script",num+" - ACTION_DRAG_ENDED");
                    if(VerifDrop==true&&num!=3){ if(ActualNum==num){Log.i("Script",num+" - buen drop"+ActualNum);VerifDrop=false;dropp=true;}
                    }

                    if(num==3&&VerifDrop==true){Log.i("Script",num+" - drop fuera de limite");}

                    break;
            }
            return true;
        }
    }

}
