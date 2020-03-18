package com.example.testconnection;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CheckCallback extends AsyncTask<String, String, ArrayList<HashMap<String, String>>> {
    //Global global = new Global();
    Context context;
    int success;
    String message;
    private JSONParser jsonParser = new JSONParser();
    JSONArray jsonArray = null;
    ArrayList<HashMap<String, String>> arrayListRet;

    public CheckCallback(Context m_context) {
        this.context= m_context;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }


    @Override
    protected ArrayList<HashMap<String, String>> doInBackground(String... params) {

        // Building Parameters
        List<NameValuePair> paramsed = new ArrayList<NameValuePair>();

        Log.d("test : ",params[0]);

        paramsed.add(new BasicNameValuePair("test", params[0])); //nama


        arrayListRet = new ArrayList<>();

        try {
            //call http
            JSONObject json = jsonParser.makeHttpRequest("http://dev.projectlab.co.id/mit/test_conn.php",
                    "POST", paramsed);
            System.out.println("json 2 = "+json.toString());
            //Log.d("CEKIDBOOK_MSG",json.toString());

            success = json.getInt("success");

            message = json.getString("message");


            if (success == 1) {

                HashMap<String, String> map = new HashMap<String, String>();

                map.put("success", String.valueOf(success));
                map.put("message", message);

                arrayListRet.add(map);

            } else {
                //Unsuccessfully picking book. Another driver has taken the order.
                HashMap<String, String> map = new HashMap<String, String>();

                map.put("success", String.valueOf(success));
                map.put("message", message);
                arrayListRet.clear();
                arrayListRet.add(map);

            }
        } catch (Exception error) {
            Log.d("Error Http",error.getMessage());
            HashMap<String, String> map = new HashMap<String, String>();

            map.put("success", "-1");
            map.put("message", "Internal Error : Hubungi Customer Service");

            arrayListRet.clear();
            arrayListRet.add(map);
        }

        System.out.println("arrayListRet = "+arrayListRet);
        return arrayListRet;
    }


    /**
     * After completing sys_navdrawer_background task Dismiss the progress dialog
     * **/
    protected void onPostExecute(ArrayList<HashMap<String, String>> file_url) {

    }
}

