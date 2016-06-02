package com.example.recycleviewtest;

import java.util.ArrayList;
import java.util.List;

import com.example.recycleviewtest.HomeAdapter.OnItemClickerListener;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.Toast;
	
@TargetApi(Build.VERSION_CODES.HONEYCOMB) public class MainActivity extends ActionBarActivity
{
	private ViewPager viewPager;
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private HomeAdapter mAdapter;
	private ArrayList<Fragment> fraList = new ArrayList<Fragment>();
	private MyPagerAdapter mmAdapter;
	private int offest;
	private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=(ImageView)findViewById(R.id.imageview);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
       
        StaggeredGridLayoutManager mStaggeredLayoutManager= new StaggeredGridLayoutManager(4,
                StaggeredGridLayoutManager.HORIZONTAL);
//        mStaggeredLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);
        mRecyclerView.setAdapter(mAdapter = new HomeAdapter(MainActivity.this,mDatas));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        getDispaly(mRecyclerView);
        viewPageAdapterDuel();
        mAdapter.setOnItemClickerListener(new OnItemClickerListener() {
			
			@Override
			public void OnItemClicker(View view, int position) {
				// TODO Auto-generated method stub
				if(position%4==0){
					 viewPager.setCurrentItem(position/4, true);
					 
					Toast.makeText(MainActivity.this, "你点击了"+position, Toast.LENGTH_SHORT).show();
				}
				else{return;}
			}
		});
    }
    
	protected void initData()
    {
        mDatas = new ArrayList<String>();
        for (int i = 0; i < 36; i++)
        {
            mDatas.add("" +  i);
        }
    }
    
	
    @SuppressWarnings("deprecation")
	private void viewPageAdapterDuel() {
		// TODO Auto-generated method stub
    	for (int i = 0; i < 8; i++) {
			MyFragment mf = new MyFragment();
			Bundle bundle = new Bundle();
			bundle.putInt("index", i);
			mf.setArguments(bundle);
			fraList.add(mf);
			
		}
		mmAdapter = new MyPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(mmAdapter);
		
		
	
		
		
		
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
//				int sw = getResources().getDisplayMetrics().widthPixels;
//				offest = (int) (Constant.displayWidth*0.2*arg0 - sw/2 + (Constant.displayWidth*0.2*(arg0+1)/2));
				//一个小的投机取巧的方式，我没有使用计算得出的偏移量来设置滚动，而是根据这个方法的特性显示出下一列的最后一个数字，这样下一列就完全显示出来从而达到可操控的滚动
				mRecyclerView.scrollToPosition(arg0*4);
				//从ScrollToPosition方法切换过来的，不平滑滚动的话会出现两个问题：
				//1是视图无法滚动到最左边，二是当手动去滚动至最左边的时候item的位置出现错乱（虽然会自动恢复，但是有明显的视觉延迟）
//				mRecyclerView.scrollTo(offest, 0);
//				if(arg0>9){
//					String string=String.valueOf(arg0).substring(1);
//					int i = Integer.parseInt(string);
//					mRecyclerView.scrollToPosition(i*4+3);
////					mRecyclerView.scrollBy(x, y);  
//				}
			}
			/** 此方法在滑动ViewPager的时候一直被调用，页面在滑动过程中不停触发该方法：�?�position”按照api的解释是“目前显示在屏幕上的第一个页面，只要positionOffset不为0，那么他后面的页面同样是可见的�??
       第一页~第二页
       position = 0
       positionOffset  0.0 ~ 1.0
       第二页~第一页 
       position = 0
       positionOffset  1.0 ~ 0.0
       通过上面的结果，由于position的�?�在切换第一页和第二页的时�?�没有变化，就可以同过position+1得到右边的view，�?�过position拿到左边的view
       position                   不论是滑动还是静止，他表示的都是屏幕�?左边的页�?
       positionOffset           移量的百分比
       positionOffsetPixels   偏移量的数值*/
			@Override
			public void onPageScrolled(int position, float offest, int offestPixes) {
			}
     /**当页面的滑动状态改变时该方法会被触发，页面的滑动状态有3个：0表示什么都不做，1表示开始滑动，�?2”表示结束滑�?*/			  
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
 			}
		});
	}

    
    /*获取屏幕尺寸*/
	private void getDispaly(final View view) {
		// TODO Auto-generated method stub
		DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Constant.displayWidth = displayMetrics.widthPixels;
        Constant.displayHeight = displayMetrics.heightPixels;
		/* 
		* 获取控件高 ,800*1216,算出来800*485，XML中本来设置30%屏幕高度大小
		*/ 
        int widthMeasureSpec = MeasureSpec.makeMeasureSpec((int)MainActivity.this.getResources().getDisplayMetrics().density *Constant.displayWidth,MeasureSpec.EXACTLY);
        int heightMeasureSpec = MeasureSpec.makeMeasureSpec((int) (MainActivity.this.getResources().getDisplayMetrics().density *Constant.displayHeight*0.3),MeasureSpec.EXACTLY);  
        view.measure(widthMeasureSpec,heightMeasureSpec);  
        Constant.recycleviewHeight = view.getMeasuredHeight(); 
        int recycleviewwidth = view.getMeasuredWidth();
	}
	//这段代码无关，屏掉即可，是测试图片的tint属性的
	public void imageclick(View view){
		Drawable drawable=ContextCompat.getDrawable(this, R.drawable.ic_launcher);
		int[] colors=new int[]{ContextCompat.getColor(this, R.color.blue),ContextCompat.getColor(this, R.color.black)};
		int[][] states=new int[2][];
		states[0]=new int[]{android.R.attr.state_pressed};
		states[1]=new int[]{};
        ColorStateList colorStateList=new ColorStateList(states, colors);
        StateListDrawable stateListDrawable=new StateListDrawable();
        stateListDrawable.addState(states[0], drawable);
        stateListDrawable.addState(states[1], drawable);
        Drawable.ConstantState constantState=stateListDrawable.getConstantState();
        drawable=DrawableCompat.wrap(constantState==null?stateListDrawable:constantState.newDrawable()).mutate();
        DrawableCompat.setTintList(drawable, colorStateList);
        imageView.setImageDrawable(drawable);
	}
	private	class MyPagerAdapter extends FragmentPagerAdapter{

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}
		@Override
		public Fragment getItem(int arg0) {
			return fraList.get(arg0);
		}
		@Override
		public int getCount() {
			return fraList.size();
		}
	}
}