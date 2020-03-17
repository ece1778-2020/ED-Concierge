package com.example.edconcierge;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayDeque;
import java.util.Deque;


//Params: 这个泛型指定的是我们传递给异步任务执行时的参数的类型。
//Progress: 这个泛型指定的是我们的异步任务在执行的时候将执行的进度返回给UI线程的参数的类型。
//Result: 这个泛型指定的异步任务执行完后返回给UI线程的结果的类型。我们在定义一个类继承AsyncTask类的时候，必须要指定好这三个泛型的类型，如果都不指定的话，则都将其写成Void

public class NavigationTask extends AsyncTask<TaskParameters, Node,String>{
    int[][] MapMatrix;
    ImageView map;
    int[] current,destination;
    Deque<Node> path;
    Bitmap temp;
    Paint paint;
    Canvas canvas;
    @Override
    protected String doInBackground(TaskParameters... taskParameters) {
        Log.d("NavigationTask","doInbackground");
        //pathfinding
        MapMatrix=taskParameters[0].MapMatrix;
        map=taskParameters[0].map;
        current=taskParameters[0].current;
        destination=taskParameters[0].destination;
        temp=taskParameters[0].temp;
        AStar aStar=new AStar(MapMatrix,current,destination);
        Boolean result=aStar.search();
        System.out.println(result);
        path=new ArrayDeque<>();
        if(result) path=aStar.findpath();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //画图
        paint = new Paint();
        paint.setColor(-65536);
        canvas = new Canvas(temp);
        //起始
        canvas.drawCircle(current[0], current[1], 2, paint);
        //路径
        while(!path.isEmpty()){
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Node curretloc=path.pollLast();
            publishProgress(curretloc);
        }
        //终点
        canvas.drawCircle(destination[0], destination[1], 2, paint);
        return null;
    }

    @Override
    protected void onProgressUpdate(Node... nodes) {
        Log.d("NavigationTask","ProgressUpdate");
        //画图
        //bitmap should be mutable 才可以set
        //location画圆
        canvas.drawCircle(nodes[0].getRow(),nodes[0].getColumn(),1,paint);
        map.setImageBitmap(temp);
        //super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d("NavigationTask","PostExecute");
        map.setImageBitmap(temp);
        super.onPostExecute(s);
    }

    @Override
    protected void onCancelled() {
        Log.d("NavigationTask","onCancelled");
        super.onCancelled();
    }
}
