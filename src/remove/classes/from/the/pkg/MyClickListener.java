package remove.classes.from.the.pkg;

import android.view.View;
import android.view.View.OnClickListener;

public class MyClickListener implements OnClickListener{

	
	   private int position;
	   private int requestId;

	    public MyClickListener(int position, int requestId) {
	       this.position = position;
	       this.requestId = requestId;
	    }

	    public void onClick(View v) {
	       System.out.println("position clicked " + getPosition() + " request id "+getRequestId());
	    }

	    public int getPosition() {
	      return position;
	    }
	    public int getRequestId(){
	    	return requestId;
	    }
}
