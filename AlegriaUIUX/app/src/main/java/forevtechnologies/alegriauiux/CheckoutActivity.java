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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by jojosexbomb69 on 12/12/17.
 * class is still incomplete, don't link with any activities
 */

public class CheckoutActivity extends Activity {
    Checkout checkout= new Checkout();
    Intent authIntent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkout.setMerchantIdentifier("T1234");  //where T1234 is the MERCHANT CODE, update it with Merchant Code provided by TPSL
        checkout.setTransactionIdentifier("TXN001"); //where TXN001 is the Merchant Transaction Identifier, it should be different for each transaction (alphanumeric value, no special character allowed)
        checkout.setTransactionReference ("ORD0001"); //where ORD0001 is the Merchant Transaction Reference number
        checkout.setTransactionType (PaymentActivity.TRANSACTION_TYPE_SALE); //Transaction Type
        checkout.setTransactionSubType (PaymentActivity.TRANSACTION_SUBTYPE_DEBIT); //Transaction Subtype
        checkout.setTransactionCurrency ("INR"); //Currency Type
        checkout.setTransactionAmount ("1.00"); //Transaction Amount
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c.getTime());
        checkout.setTransactionDateTime (formattedDate); //Transaction Date
// setting Consumer fields values
        checkout.setConsumerIdentifier (""); //Consumer Identifier, default value "", set this value as application user name if you want Instrument Vaulting, SI on Cards. Consumer ID should be alpha-numeric value with no space
        checkout.setConsumerEmailID ("test@gmail.com"); //Consumer Email ID
        checkout.setConsumerMobileNumber ("7620656789"); //Consumer Mobile Number
        checkout.setConsumerAccountNo ("");//Account Number, default value "". For eMandate, you can set this value here or can be set later in SDK.
        checkout.addCartItem("ProductID",
                "1.00",
                "0.00",
                "0.00",
                " ",
                " ",
                "Default item",
                "696969696");

        checkout.setTransactionMerchantInitiated("Y");
        int paymentToken = ThreadLocalRandom.current().nextInt(10000, 99999+1);
        checkout.setPaymentMethodToken(String.valueOf(paymentToken));
        authIntent=new Intent(CheckoutActivity.this, PaymentModesActivity.class);
        Log.d("Checkout Request Object",
                checkout.getMerchantRequestPayload().toString());
        authIntent.putExtra(PaymentActivity.ARGUMENT_DATA_CHECKOUT,
                checkout);
// Public Key
        authIntent.putExtra(PaymentActivity.EXTRA_PUBLIC_KEY,
                "1234-6666-6789-56");
// Requested Payment Mode
        authIntent.putExtra(PaymentActivity.EXTRA_REQUESTED_PAYMENT_MODE,PaymentActivity.PAYMENT_METHOD_CASHCARDS);


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
                if (resultCode==PaymentActivity.RESULT_OK){
                    if(data!=null){
                        Checkout checkout_res=(Checkout)data.getSerializableExtra(PaymentActivity.ARGUMENT_DATA_CHECKOUT);
                        Log.d("Checkout Response Obj", checkout_res
                                .getMerchantResponsePayload().toString());
                        String transaction_type=checkout_res.getMerchantRequestPayload().getTransaction().getType().toString();
                        String transaction_Subtype=checkout_res.getMerchantRequestPayload().getTransaction().getSubType().toString();

                    }
                }
            }
    }
}
