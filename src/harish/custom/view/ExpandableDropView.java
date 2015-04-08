package harish.custom.view;

import com.babloosashi.neighbour.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class ExpandableDropView extends RelativeLayout implements OnClickListener{

	private View child;
	private ScrollView scrollParent;
	private boolean isExpanded = false;
	
	private static final int BOTTOM_PADDING = 20;
	
	private LayoutInflater inflater;
	
	public ExpandableDropView(Context context) {
        this(context, null);
    }

    //Thus constructor gets used if you ever instantiate your component from XML
    public ExpandableDropView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ExpandableDropView, 0, 0);
        String titleText = a.getString(R.styleable.ExpandableDropView_titleText);
        a.recycle();
        
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vi = inflater.inflate(R.layout.expandableview_header, this);    
        
        TextView title = (TextView) vi.findViewById(R.id.lblListHeader);
      //  title.setText("titleText");
        
        setBackgroundColor(Color.WHITE);
        
        setClickable(true);
    }
    
    public void expandView()
    {
    	if( child != null )
		{
	        ImageView arrow = (ImageView) this.findViewById(R.id.ivArrow); 

	        if(!isExpanded)
	        {
	        	arrow.setImageResource(R.drawable.uarrow);
	        	child.setVisibility(View.VISIBLE);
	        	
	        	isExpanded = true;
	        }

		}
		else
			Log.e("CAT", "ERROR: Child not instantiated.");
    }
    
    public void setChildView( View child, ScrollView scrollParent )
    {
    	this.child = child;
    	this.scrollParent = scrollParent;
    	LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
    	scrollParent.setLayoutParams(params);
    	child.setLayoutParams(params);
    	child.setVisibility(View.GONE);
    	child.setPadding(0, 0, 0, BOTTOM_PADDING);
    	
    	LinearLayout parent = (LinearLayout) getParent();
    	int thisIndex = parent.indexOfChild(this);
    	parent.addView(child, thisIndex + 1);
    	
    	setOnClickListener(this);
    }
	
	@Override
	public void onClick(View v) {
		
		if( child != null )
		{
	        ImageView arrow = (ImageView) v.findViewById(R.id.ivArrow); 
	        
	        if( isExpanded )
	        {
	        	arrow.setImageResource(R.drawable.downarrow);
	        	child.setVisibility(View.GONE);
	        	
	        	isExpanded = false;
	        }
	        else
	        {
	        	arrow.setImageResource(R.drawable.uarrow);
	        	child.setVisibility(View.VISIBLE);
	        	
	        	isExpanded = true;
	        }
	        
	        focusOnView();
		}
		else
			Log.e("CAT", "ERROR: Child not instantiated.");
	}
	
    private final void focusOnView(){
        new Handler().post(new Runnable() {
            @Override
            public void run() {
            	
                scrollParent.smoothScrollTo(0, child.getBottom());
            }
        });
    }

}