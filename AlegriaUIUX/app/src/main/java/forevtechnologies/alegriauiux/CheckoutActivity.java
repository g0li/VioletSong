package forevtechnologies.alegriauiux;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.paynimo.android.payment.PaymentActivity;
import com.paynimo.android.payment.PaymentModesActivity;
import com.paynimo.android.payment.model.Checkout;

import java.util.ArrayList;

import forevtechnologies.alegriauiux.models.CartModel;
import forevtechnologies.alegriauiux.models.TicketCartModel;
import forevtechnologies.alegriauiux.sharedPreferenceFile.SharedPreferenceStringTags;

import static forevtechnologies.alegriauiux.CartActivity.CART_EXISTS;
import static forevtechnologies.alegriauiux.CartActivity.TICKET_EXISTS;

public class CheckoutActivity extends BaseActivity {
    private static final String TAG = "CheckoutActivity";
    int varun;
    int transactionAmount;
    int FLAG;
    SharedPreferences userOfflineCartItems,userOfflineTickets;
    ArrayList<CartModel> items=new ArrayList<>();
    ArrayList<TicketCartModel> tItems=new ArrayList<>();
    FirebaseUser user;
    DatabaseReference databaseReference,mRefTickets;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.merchant_activity_checkout);
        SharedPreferences prefs = getSharedPreferences(
                getApplicationContext().getPackageName()+".cartPrice", Context.MODE_PRIVATE);
        varun=prefs.getInt("totalPrice",0);
        user= FirebaseAuth.getInstance().getCurrentUser();
        userOfflineCartItems=getSharedPreferences(SharedPreferenceStringTags.USER_CART_DATABASE,MODE_PRIVATE);
        userOfflineTickets = getSharedPreferences(SharedPreferenceStringTags.USER_TICKET_DATABASE,MODE_PRIVATE);
        databaseReference=FirebaseDatabase.getInstance().getReference().child("User Data").child(user.getUid());
        mRefTickets=FirebaseDatabase.getInstance().getReference().child("Tickets").child(user.getUid());
        Intent i=getIntent();
        Bundle b=new Bundle();
        b=i.getExtras();
        if(b.isEmpty()){
            Log.w("EMPTY:","TRUE");
        }
        else{
            Log.w("EMPTY:","FALSE");
        }
        if(b.getString("DATA_TYPE").equals("Tickets")){
            FLAG=1;
            tItems=b.getParcelableArrayList("TICKET_DATA");
            transactionAmount=b.getInt("TICKET_PRICE");
            for(TicketCartModel t:tItems){
                Log.w("DATA & PRICE:",t.getName()+t.getPrice());
            }
        }
        else if(b.getString("DATA_TYPE").equals("Reg")){
            FLAG=2;
            items=b.getParcelableArrayList("REG_DATA");
            transactionAmount=b.getInt("REG_PRICE");
            for(CartModel t:items){
                Log.w("DATA & PRICE:",t.getName()+String.valueOf(PriceMapper.getPrice(t.getName())));
            }

        }

        creatingCheckOutObjects();
    }

    private void creatingCheckOutObjects() {
        Checkout checkout = new Checkout();

        checkout.setMerchantIdentifier("T142944");  //where T1234 is the merchant Code and will be provided by TPSL
        checkout.setTransactionIdentifier("TXN001"); //where TXN001 is the Merchant transaction identifier (alphanumeric no special character allowed)
        checkout.setTransactionReference("ORD0001"); //where ORD0001 is the Merchant transaction reference number
        checkout.setTransactionType("Sale"); //Transaction type
        checkout.setTransactionSubType("Debit"); //Transaction subtype
        checkout.setTransactionCurrency("INR"); //CURRENCY
        checkout.setTransactionAmount("5.00"); //Transaction amount
        checkout.setTransactionDateTime("27-06-2016"); //Transaction date

        checkout.setConsumerIdentifier("User1"); //Consumer Identifier, set this value as application user name
        checkout.setConsumerEmailID("test@gmail.com"); //Consumer email id
        checkout.setConsumerMobileNumber("7620656789"); //Consumer mobile number
        checkout.setConsumerAccountNo(""); //Default value "", leave it blank
        Example : checkout.addCartItem("TEST", "5.0", "0.0", "0.0", "SMSG2015-01-12345", "Mobile", "HTC Desire", "www.mes.ac.in");
        callingPaymentActivity(checkout);

    }

    private void callingPaymentActivity(Checkout checkout) {
        Intent authIntent = new Intent(this, PaymentModesActivity.class);
        Log.d("Checkout Request Object",
                checkout.getMerchantRequestPayload().toString());
        authIntent.putExtra(PaymentActivity.ARGUMENT_DATA_CHECKOUT,
                checkout);
// Public Key
        authIntent.putExtra(PaymentActivity.EXTRA_PUBLIC_KEY,
                "1234-6666-6789-56");
// Requested Payment Mode
        authIntent.putExtra(PaymentActivity.EXTRA_REQUESTED_PAYMENT_MODE,
                PaymentActivity.PAYMENT_METHOD_DEFAULT);

        startActivityForResult(authIntent, PaymentActivity.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == PaymentActivity.REQUEST_CODE) {
            // Make sure the request was successful
            if (resultCode == PaymentActivity.RESULT_OK) {
                Log.d(TAG, "Result Code :" + RESULT_OK);
                if (data != null) {

                    try {
                        Checkout checkout_res = (Checkout) data
                                .getSerializableExtra(PaymentActivity.ARGUMENT_DATA_CHECKOUT);
                        Log.d("Checkout Response Obj", checkout_res
                                .getMerchantResponsePayload().toString());

                        String transactionType = checkout_res.
                                getMerchantRequestPayload().getTransaction().getType();
                        String transactionSubType = checkout_res.
                                getMerchantRequestPayload().getTransaction().getSubType();
                        if (transactionType != null && transactionType.equalsIgnoreCase(PaymentActivity.TRANSACTION_TYPE_PREAUTH)
                                && transactionSubType != null && transactionSubType
                                .equalsIgnoreCase(PaymentActivity.TRANSACTION_SUBTYPE_RESERVE)) {
                            // Transaction Completed and Got SUCCESS
                            if (checkout_res.getMerchantResponsePayload()
                                    .getPaymentMethod().getPaymentTransaction()
                                    .getStatusCode().equalsIgnoreCase(PaymentActivity.TRANSACTION_STATUS_PREAUTH_RESERVE_SUCCESS)) {
                                Toast.makeText(getApplicationContext(), "Transaction Status - Success", Toast.LENGTH_SHORT).show();
                                Log.v("TRANSACTION STATUS=>", "SUCCESS");

                                /**
                                 * TRANSACTION STATUS - SUCCESS (status code
                                 * 0200 means success), NOW MERCHANT CAN PERFORM
                                 * ANY OPERATION OVER SUCCESS RESULT
                                 */

                                if (checkout_res.getMerchantResponsePayload().getPaymentMethod().getPaymentTransaction().getInstruction().
                                        getStatusCode().equalsIgnoreCase("")) {
                                    /**
                                     * SI TRANSACTION STATUS - SUCCESS (status
                                     * code 0200 means success)
                                     */
                                    Log.v("TRANSACTION SI STATUS=>",
                                            "SI Transaction Not Initiated");
                                }

                            } // Transaction Completed and Got FAILURE

                            else {
                                // some error from bank side
                                Log.v("TRANSACTION STATUS=>", "FAILURE");
                                Toast.makeText(getApplicationContext(),
                                        "Transaction Status - Failure",
                                        Toast.LENGTH_SHORT).show();
                            }

                        } else {

                            // Transaction Completed and Got SUCCESS
                            if (checkout_res.getMerchantResponsePayload().getPaymentMethod().getPaymentTransaction().getStatusCode().equalsIgnoreCase(
                                    PaymentActivity.TRANSACTION_STATUS_SALES_DEBIT_SUCCESS)) {
                                Toast.makeText(getApplicationContext(), "Transaction Status - Success", Toast.LENGTH_SHORT).show();
                                Log.v("TRANSACTION STATUS=>", "SUCCESS");
                                finish();
                                //make a function to push data on sheets+firebase
                                pushDataToStorages();
                                startActivity(new Intent(this,MyEvents.class));

                                /**
                                 * TRANSACTION STATUS - SUCCESS (status code
                                 * 0300 means success), NOW MERCHANT CAN PERFORM
                                 * ANY OPERATION OVER SUCCESS RESULT
                                 */

                                if (checkout_res.getMerchantResponsePayload().
                                        getPaymentMethod().getPaymentTransaction().
                                        getInstruction().getStatusCode()
                                        .equalsIgnoreCase("")) {
                                    /**
                                     * SI TRANSACTION STATUS - SUCCESS (status
                                     * code 0300 means success)
                                     */
                                    Log.v("TRANSACTION SI STATUS=>",
                                            "SI Transaction Not Initiated");
                                } else if (checkout_res.getMerchantResponsePayload()
                                        .getPaymentMethod().getPaymentTransaction()
                                        .getInstruction()
                                        .getStatusCode().equalsIgnoreCase(
                                                PaymentActivity.TRANSACTION_STATUS_SALES_DEBIT_SUCCESS)) {

                                    /**
                                     * SI TRANSACTION STATUS - SUCCESS (status
                                     * code 0300 means success)
                                     */
                                    Log.v("TRANSACTION SI STATUS=>", "SUCCESS");
                                } else {
                                    /**
                                     * SI TRANSACTION STATUS - Failure (status
                                     * code OTHER THAN 0300 means failure)
                                     */
                                    Log.v("TRANSACTION SI STATUS=>", "FAILURE");
                                }

                            } // Transaction Completed and Got FAILURE
                            else {
                                // some error from bank side
                                Log.v("TRANSACTION STATUS=>", "FAILURE");
                                Toast.makeText(getApplicationContext(),
                                        "Transaction Status - Failure",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                        String result = "StatusCode : " + checkout_res
                                .getMerchantResponsePayload().getPaymentMethod()
                                .getPaymentTransaction().getStatusCode()
                                + "\nStatusMessage : " + checkout_res
                                .getMerchantResponsePayload().getPaymentMethod()
                                .getPaymentTransaction().getStatusMessage()
                                + "\nErrorMessage : " + checkout_res
                                .getMerchantResponsePayload().getPaymentMethod()
                                .getPaymentTransaction().getErrorMessage()
                                + "\nAmount : " + checkout_res
                                .getMerchantResponsePayload().getPaymentMethod().getPaymentTransaction().getAmount()
                                + "\nDateTime : " + checkout_res.
                                getMerchantResponsePayload().getPaymentMethod()
                                .getPaymentTransaction().getDateTime()
                                + "\nMerchantTransactionIdentifier : "
                                + checkout_res.getMerchantResponsePayload()
                                .getMerchantTransactionIdentifier()
                                + "\nIdentifier : " + checkout_res
                                .getMerchantResponsePayload().getPaymentMethod()
                                .getPaymentTransaction().getIdentifier();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            } else if (resultCode == PaymentActivity.RESULT_ERROR) {
                Log.d(TAG, "got an error");

                if (data.hasExtra(PaymentActivity.RETURN_ERROR_CODE) &&
                        data.hasExtra(PaymentActivity.RETURN_ERROR_DESCRIPTION)) {
                    String error_code = (String) data
                            .getStringExtra(PaymentActivity.RETURN_ERROR_CODE);
                    String error_desc = (String) data
                            .getStringExtra(PaymentActivity.RETURN_ERROR_DESCRIPTION);

                    Toast.makeText(getApplicationContext(), " Got error :"
                            + error_code + "--- " + error_desc, Toast.LENGTH_SHORT)
                            .show();
                    Log.d(TAG + " Code=>", error_code);
                    Log.d(TAG + " Desc=>", error_desc);

                }

            } else if (resultCode == PaymentActivity.RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Transaction Aborted by User",
                        Toast.LENGTH_SHORT).show();
                Log.d(TAG, "User pressed back button");
                startActivity(new Intent(this,MainActivity.class));

            }
        }
    }

    private void pushDataToStorages() {
            switch (FLAG){
                case 1: //tickets
                    sendTicketData();
                    break;
                case 2: //registration
                    sendRegistrationData();
                    break;
            }
    }

    private void sendTicketData() {
        for(TicketCartModel m: tItems ){
            if(userOfflineTickets.contains("Concert@"+m.getName())||mRefTickets.child("Concert@"+m.getName()).getKey().equals("Conert@"+m.getName())){
                Log.w("Skipped","Skipped");
                Log.w("Child Name",mRefTickets.child("Concert@"+m.getName()).getKey());
                continue;
            }
            userOfflineTickets.edit().putString("Concert@"+m.getName(),m.getName()).commit();
            if(!userOfflineTickets.contains(TICKET_EXISTS)){userOfflineTickets.edit().putString(TICKET_EXISTS,TICKET_EXISTS).commit();}

            new SendData(user.getUid(),"Concert"+m.getName(),String.valueOf(m.getPrice())).execute();
            Log.w("EventBeingPosted",m.getName());
            mRefTickets.child("Concert@"+m.getName()+String.valueOf(m.getPrice())).setValue(m.getName());

        }
    }

    private void sendRegistrationData() {
        for(CartModel m : items ){
            //check if user has already subscribed for this event
            if(userOfflineCartItems.contains("Event@"+m.getName()) || databaseReference.child("Event@"+m.getName()).getKey().equals("Event@"+m.getName()))
            {
                Log.w("Skipped","Skipped");
                Log.w("ChildName:",
                        databaseReference.child("Event@"+m.getName()).getKey());
                //continue;
            }
            //event into sharedPreference
            userOfflineCartItems.edit().putString("Event@"+m.getName(),m.getName()).commit();
            if(!userOfflineCartItems.contains(CART_EXISTS)){userOfflineCartItems.edit().putString(CART_EXISTS,CART_EXISTS).commit();}
            //event into google sheet
            new SendData(user.getUid(),m.getName(),String.valueOf(PriceMapper.getPrice(m.getName()))+"/-").execute();
            //event into firebase database
            databaseReference.child("Event@"+m.getName()).setValue(m.getName());

        }
    }

}
