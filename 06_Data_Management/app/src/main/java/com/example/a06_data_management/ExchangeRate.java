package com.example.a06_data_management;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpPost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.HttpClientBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ExchangeRate extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_rate);
        Button btn = findViewById(R.id.AddItem);
        Button bPrevious = findViewById(R.id.PreviousPage);
        TextView info = findViewById(R.id.info);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        HttpPost httpPost = new HttpPost("http://www.cbr.ru/scripts/XML_daily.asp");
        HttpClient client = HttpClientBuilder.create().build();
        String result = "";
        Document document = null;
        try {
            HttpResponse response = client.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                InputStream inputStream = response.getEntity().getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "windows-1251"));
                String line;
                while ((line = reader.readLine()) != null){
                    result += line;
                }
                result = result.substring(45);
                document = loadXMLFromString(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        NodeList nameList = document.getElementsByTagName("Name");
        NodeList valueList = document.getElementsByTagName("Value");
        ArrayList Names = new ArrayList<String>();
        ArrayList Values = new ArrayList<String>();

        for (int i = 0; i < nameList.getLength(); i++) {
            Names.add(nameList.item(i).getTextContent());
            Values.add(valueList.item(i).getTextContent());
        }
//
        ListView listView = findViewById(R.id.listView);
        ListAdapter adapter = new ListAdapter(this, R.layout.list_items_exchange_rate, Names, Values);
        listView.setAdapter(adapter);

        Button bNext = findViewById(R.id.NextPage);
        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExchangeRate.this, NoteList.class);
                startActivity(intent);
            }
        });
        bPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExchangeRate.this, SquaresList.class);
                startActivity(intent);
            }
        });
    }

    private class ListAdapter extends ArrayAdapter {

        private int resourceLayout;
        private Context mContext;
        private ArrayList<String> Names;
        private ArrayList<String> Values;


        public ListAdapter(Context context, int resource, ArrayList<String> _Names, ArrayList<String> _Values) {
            super(context, resource, _Names);
            this.resourceLayout = resource;
            this.mContext = context;
            this.Names = _Names;
            this.Values = _Values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(mContext);
                v = vi.inflate(resourceLayout, null);
            }
            TextView Name = v.findViewById(R.id.Name);
            TextView Value = v.findViewById(R.id.Value);

            Name.setText(String.valueOf(Names.get(position)));
            Value.setText(String.valueOf(Values.get(position)));

            return v;
        }
    }

    public static Document loadXMLFromString(String xml) throws Exception
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        return builder.parse(is);
    }

    public void Http() {
        OkHttpClient client = new OkHttpClient();
        String url = "https://stackoverflow.com/questions/8418868/how-to-know-when-an-activity-finishes-a-layout-pass";
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String myResponse = response.body().string();

                    ExchangeRate.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //btn.setText(myResponse);
                        }
                    });
                }
            }
        });
    }

}
