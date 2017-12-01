package tw.idv.jameschang.baseadapterdemo;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = DataTask.class.getSimpleName();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        listView = findViewById(R.id.listView);

        new DataTask().execute("https://dickfala.github.io/test.html");
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

    class DataTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            StringBuilder stringBuilder = new StringBuilder();

            try {

                URL url = new URL(strings[0]);
                InputStream inputStream = url.openStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

                String data = in.readLine();
                while (data != null) {
                    stringBuilder.append(data);
                    data = in.readLine();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d(TAG, "Json:" + s);
            parseJson(s);
        }

        private void parseJson(String result) {

            Gson gson = new Gson();
            ArrayList<UserDataModel> list = gson.fromJson(result,
                    new TypeToken<ArrayList<UserDataModel>>() {
                    }.getType());
            Log.d(TAG, "parseJson: list size:" + list.size() +
                    "\n first name :" + list.get(1).getAccount());

            DataAdapter adapter = new DataAdapter(getApplicationContext(), list);
            listView.setAdapter(adapter);
        }

    }


    class DataAdapter extends BaseAdapter {

        public ArrayList<UserDataModel> userDataModels;
        private Context context;

        public DataAdapter(Context ctx, ArrayList<UserDataModel> userDataModels) {
            this.userDataModels = userDataModels;
            this.context = ctx;

        }

        @Override
        public int getCount() {
            return userDataModels.size();
        }

        @Override
        public Object getItem(int position) {
            return userDataModels.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = convertView;
            if (convertView == null) {
                view = getLayoutInflater().inflate(R.layout.row_item, null);
            }
            TextView tvName = view.findViewById(R.id.tv_name);
            TextView tvSport = view.findViewById(R.id.tv_sport);
            TextView tvAge = view.findViewById(R.id.tv_age);
            TextView tvSex = view.findViewById(R.id.tv_sex);

            tvName.setText(userDataModels.get(position).getAccount());
            tvSport.setText(userDataModels.get(position).getSport());
            int age =  userDataModels.get(position).getAge();
            tvAge.setText( Integer.toString(age));
            tvSex.setText(userDataModels.get(position).getSex());

            return view;
        }
    }
}
