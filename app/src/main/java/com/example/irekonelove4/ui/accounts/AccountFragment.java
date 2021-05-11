package com.example.irekonelove4.ui.accounts;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.irekonelove4.AllOf;
import com.example.irekonelove4.ApiClient;
import com.example.irekonelove4.DataRequest.FirstPrReq;
import com.example.irekonelove4.Enterance;
import com.example.irekonelove4.KnigaAbout;
import com.example.irekonelove4.PageFragmentforAccount;
import com.example.irekonelove4.R;
import com.example.irekonelove4.ui.home.HomeFragment;
import com.example.irekonelove4.ui.search.SearchFragment;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


public class AccountFragment extends Fragment {

    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_NAME = "Name";
    public static final String APP_PREFERENCES_AGE = "Age";
    public static final String APP_PREFERENCES_LOGIN = "Login";
    public static final String APP_PREFERENCES_CITY = "City";
    public static final String APP_PREFERENCES_MAIL = "Mail";
    public static final String APP_PREFERENCES_PASSWORD = "Pass";
    public static final String APP_PREFERENCES_SEX = "00";
    public static final String APP_PREFERENCES_AVATAR = "AVATAR";
    public static final String APP_PREFERENCES_ID = "ID";
    public static final String APP_PREFERENCES_QUOTE = "Quote";
    public static final String APP_PREFERENCES_FAVOURITE = "Fav";
    public static final String APP_PREFERENCES_RECOMMEND_GENRES = "GENRES";
    public static final String APP_PREFERENCES_RECOMMEND_AUTHORS = "AUTHORS";
    public static final String APP_PREFERENCES_INTERVIEW = "Interview";
    public static final String APP_PREFERENCES_DATA = "Data";

    private final int Pick_image = 1;
    int onlyone = 0;
    static final int GALLERY_REQUEST = 1;
    private ImageView avatar;

    private URL url;
    private String fullstring;
    private TextView name, age, city,info_about;
    private FirstPrReq loginResponse;
    private LayoutInflater inflater;
    private LinearLayout lin2;


    static final int PAGE_COUNT = 2;
    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    public SharedPreferences mSettings;

    private String favourites;

    private LottieAnimationView lottie;
    private Animation anim;
    private RelativeLayout rel_anim;
    private Button add, logOut, entr;

    private ConstraintLayout cons_for_log;
    private View header;
    private ScrollView big_info;

    public static final String APP_PREFS_NAME = SyncStateContract.Constants.class.getPackage().getName();
    public static final String APP_CACHE_PATH =
            Environment.getExternalStorageDirectory().getAbsolutePath() +
                    "/Android/data/" + APP_PREFS_NAME + "/cache/";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_accounts, container, false);

        cons_for_log = view.findViewById(R.id.cons_for_Log);
        header = view.findViewById(R.id.Tool);
        big_info = view.findViewById(R.id.big_info);

        entr =view.findViewById(R.id.goToLog);

        anim = AnimationUtils.loadAnimation(getContext(),R.anim.go_back);
        TextView tools_name = (TextView) view.findViewById(R.id.fragment_name);
        tools_name.setText("Аккаунт");
        name = (TextView) view.findViewById(R.id.name);
        age = (TextView) view.findViewById(R.id.age);
        city = view.findViewById(R.id.city);
        avatar = (ImageView) view.findViewById(R.id.avatar);
        info_about = view.findViewById(R.id.infoaboutyourself);
        add = view.findViewById(R.id.goToAdd);
        logOut = view.findViewById(R.id.log_out);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.navigation_addition);
            }
        });

        avatar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //Вызываем стандартную галерею для выбора изображения с помощью Intent.ACTION_PICK:
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                //Тип получаемых объектов - image:
                photoPickerIntent.setType("image/*");
                //Запускаем переход с ожиданием обратного результата в виде информации об изображении:
                startActivityForResult(photoPickerIntent, Pick_image);
            }
        });


        mSettings = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        name.setText(mSettings.getString(APP_PREFERENCES_NAME, "Name"));
        age.setText(mSettings.getString(APP_PREFERENCES_AGE, "Age") + ", ");
        city.setText(mSettings.getString(APP_PREFERENCES_CITY, "City"));
        info_about.setText(mSettings.getString(APP_PREFERENCES_QUOTE, "Quote"));

        pager = (ViewPager) view.findViewById(R.id.pager);  ///////////////////////////////////////////ИЗБРАННОЕ И ДОБАВЛЕННОЕ
        pagerAdapter = new MyFragmentPagerAdapter(getFragmentManager());
        pager.setAdapter(pagerAdapter);
        final Drawable text_line = getResources().getDrawable(R.drawable.text_line);
        final Drawable text_line_1 = getResources().getDrawable(R.drawable.text_line_1);
        final TextView aut = view.findViewById(R.id.po_izbr);
        final TextView kn = view.findViewById(R.id.po_dobav);
        aut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aut.setBackground(text_line);
                kn.setBackground(text_line_1);
                pager.setCurrentItem(0);
            }
        });
        kn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kn.setBackground(text_line);
                aut.setBackground(text_line_1);
                pager.setCurrentItem(1);
            }
        });


        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {

                    aut.setBackground(text_line);
                    kn.setBackground(text_line_1);
                } else if (position == 1) {

                    kn.setBackground(text_line);
                    aut.setBackground(text_line_1);
                    if(onlyone == 0){
                        AllInfooo();
                        onlyone++;
                    }

                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });////////////////////////////////////////////////////////////////
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = mSettings.edit();
                editor.putString(APP_PREFERENCES_NAME, "Name");
                editor.putString(APP_PREFERENCES_AGE, "Age");
                editor.putString(APP_PREFERENCES_CITY, "City");
                editor.putString(APP_PREFERENCES_LOGIN, "Login");
                editor.putString(APP_PREFERENCES_PASSWORD, "Pass");
                editor.putString(APP_PREFERENCES_MAIL, "Mail");
                editor.putString(APP_PREFERENCES_FAVOURITE,"Fav");
                editor.putString(APP_PREFERENCES_INTERVIEW,"Interview");
                editor.putString(APP_PREFERENCES_ID,"ID");
                editor.putString(APP_PREFERENCES_QUOTE,"Quote");
                editor.putString(APP_PREFERENCES_RECOMMEND_AUTHORS,"AUTHORS");
                editor.putString(APP_PREFERENCES_RECOMMEND_GENRES,"GENRES");
                editor.putString(APP_PREFERENCES_SEX,"00");
                editor.putString(APP_PREFERENCES_AVATAR, "AVATAR");
                editor.apply();
                Intent intent = new Intent(getContext(), Enterance.class);
                startActivityForResult(intent, 1);
                getActivity().overridePendingTransition(R.anim.left_anim, R.anim.alpha_to_zero);
            }
        });
        entr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Enterance.class);
                startActivity(intent);
            }
        });

        if(!mSettings.getString(APP_PREFERENCES_AGE,"Age").equals("Age")) {
            big_info.setVisibility(View.VISIBLE);
            header.setVisibility(View.VISIBLE);

            AllInfoo();

        }

        return view;
    }

    private class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PageFragmentforAccount.newInstance(position);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

    }

    //Обрабатываем результат выбора в галерее:
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case Pick_image:
                if (resultCode == RESULT_OK) {
                    try {
                        ////////////////////////////////Здесь мы должны залить фотку в бд и в след раз брать оттуда
                        //Получаем URI изображения, преобразуем его в Bitmap
                        //объект и отображаем в элементе ImageView нашего интерфейса:
                        final Uri imageUri = imageReturnedIntent.getData();
                        final InputStream imageStream = getContext().getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        avatar.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

        }
    }


    public void AllInfoo() {
        final Call<FirstPrReq> loginDataCall = ApiClient.getUserService().allInfo();
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(20, 20, 20, 0);

        inflater = getLayoutInflater();
        loginDataCall.enqueue(new Callback<FirstPrReq>() {
            @Override
            public void onResponse(Call<FirstPrReq> call, Response<FirstPrReq> response) {
                if (response.isSuccessful()) {

                    loginResponse = response.body();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                                    try {
                                        Glide.with(getContext()).load("http://irek.studio/images/avatars/" + mSettings.getString(APP_PREFERENCES_AVATAR, "avatar"))
                                                .into(avatar);
                                    }catch (Exception e){

                                    }
                        }
                    }, 1000);


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

    public void AllInfooo(){
        final Call<FirstPrReq> loginDataCall = ApiClient.getUserService().allInfo();
        inflater = getLayoutInflater();

        loginDataCall.enqueue(new Callback<FirstPrReq>() {
            @Override
            public void onResponse(Call<FirstPrReq> call, Response<FirstPrReq> response) {
                if (response.isSuccessful()) {
                    loginResponse = response.body();
                    lin2 = pager.findViewById(R.id.lin2);
                    rel_anim = pager.findViewById(R.id.rel_for_anim_dob);
                    lottie = pager.findViewById(R.id.animation2);
                    new CountDownTimer(3000, 500) {

                        public void onTick(long millisUntilFinished) {

                        }

                        public void onFinish() {
                            rel_anim.startAnimation(anim);
                            lottie.startAnimation(anim);
                            anim.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    rel_anim.setVisibility(View.GONE);
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                        }
                    }.start();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            for (int i = 0; i < loginResponse.getBooks().size(); i++) {
                                                                                                        //////////ЛОГИН ЮЗЕРА!!!!!!!!!!!!!!!!!!!!!
                                if (loginResponse.getBooks().get(i).getUserAuthor().equals(mSettings.getString(APP_PREFERENCES_LOGIN,"Login"))) {
                                    View view = inflater.inflate(R.layout.customkitapforcarusel,null);
                                    String urll = loginResponse.getBooks().get(i).getImg();
                                    ImageView imageView = view.findViewById(R.id.KitapImage);
                                    urll.trim().replaceAll(" ", "%20");
                                    try {
                                        Glide.with(getContext()).load("http://irek.studio/books/books_img/" + urll).into(imageView);
                                    }catch (Exception e){

                                    }//                                        Image image = ImageIO.read(url);
                                    TextView name = view.findViewById(R.id.name);
                                    name.setText(loginResponse.getBooks().get(i).getName());
                                    TextView autor = view.findViewById(R.id.autor);
                                    autor.setText(loginResponse.getBooks().get(i).getAuthor());
                                    view.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            bigClick(v);
                                        }
                                    });


                                    lin2.addView(view);
                                }
                            }
                        }
                    }, 1000);


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

                        try {
                            url = new URL("http://irek.studio/books/books_text/books_txt/" + loginResponse.getBooks().get(i).getName().trim().replaceAll(" ","%20")+"_tat.txt");
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
                                intent.putExtra("lang", loginResponse.getBooks().get(finalI).getLanguage());
                                intent.putExtra("genre", loginResponse.getBooks().get(finalI).getGenre());
                                intent.putExtra("lang1", loginResponse.getBooks().get(finalI).getLanguageOriginal());
                                intent.putExtra("tag", loginResponse.getBooks().get(finalI).getTags());
                                intent.putExtra("kol_str", loginResponse.getBooks().get(finalI).getSizeOfBookPages());
                                intent.putExtra("who", loginResponse.getBooks().get(finalI).getUserAuthor());
                                intent.putExtra("how_many", loginResponse.getBooks().get(finalI).getViews());
                                intent.putExtra("img", loginResponse.getBooks().get(finalI).getImg());
                                intent.putExtra("url", url.toString());
                                intent.putExtra("text", fullstring);
                                Log.d("1",loginResponse.getBooks().get(finalI).getLanguage()+" " +loginResponse.getBooks().get(finalI).getLanguageOriginal()
                                        + " " + loginResponse.getBooks().get(finalI).getTags()+ " " + loginResponse.getBooks().get(finalI).getSizeOfBookPages()
                                        + " "+loginResponse.getBooks().get(finalI).getUserAuthor()+ " " + loginResponse.getBooks().get(finalI).getViews());
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
}