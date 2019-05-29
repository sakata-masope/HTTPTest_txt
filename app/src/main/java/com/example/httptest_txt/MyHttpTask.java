package com.example.httptest_txt;

import android.os.AsyncTask;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;


public class MyHttpTask extends AsyncTask<String,Void,String> {

    private static final String TAG="MyHttpTAsk";
    private TextView textView;

    public MyHttpTask(Context context)
    {
        super();
        MainActivity mainActivity = (MainActivity)context;
        textView = (TextView)mainActivity.findViewById(R.id.myTextView);

    }

    @Override
    protected java.lang.String doInBackground(java.lang.String... strings) {
        StringBuilder sb = new StringBuilder();

        InputStream inputStream=null;
        HttpURLConnection connection = null;

        try{
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection)url.openConnection();
            connection.setInstanceFollowRedirects(false);
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);

            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode=connection.getResponseCode();
            if(responseCode!=HttpURLConnection.HTTP_OK)
            {
                throw new IOException("HTTP responseCode:"+responseCode);
            }

            inputStream = connection.getInputStream();
            if(inputStream!=null)
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                String line;
                while((line=reader.readLine())!=null)
                {
                    sb.append(line);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(inputStream!=null)
            {
                try{
                    inputStream.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(connection!=null)
            {
                connection.disconnect();
            }
        }
        return sb.toString();
    }

    @Override
    protected void onPostExecute(String result)
    {
        Log.d(TAG,result);
        textView.setText(result);
    }



}
