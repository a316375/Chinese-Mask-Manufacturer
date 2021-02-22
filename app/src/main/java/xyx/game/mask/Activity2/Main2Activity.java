package xyx.game.mask.Activity2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;

import java.util.ArrayList;
import java.util.List;

import xyx.game.mask.R;

public class Main2Activity extends AppCompatActivity {

    // An activity reference from which the billing flow will be launched.
    Activity activity = Main2Activity.this;


    private PurchasesUpdatedListener purchasesUpdatedListener = new PurchasesUpdatedListener() {
        @Override
        public void onPurchasesUpdated(BillingResult billingResult, List<Purchase> purchases) {
            // To be implemented in a later section.
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        final Button button=findViewById(R.id.button);

        final BillingClient billingClient = BillingClient.newBuilder(activity)
                .setListener(purchasesUpdatedListener)
                .enablePendingPurchases()
                .build();

        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {
                if (billingResult.getResponseCode() ==  BillingClient.BillingResponseCode.OK) {
                    // The BillingClient is ready. You can query purchases here.
                    button.setText("gggggg");

                }
            }
            @Override
            public void onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        });










        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //3
                final List<String> skuList = new ArrayList<> ();
                skuList.add("vip");
                skuList.add("vip2");
                skuList.add("vip3");
                skuList.add("vip999");

                SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
                params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP);
                billingClient.querySkuDetailsAsync(params.build(),
                        new SkuDetailsResponseListener() {
                            @Override
                            public void onSkuDetailsResponse(BillingResult billingResult,
                                                             List<SkuDetails> skuDetailsList) {
                                // Process the result.
                                button.setText(BillingClient.BillingResponseCode.OK+"="+billingResult.getResponseCode());
//
//                                if (skuDetailsList==null){button.setText("null");}
                                if (billingResult.getResponseCode()== BillingClient.BillingResponseCode.OK&&
                                skuList!=null){


                                    for (SkuDetails skuDetails:skuDetailsList){
                                        String name=skuDetails.getTitle();
                                        String sku=skuDetails.getSku();
                                        String price=skuDetails.getPrice();
                                        System.out.println("--"+name);

                                        button.setText(sku+""+price);
                                        // Retrieve a value for "skuDetails" by calling querySkuDetailsAsync().
                                        BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                                                .setSkuDetails(skuDetails) .build();
                                        int responseCode = billingClient.launchBillingFlow(activity, billingFlowParams).getResponseCode();

// Handle the result.
                                    }


                                }

                            }
                        });


            }
        });
    }



}
