package forevtechnologies.alegriauiux;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.paynimo.android.payment.PaymentActivity;
import com.paynimo.android.payment.PaymentModesActivity;
import com.paynimo.android.payment.model.Checkout;
import android.R;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by jojosexbomb69 on 12/12/17.
 * class is still incomplete, don't link with any activities
 */

public class CheckoutActivity extends Activity {
    Checkout checkout= new Checkout();
    String TAG="CheckoutActivity";
    Intent authIntent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkout.setMerchantIdentifier("T142944");  //where T1234 is the MERCHANT CODE, update it with Merchant Code provided by TPSL
        checkout.setTransactionIdentifier("TXN001"); //where TXN001 is the Merchant Transaction Identifier, it should be different for each transaction (alphanumeric value, no special character allowed)
        checkout.setTransactionReference ("ORD0001"); //where ORD0001 is the Merchant Transaction Reference number
        checkout.setTransactionType ("Sale"); //Transaction Type
        checkout.setTransactionSubType ("Debit"); //Transaction Subtype
        checkout.setTransactionCurrency ("INR"); //Currency Type
        checkout.setTransactionAmount ("5.00"); //Transaction Amount
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c.getTime());
        checkout.setTransactionDateTime ("2017-12-16"); //Transaction Date
// setting Consumer fields values
        checkout.setConsumerIdentifier ("USER1"); //Consumer Identifier, default value "", set this value as application user name if you want Instrument Vaulting, SI on Cards. Consumer ID should be alpha-numeric value with no space
        checkout.setConsumerEmailID ("test@gmail.com"); //Consumer Email ID
        checkout.setConsumerMobileNumber ("9930920642"); //Consumer Mobile Number
        checkout.setConsumerAccountNo ("58275827");//Account Number, default value "". For eMandate, you can set this value here or can be set later in SDK.
        checkout.addCartItem("TEST",
                "5.00",
                "0.00",
                "0.00",
                " ",
                " ",
                "Default item",
                "696969696");

        checkout.setTransactionMerchantInitiated("Y");
        int paymentToken = ThreadLocalRandom.current().nextInt(10000, 99999+1);
        checkout.setPaymentMethodToken("1234");

        authIntent=new Intent(CheckoutActivity.this, PaymentModesActivity.class);
        Log.d("Checkout Request Object",
                checkout.getMerchantRequestPayload().toString());
        authIntent.putExtra(PaymentActivity.ARGUMENT_DATA_CHECKOUT,
                checkout);
// Public Key
        authIntent.putExtra(PaymentActivity.EXTRA_PUBLIC_KEY,
                "1234-6666-6789-56");
// Requested Payment Mode
        authIntent.putExtra(PaymentActivity.EXTRA_REQUESTED_PAYMENT_MODE,PaymentActivity.PAYMENT_METHOD_DEFAULT);


//        calling payment webview
        startActivityForResult(authIntent, PaymentActivity.REQUEST_CODE);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }



//callback from payment webview
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if(requestCode==PaymentActivity.REQUEST_CODE){
                if(resultCode==PaymentActivity.RESULT_OK){
                    if (data != null) {

                        try {
                            Checkout checkout_res = (Checkout) data
                                    .getSerializableExtra(PaymentActivity
                                            .ARGUMENT_DATA_CHECKOUT);
                            Log.d("Checkout Response Obj", checkout_res
                                    .getMerchantResponsePayload().toString());


                            String transactionType = checkout_res.
                                    getMerchantRequestPayload().getTransaction().getType();
                            String transactionSubType = checkout_res.
                                    getMerchantRequestPayload().getTransaction().getSubType();
                            if (transactionType != null && transactionType						   .equalsIgnoreCase(PaymentActivity.TRANSACTION_TYPE_PREAUTH)
                                    && transactionSubType != null && transactionSubType
                                    .equalsIgnoreCase(PaymentActivity.TRANSACTION_SUBTYPE_RESERVE)){
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

                                    if (checkout_res.getMerchantResponsePayload()
                                            .getPaymentMethod().getPaymentTransaction().getInstruction().getStatusCode().equalsIgnoreCase("")) {
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
                                    + "\nErrorMessage : "+ checkout_res
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
                                    .getPaymentTransaction().getIdentifier()
                                    + "\nBankSelectionCode : " + checkout_res
                                    .getMerchantResponsePayload().getPaymentMethod()
                                    .getBankSelectionCode()
                                    + "\nBankReferenceIdentifier : " + checkout_res
                                    .getMerchantResponsePayload().getPaymentMethod()
                                    .getPaymentTransaction().getBankReferenceIdentifier()
                                    + "\nRefundIdentifier : " + checkout_res
                                    .getMerchantResponsePayload().getPaymentMethod()
                                    .getPaymentTransaction().getRefundIdentifier()
                                    + "\nBalanceAmount : " + checkout_res
                                    .getMerchantResponsePayload().getPaymentMethod()
                                    .getPaymentTransaction().getBalanceAmount()
                                    + "\nInstrumentAliasName : " + checkout_res
                                    .getMerchantResponsePayload().getPaymentMethod()
                                    .getInstrumentAliasName()
                                    + "\nSI Mandate Id : " + checkout_res
                                    .getMerchantResponsePayload().getPaymentMethod()
                                    .getPaymentTransaction().getInstruction().getId()
                                    + "\nSI Mandate Status : " + checkout_res
                                    .getMerchantResponsePayload().getPaymentMethod()
                                    .getPaymentTransaction().getInstruction().getStatusCode()
                                    + "\nSI Mandate Error Code : " + checkout_res
                                    .getMerchantResponsePayload().getPaymentMethod()
                                    .getPaymentTransaction().getInstruction().getErrorcode();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
                else if (resultCode == PaymentActivity.RESULT_ERROR) {
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
                }
                else if (resultCode == PaymentActivity.RESULT_CANCELED) {
                    Toast.makeText(getApplicationContext(), "Transaction Aborted by User",
                            Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "User pressed back button");

                }
            }
    }
}


