package com.example.irekonelove4;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.irekonelove4.DataRequest.BookForJson;
import com.example.irekonelove4.DataRequest.PopularReq;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageFragmentforHome extends Fragment {

    static final String TAG = "myLogs";

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    static final String SAVE_PAGE_NUMBER = "save_page_number";

    int pageNumber;
    private List<BookForJson> pop_req;
    private LayoutInflater inflater;
    private LinearLayout lin1, lin2;
    private Button all;
    private String favourites;
    int[] fav;
    private LottieAnimationView lottie;
    private URL url;
    private String fullstring;
    private Animation anim;
    private RelativeLayout rel_anim;

    public static PageFragmentforHome newInstance(int page) {
        PageFragmentforHome pageFragment = new PageFragmentforHome();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
        int savedPageNumber = -1;
        if (savedInstanceState != null) {
            savedPageNumber = savedInstanceState.getInt(SAVE_PAGE_NUMBER);
        }
        Log.d(TAG, "savedPageNumber = " + savedPageNumber);

    }
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.custom_kitap_in_home, null);

        AllInfoo(pageNumber);

        return view;
    }
    int j = 0;
    public void AllInfoo(int state) {
        Call<List<BookForJson>> pop_request = ApiClient.getUserService().popInfo();
        inflater = getLayoutInflater();
            pop_request.enqueue(new Callback<List<BookForJson>>() {
                @Override
                public void onResponse(Call<List<BookForJson>> call, Response<List<BookForJson>> response) {
                    if (response.isSuccessful()) {
                        pop_req = response.body();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                        ImageView imageView = view.findViewById(R.id.KitapImage);
                        TextView name = view.findViewById(R.id.name);
                        TextView autor = view.findViewById(R.id.autor);
                        TextView about = view.findViewById(R.id.about);
                        name.setText(pop_req.get(pageNumber).getName());
                        autor.setText(pop_req.get(pageNumber).getAuthor());
                        about.setText(pop_req.get(pageNumber).getBriefly());
                        try {
                            Glide.with(getContext()).load("http://irek.studio/books/books_img/" + pop_req.get(pageNumber).getImg()).into(imageView);
                        } catch (Exception e) {

                        }
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                bigClick(v);
                            }
                        });
                        }
                    },250);
                        /*new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                for (int i = 0; i < pop_req.getPopBooks().size(); i++) {
                                        if(i == pageNumber) {
                                            ImageView imageView = view.findViewById(R.id.KitapImage);
                                            TextView name = view.findViewById(R.id.name);
                                            TextView autor = view.findViewById(R.id.autor);
                                            TextView about = view.findViewById(R.id.about);
                                            name.setText(pop_req.getPopBooks().get(i).getName());
                                            autor.setText(pop_req.getPopBooks().get(i).getAuthor());
                                            about.setText(pop_req.getPopBooks().get(i).getBriefly());
                                            try {
                                                Glide.with(getContext()).load("http://irek.studio/books/books_img/" + pop_req.getPopBooks().get(i).getImg()).into(imageView);
                                            } catch (Exception e) {

                                            }
                                            view.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    bigClick(v);
                                                }
                                            });
                                        }
                                }

                            }
                        },100);*/


                    } else {
                        Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<BookForJson>> call, Throwable t) {
                    Toast.makeText(getContext(),t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
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
                for(int i = 0; i < pop_req.size(); i++){
                    if(pop_req.get(i).getName().equals(name.getText().toString())){

                        try {
                            url = new URL("http://irek.studio/books/books_text/books_txt/" + pop_req.get(i).getName().trim().replaceAll(" ","%20")+"_tat.txt");
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        new AsyncRequest().execute("123", "/ajax", "foo=b'ar");

                        Timer timer = new Timer();
                        final int finalI = i;
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(getContext(), KnigaAbout.class);
                                intent.putExtra("name",name.getText().toString());
                                intent.putExtra("autor",autor.getText().toString());
                                intent.putExtra("lang", pop_req.get(finalI).getLanguage());
                                intent.putExtra("genre", pop_req.get(finalI).getGenre());
                                intent.putExtra("lang1", pop_req.get(finalI).getLanguageOriginal());
                                intent.putExtra("tag", pop_req.get(finalI).getTags());
                                intent.putExtra("kol_str", pop_req.get(finalI).getSizeOfBookPages());
                                intent.putExtra("who", pop_req.get(finalI).getUserAuthor());
                                intent.putExtra("how_many", pop_req.get(finalI).getViews());
                                intent.putExtra("img", pop_req.get(finalI).getImg());
                                intent.putExtra("about", pop_req.get(finalI).getBriefly());
                                intent.putExtra("url", url.toString());
                                intent.putExtra("text", fullstring);
                                Log.d("1", pop_req.get(finalI).getLanguage()+" " + pop_req.get(finalI).getLanguageOriginal()
                                        + " " + pop_req.get(finalI).getTags()+ " " + pop_req.get(finalI).getSizeOfBookPages()
                                        + " "+ pop_req.get(finalI).getUserAuthor()+ " " + pop_req.get(finalI).getViews());
                                startActivity(intent);
                            }
                        },200);
                    }
                }
            }
        }, 800);
    }

    public class AsyncRequest extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            try {
                String string;


                LineNumberReader reader = new LineNumberReader(new InputStreamReader(url.openStream()));
                string = reader.readLine();
                fullstring = string;
                int  i = 1;
                while (string != null) {
                    i++;
                    //System.out.println(string);
                    string = reader.readLine();
                    fullstring = fullstring + string;
                    Log.d(String.valueOf(i), fullstring);
                }
                // Log.d("1",fullstring);
                reader.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVE_PAGE_NUMBER, pageNumber);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: " + pageNumber);
    }
}