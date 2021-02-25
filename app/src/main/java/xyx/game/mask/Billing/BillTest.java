package xyx.game.mask.Billing;

import android.app.Activity;
import android.util.Log;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;

import java.util.ArrayList;
import java.util.List;

public class BillTest {

    private PurchasesUpdatedListener purchasesUpdatedListener = new PurchasesUpdatedListener() {
        @Override
        public void onPurchasesUpdated(BillingResult billingResult, List<Purchase> purchases) {
            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK
                    && purchases != null) {
                for (Purchase purchase : purchases) {
                    handlePurchase(purchase);
                }
            } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
                // Handle an error caused by a user cancelling the purchase flow.
                billing.Cance();


            } else {
                // Handle any other error codes.
                billing.Error();



            }
        }
    };
    private BillingClient billingClient;

    private Billing billing;

    public static String vip1="vip",vip2="vip5";


    void handlePurchase(final Purchase purchase) {
        // Purchase retrieved from BillingClient#queryPurchases or your PurchasesUpdatedListener.
        // Purchase purchase = purchase;

        // Verify the purchase.
        // Ensure entitlement was not already granted for this purchaseToken.
        // Grant entitlement to the user.

        ConsumeParams consumeParams =
                ConsumeParams.newBuilder()
                        .setPurchaseToken(purchase.getPurchaseToken())
                        .build();

        ConsumeResponseListener listener = new ConsumeResponseListener() {
            @Override
            public void onConsumeResponse(BillingResult billingResult, String purchaseToken) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    // Handle the success of the consume operation.
                    billing.OK(purchase.getSku());


                }
            }
        };

        billingClient.consumeAsync(consumeParams, listener);//消耗
    }

    Activity activity;
    public BillTest(Activity ativity, Billing billing) {
        this.activity=ativity;
        this.billing=billing;

        billingClient = BillingClient.newBuilder(activity)
                .setListener(purchasesUpdatedListener)
                .enablePendingPurchases()
                .build();


        // start();


        // start(vip1);


    }

    public void start(final String string) {
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {
                if (billingResult.getResponseCode() ==  BillingClient.BillingResponseCode.OK) {
                    // The BillingClient is ready. You can query purchases here.
                    Log.v("------","START...");
                    // textView.setText("START...");
                    List<String> skuList = new ArrayList<>();
                    skuList.add(vip1);
                    skuList.add(vip2);

                    SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
                    params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP);
                    billingClient.querySkuDetailsAsync(params.build(),
                            new SkuDetailsResponseListener() {
                                @Override
                                public void onSkuDetailsResponse(BillingResult billingResult,
                                                                 List<SkuDetails> skuDetailsList) {
                                    if (skuDetailsList.size()!=0) {

                                        for (SkuDetails sku:skuDetailsList){
                                            if (sku.getSku().equals(string)){
                                                // Retrieve a value for "skuDetails" by calling querySkuDetailsAsync().
                                                BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                                                        .setSkuDetails(sku)
                                                        .build();
                                                //textView.setText(skuDetailsList.get(0)+"\n" +skuDetailsList.get(1)+"\n"+ skuDetailsList.get(2)+"\n"+ skuDetailsList.get(3)+"\n");
                                                int responseCode = billingClient.launchBillingFlow(activity, billingFlowParams).getResponseCode();


                                            }
                                        }
                                    }
// Handle the result.
                                }
                            });
                }
            }
            @Override
            public void onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.


            }
        });

    }
}
