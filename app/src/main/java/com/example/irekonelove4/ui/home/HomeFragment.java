package com.example.irekonelove4.ui.home;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.example.irekonelove4.ApiClient;
import com.example.irekonelove4.DataRequest.FirstPrReq;
import com.example.irekonelove4.KnigaAbout;
import com.example.irekonelove4.R;
import com.example.irekonelove4.SliderAdapter;
import com.example.irekonelove4.SliderItem;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private ScrollView scrollView_inHome;
    private ViewPager2  viewPager1, viewPager2, viewPager3, viewPager4;
    private Handler sliderHandler4 = new Handler();
    private Handler sliderHandler1 = new Handler();
    private Handler sliderHandler2 = new Handler();
    private Handler sliderHandler3 = new Handler();
    private int where = 1;
    private URL url;

    SliderItem[] items2 = new SliderItem[5];
    SliderItem[] items1 = new SliderItem[5];
    SliderItem[] items3 = new SliderItem[5];
    SliderItem[] items4 = new SliderItem[5];
    private FirstPrReq loginResponse;
    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView tools_name = (TextView) view.findViewById(R.id.fragment_name);
        ConstraintLayout allin = (ConstraintLayout) view.findViewById(R.id.allIN);
        scrollView_inHome = (ScrollView) view.findViewById(R.id.allkarusel_in_home);
        viewPager4 = view.findViewById(R.id.ViewPagerSliderImage4);
        viewPager1 = view.findViewById(R.id.ViewPagerSliderImage1);
        viewPager2 = view.findViewById(R.id.ViewPagerSliderImage2);
        viewPager3 = view.findViewById(R.id.ViewPagerSliderImage3);
        List<SliderItem> sliderItems4 = new ArrayList<>();
        items4[0] = new SliderItem(R.drawable.back_for_no_kniga,"AYF1","BOH1");
        items4[1] = new SliderItem(R.drawable.back_for_no_kniga,"AYF1","BOH1");
        items4[2] = new SliderItem(R.drawable.back_for_no_kniga,"AYF1","BOH1");
        items4[3] = new SliderItem(R.drawable.back_for_no_kniga,"AYF1","BOH1");
        items4[4] = new SliderItem(R.drawable.back_for_no_kniga,"AYF1","BOH1");
        sliderItems4.add(items4[0]);
        sliderItems4.add(items4[1]);
        sliderItems4.add(items4[2]);
        sliderItems4.add(items4[3]);
        sliderItems4.add(items4[4]);
        viewPager4.setAdapter(new SliderAdapter(sliderItems4,viewPager4));
        viewPager4.setClipToPadding(false);
        viewPager4.setClipChildren(false);
        viewPager4.setOffscreenPageLimit(3);
        viewPager4.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);


        final List<SliderItem> sliderItems1 = new ArrayList<>();
        items1[0] = new SliderItem(R.drawable.back_for_no_kniga,"AYF1","BOH1");
        items1[1] = new SliderItem(R.drawable.back_for_no_kniga,"AYF1","BOH1");
        items1[2] = new SliderItem(R.drawable.back_for_no_kniga,"AYF1","BOH1");
        items1[3] = new SliderItem(R.drawable.back_for_no_kniga,"AYF1","BOH1");
        items1[4] = new SliderItem(R.drawable.back_for_no_kniga,"AYF1","BOH1");
        sliderItems1.add(items1[0]);
        sliderItems1.add(items1[1]);
        sliderItems1.add(items1[2]);
        sliderItems1.add(items1[3]);
        sliderItems1.add(items1[4]);
        viewPager1.setAdapter(new SliderAdapter(sliderItems1,viewPager1));
        viewPager1.setClipToPadding(false);
        viewPager1.setClipChildren(false);
        viewPager1.setOffscreenPageLimit(3);
        viewPager1.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);


        List<SliderItem> sliderItems2 = new ArrayList<>();
        items2[0] = new SliderItem(R.drawable.back_for_no_kniga,"AYF1","BOH1");
        items2[1] = new SliderItem(R.drawable.back_for_no_kniga,"AYF1","BOH1");
        items2[2] = new SliderItem(R.drawable.back_for_no_kniga,"AYF1","BOH1");
        items2[3] = new SliderItem(R.drawable.back_for_no_kniga,"AYF1","BOH1");
        items2[4] = new SliderItem(R.drawable.back_for_no_kniga,"AYF1","BOH1");
        sliderItems2.add(items2[0]);
        sliderItems2.add(items2[1]);
        sliderItems2.add(items2[2]);
        sliderItems2.add(items2[3]);
        sliderItems2.add(items2[4]);
        viewPager2.setAdapter(new SliderAdapter(sliderItems2,viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);


        List<SliderItem> sliderItems3 = new ArrayList<>();
        items3[0] = new SliderItem(R.drawable.back_for_no_kniga,"AYF1","BOH1");
        items3[1] = new SliderItem(R.drawable.back_for_no_kniga,"AYF1","BOH1");
        items3[2] = new SliderItem(R.drawable.back_for_no_kniga,"AYF1","BOH1");
        items3[3] = new SliderItem(R.drawable.back_for_no_kniga,"AYF1","BOH1");
        items3[4] = new SliderItem(R.drawable.back_for_no_kniga,"AYF1","BOH1");
        sliderItems3.add(items3[0]);
        sliderItems3.add(items3[1]);
        sliderItems3.add(items3[2]);
        sliderItems3.add(items3[3]);
        sliderItems3.add(items3[4]);
        viewPager3.setAdapter(new SliderAdapter(sliderItems3,viewPager3));
        viewPager3.setClipToPadding(false);
        viewPager3.setClipChildren(false);
        viewPager3.setOffscreenPageLimit(3);
        viewPager3.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        AllInfoo();

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();

        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        viewPager1.setPageTransformer(compositePageTransformer);
        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager3.setPageTransformer(compositePageTransformer);
        viewPager4.setPageTransformer(compositePageTransformer);





        scrollView_inHome.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {


                int location[][] = new int[4][2];
                viewPager1.getLocationInSurface(location[0]);
                viewPager2.getLocationInSurface(location[1]);
                viewPager3.getLocationInSurface(location[2]);
                viewPager4.getLocationInSurface(location[3]);
                Log.d("1", String.valueOf(where)+" " +String.valueOf(location[0][1])+" " + String.valueOf(location[1][1])+" " +String.valueOf(location[2][1])+" " +String.valueOf(location[3][1]));
                int wtff1 = 0,wtff2 = 0,wtff3 = 0,wtff4 = 0;
                if(location[0][1] > 0 && wtff1 == 0) {
                    sliderHandler1.removeCallbacks(sliderRunnable1);
                    sliderHandler1.postDelayed(sliderRunnable1, 2000);
                        where = 1;
                        wtff1 = 1;
                        wtff2 = 0;
                        wtff3 = 0;
                        wtff1 = 0;

                }
                if(location[1][1] > 0 && location[0][1] < 0 && wtff2 == 0) {
                    where = 2;
                    sliderHandler2.removeCallbacks(sliderRunnable2);
                    sliderHandler2.postDelayed(sliderRunnable2, 2000);
                    wtff1 = 0;
                    wtff2 = 1;
                    wtff3 = 0;
                    wtff4 = 0;

                }
                if(location[2][1] > 0 && wtff3 == 0&& location[1][1] < 0) {
                    where = 3;
                    sliderHandler3.removeCallbacks(sliderRunnable3);
                    sliderHandler3.postDelayed(sliderRunnable3, 2000);
                    wtff1 = 0;
                    wtff2 = 0;
                    wtff3 = 1;
                    wtff4 = 0;
                }
                if(location[3][1] > 0 && wtff4 == 0 && location[2][1] < 0) {
                    where = 4;
                    sliderHandler4.removeCallbacks(sliderRunnable4);
                    sliderHandler4.postDelayed(sliderRunnable4, 2000);
                    wtff1 = 0;
                    wtff2 = 0;
                    wtff3 = 0;
                    wtff4 = 1;
                }
            }
        });
        viewPager4.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler4.removeCallbacks(sliderRunnable4);
                sliderHandler4.postDelayed(sliderRunnable4, 2000);
            }
        });
        viewPager1.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler1.removeCallbacks(sliderRunnable1);
                sliderHandler1.postDelayed(sliderRunnable1, 2000);
            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler2.removeCallbacks(sliderRunnable2);
                sliderHandler2.postDelayed(sliderRunnable2, 2000);
            }
        });
        viewPager3.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler3.removeCallbacks(sliderRunnable3);
                sliderHandler3.postDelayed(sliderRunnable3, 2000);
            }
        });
        tools_name.setText("Главная");



        return view;
    }

    private Runnable sliderRunnable1 = new Runnable(){
        @Override
        public void run() {
            if(where == 1) {
                viewPager1.setCurrentItem(viewPager1.getCurrentItem() + 1);
            }
        }
    };
    private Runnable sliderRunnable2 = new Runnable(){
        @Override
        public void run() {

            if(where == 2) {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
            }
        }
    };
    private Runnable sliderRunnable3 = new Runnable(){
        @Override
        public void run() {

            if(where == 3) {
                viewPager3.setCurrentItem(viewPager3.getCurrentItem() + 1);
            }
        }
    };
    private Runnable sliderRunnable4 = new Runnable(){
        @Override
        public void run() {

            if(where == 4) {
                viewPager4.setCurrentItem(viewPager4.getCurrentItem() + 1);
            }
        }

    };





    public void AllInfoo() {
        Log.d("2", "fuck");
        final Call<FirstPrReq> loginDataCall = ApiClient.getUserService().allInfo();


        loginDataCall.enqueue(new Callback<FirstPrReq>() {
            @Override
            public void onResponse(Call<FirstPrReq> call, Response<FirstPrReq> response) {
                if (response.isSuccessful()) {
                    loginResponse = response.body();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            int last = 0;
                            for(int j = 0; j < 5; j++){
                                int[] pop = new int[5];
                                for(int i = last; i < loginResponse.getBooks().size(); i++) {

                                    if (i < 4) {
                                        last = i + 1;

                                        String urll = loginResponse.getBooks().get(i).getImg();
                                        urll.trim().replaceAll(" ", "%20");
                                        URL url = null;
                                        try {
                                            url = new URL("http://irek.studio/books/books_img/"+urll);
                                        } catch (MalformedURLException e) {
                                            e.printStackTrace();
                                        }
//                                        Image image = ImageIO.read(url);
                                        items1[j].setAutor(loginResponse.getBooks().get(i).getAuthor());
                                        items1[j].setName(loginResponse.getBooks().get(i).getName());
                                        //ImageView imageView = viewPager1.findViewById(R.id.)
                                        Log.d("1", "gg");
                                        j++;
                                        //Glide.with(getContext()). load ("http://irek.studio/books/books_img/"+urll).into(image);
                                    } else {

                                        last = i + 1;

                                        String urll = loginResponse.getBooks().get(i).getImg();
                                        urll.trim().replaceAll(" ", "%20");
                                        items1[j].setAutor(loginResponse.getBooks().get(i).getAuthor());
                                        items1[j].setName(loginResponse.getBooks().get(i).getName());
                                        break;
                                    }
                                }
                            }
                        }
                    },1000);


                } else {
                    Toast.makeText(getContext(), "Неправильный логин или пароль!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FirstPrReq> call, Throwable t) {
                Toast.makeText(getContext(), "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Error", t.getLocalizedMessage());
            }
        });

    }

    public void bigClick(View view){

        final TextView name = view.findViewById(R.id.name);
        final TextView autor = view.findViewById(R.id.autor);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int last = 0;
                for(int i = 0; i < loginResponse.getBooks().size(); i++){
                    if(loginResponse.getBooks().get(i).getName().equals(name.getText().toString())){
                        if(loginResponse.getBooks().get(i).getLanguage().equals("Татарский")) {
                            try {
                                url = new URL("http://irek.studio/books/books_text/books_txt/" + loginResponse.getBooks().get(i).getName().trim().replaceAll(" ","%20") + "_tat.txt");
                               // new AsyncRequest().execute("123", "/ajax", "foo=bar");
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                        }

                        Timer timer = new Timer();
                        final int finalI = i;
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {

                                Intent intent = new Intent(getContext(), KnigaAbout.class);
                                intent.putExtra("name",name.getText().toString());
                                intent.putExtra("autor",autor.getText().toString());
                                intent.putExtra("lang", loginResponse.getBooks().get(finalI).getLanguage());
                                intent.putExtra("genre", loginResponse.getBooks().get(finalI).getGenre());
                                intent.putExtra("lang1", loginResponse.getBooks().get(finalI).getLanguageOriginal());
                                intent.putExtra("tag", loginResponse.getBooks().get(finalI).getTags());
                                intent.putExtra("kol_str", loginResponse.getBooks().get(finalI).getSizeOfBookPages());
                                intent.putExtra("who", loginResponse.getBooks().get(finalI).getUserAuthor());
                                intent.putExtra("how_many", loginResponse.getBooks().get(finalI).getViews());
                                intent.putExtra("img", loginResponse.getBooks().get(finalI).getImg());
                                intent.putExtra("text", "");
                                Log.d("1",loginResponse.getBooks().get(finalI).getLanguage()+" " +loginResponse.getBooks().get(finalI).getLanguageOriginal()
                                        + " " + loginResponse.getBooks().get(finalI).getTags()+ " " + loginResponse.getBooks().get(finalI).getSizeOfBookPages()
                                        + " "+loginResponse.getBooks().get(finalI).getUserAuthor()+ " " + loginResponse.getBooks().get(finalI).getViews());
                                startActivity(intent);
                            }
                        },1000);
                        /*else {
                            url = new URL("http://irek.studio/books/books_text/books_txt/" + loginResponse.getBooks().get(i).getName().trim().replaceAll(" ","%20")+".txt");
                        }*/
                    }
                }
            }
        }, 1000);
    }










    @Override
    public void onResume() {
        super.onResume();
        sliderHandler4.postDelayed(sliderRunnable4, 2000);
        sliderHandler3.postDelayed(sliderRunnable3, 2000);
        sliderHandler2.postDelayed(sliderRunnable2, 2000);
        sliderHandler1.postDelayed(sliderRunnable1, 2000);
    }

}