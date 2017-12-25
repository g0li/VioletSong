package forevtechnologies.alegriauiux;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

/**
 * Created by ABHIJAY on 25-12-2017.
 */

public class QRCode extends Activity {

    private static final int COLOR1 = 0x00796B;
    private static final int COLOR2 = 0xFAFAFA;
    public final static int WIDTH=1000;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_code_view);
        ImageView imageView = findViewById(R.id.qrcode_image);
        Intent qrCode = getIntent();
        String DATA = qrCode.getStringExtra("Data");
        Toast.makeText(getBaseContext(),DATA,Toast.LENGTH_SHORT).show();
        try {
            Bitmap bitmap = encodeAsBitmap(DATA);
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }


    }

    Bitmap encodeAsBitmap(String str) throws WriterException {
        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(str,
                    BarcodeFormat.QR_CODE, WIDTH, WIDTH, null);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int w = result.getWidth();
        int h = result.getHeight();
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            int offset = y * w;
            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0,1000, 0, 0, w, h);
        return bitmap;
    }
}
