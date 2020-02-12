package com.example.tms;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
//import com.google.firebase.auth.FirebaseAuth;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DeliveryActivity extends AppCompatActivity {
private Button cont;
private EditText amount;
private Spinner spin;
SpinnerAdapter spinnerAdapter;
ArrayList<Price> arrayList_price;
Price object_price;
int taking_amt;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        cont=(Button)findViewById(R.id.ontinue);
//        amount=(EditText)findViewById(R.id.amount);
        spin=(Spinner)findViewById(R.id.spinner_price);
        arrayList_price=new ArrayList<>();
//        object_price=new Price("Choose subject",0);
//        arrayList_price.add(object_price);

        object_price=new Price("Physcis(12th)",750);
        arrayList_price.add(object_price);

        object_price=new Price("Chemistry(12th)",750);
        arrayList_price.add(object_price);

        object_price=new Price("Maths(12th)",800);
        arrayList_price.add(object_price);

        object_price=new Price("Biology(12th)",650);
        arrayList_price.add(object_price);
        object_price=new Price("Physcis(11th)",700);
        arrayList_price.add(object_price);

        object_price=new Price("Chemistry(11th)",700);
        arrayList_price.add(object_price);

        object_price=new Price("Maths(11th)",700);
        arrayList_price.add(object_price);

        object_price=new Price("Biology(11th)",650);
        arrayList_price.add(object_price);

        object_price=new Price("English",500);
        arrayList_price.add(object_price);
        object_price=new Price("SST",400);
        arrayList_price.add(object_price);

        spinnerAdapter=new SpinnerAdapter(DeliveryActivity.this,arrayList_price);
        spin.setAdapter(spinnerAdapter);

        //click on each item of spinner

       spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                taking_amt=arrayList_price.get(position).getRupees();
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {
Toast.makeText(getApplicationContext(),"no item selected",Toast.LENGTH_SHORT).show();
           }
       });
       cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cont.setEnabled(false);
                progressDialog=new ProgressDialog(DeliveryActivity.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
//                progressDialog.getWindow().setBackgroundDrawable(
//                        android.R.color.transparent
//                );
//
                if (ContextCompat.checkSelfPermission(DeliveryActivity.this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(DeliveryActivity.this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, 101);
                }
                final String Mid="YqHLjM20988223560171";
             //   final String customer_id= FirebaseAuth.getInstance().getUid();
                final String customer_id=generateStrng();

               // final String order_id= UUID.randomUUID().toString().substring(0,28);
                final String order_id=UUID.randomUUID().toString().replaceAll("-", "");
            //    final String pay_amt=amount.getText().toString();

               // String url= "https://ductless-factory.000webhostapp.com/paytm/generateChecksum.php" ;
                String url="https://ductless-factor.000webhostapp.com/paytm/generateChecksum.php";
                final String callBackUrl="https://pguat.paytm.com/paytmchecksum/paytmCallback.jsp";
                RequestQueue requestQueue;
            requestQueue = new Volley().newRequestQueue(DeliveryActivity.this);



                        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            if(jsonObject.has("CHECKSUMHASH")){
                                String CHECKSUMHASH=jsonObject.getString("CHECKSUMHASH");

                                PaytmPGService paytmPGService=PaytmPGService.getStagingService();
                                 HashMap<String,String> paramMap = new HashMap<String,String>();
                                paramMap.put( "MID" , Mid);
                              paramMap.put( "ORDER_ID" , order_id);
                              paramMap.put( "CUST_ID" , customer_id);
                                paramMap.put( "CHANNEL_ID" , "WAP");
                           //     paramMap.put( "TXN_AMOUNT" , pay_amt);
                                paramMap.put( "TXN_AMOUNT" , Integer.toString(taking_amt));
                                paramMap.put( "WEBSITE" , "WEBSTAGING");
                                paramMap.put( "INDUSTRY_TYPE_ID" , "Retail");
                           paramMap.put( "CALLBACK_URL", callBackUrl);
                               paramMap.put("CHECKSUMHASH",CHECKSUMHASH);
                              //  paramMap.put("EMAIL","rishabh@gmail.com");

                                PaytmOrder order=new PaytmOrder(paramMap);
                                paytmPGService.initialize(order,null);
                                paytmPGService.startPaymentTransaction(DeliveryActivity.this, true, true, new PaytmPaymentTransactionCallback() {
                                    @Override
                                    public void onTransactionResponse(Bundle inResponse) {
                                        Toast.makeText(getApplicationContext(), "Payment Transaction response " + inResponse.toString(), Toast.LENGTH_LONG).show();
                                    }


                                    @Override
                                    public void networkNotAvailable() {
                                        Toast.makeText(getApplicationContext(), "Network connection error: Check your internet connectivity", Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void clientAuthenticationFailed(String inErrorMessage) {
                                        Toast.makeText(getApplicationContext(), "Authentication failed: Server error" + inErrorMessage.toString(), Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void someUIErrorOccurred(String inErrorMessage) {
                                        Toast.makeText(getApplicationContext(), "UI Error " + inErrorMessage , Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void onErrorLoadingWebPage(int iniErrorCode, String inErrorMessage, String inFailingUrl) {
                                        Toast.makeText(getApplicationContext(), "Authentication failed: Server error" + inErrorMessage.toString(), Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void onBackPressedCancelTransaction() {
                                        Toast.makeText(getApplicationContext(), "Transaction cancelled" , Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {
                                        Toast.makeText(getApplicationContext(), "Transaction cancelled"+inResponse.toString() , Toast.LENGTH_LONG).show();
                                    }
                                });

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                } , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DeliveryActivity.this,"something went wrong",Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams()  {  //throws AuthFailureError
                        Map<String, String> paramMap = new HashMap<String,String>();
                        paramMap.put( "MID" , Mid);
                        paramMap.put( "ORDER_ID" , order_id);
                      paramMap.put( "CUST_ID" , customer_id);
                        paramMap.put( "CHANNEL_ID" , "WAP");
                      //  paramMap.put( "TXN_AMOUNT" , pay_amt);
                        paramMap.put( "TXN_AMOUNT" , Integer.toString(taking_amt));
                        paramMap.put( "WEBSITE" , "WEBSTAGING");
                        paramMap.put( "INDUSTRY_TYPE_ID" , "Retail");
                        paramMap.put( "CALLBACK_URL", callBackUrl);

                        return  paramMap;
                    }
                };
                requestQueue.add(stringRequest);

            }
        });
//progressDialog.dismiss();
    }
    private String generateStrng(){
        String uuid=UUID.randomUUID().toString();
        return uuid.replaceAll("-","@");

    }
}
