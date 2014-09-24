package ds.zillowpayment;

import java.util.List;

import ds.zillowpayment.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/*
 * Because this application needs access to the Internet, you need to add the appropriate permissions to the Android manifest file. 
 * Open the AndroidManifest.xml file and add the following as a child of the <manifest> element:
 * <uses-permission android:name="android.permission.INTERNET" />
 */

public class Payment extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        /*
         * The click listener will need a reference to this object, so that upon successfully finding a picture from Flickr, it 
         * can callback to this object with the resulting picture Bitmap.  The "this" of the OnClick will be the OnClickListener, not
         * this InterestingPicture. 
         */
        final Payment ip = this;
        
        /*
         * Find the "submit" button, and add a listener to it
         */
        Button submitButton = (Button)findViewById(R.id.submit);

        
      	// Add a listener to the send button
        submitButton.setOnClickListener(new OnClickListener(){
        	public void onClick(View viewParam) {
        		String price= ((EditText)findViewById(R.id.price)).getText().toString();
        		String down = ((EditText)findViewById(R.id.down)).getText().toString();
        		GetPayment gp = new GetPayment();
        		gp.search(ip, price, down); // Done asynchronously in another thread.  It calls ip.pictureReady() in this thread when complete.
        	}
        });
    }
    
    /* 
     * This is called by the GetPicture object when the picture is ready.  This allows for passing back the Bitmap picture for updating the ImageView
     */
    public void paymentReady(List payment) {
		TextView price = (EditText)findViewById(R.id.price);
		TextView down = (EditText)findViewById(R.id.down);
		TextView status = (TextView)findViewById(R.id.status);
		if (payment != null) {
    		status.setText("Result: Loan Type: " + payment.get(0) + " Rate: " + payment.get(1) + " Monthly Insurance: " 
    		+ payment.get(2) + " Monthly Payment: " + payment.get(3));
			
    		
    	} else {
    		status.setText("Sorry, I could not calculate.");
    	}
		price.setText("");
		down.setText("");
    }
}