# Modul 4 : HTTP Request

### Kalian akan belajar
Dalam Focus Group kali ini kalian akan mempelajari : 
1. Membuat Recyclerview dan Adapter
2. Melakukan request ke API yang sudah ada menggunakan Retrofit
3. Mengisi recyclerview dengan data yang benar dari API

### PART 1 : Recyclerview
Dalam praktik ini kalian akan mempelajari cara menerapkan spinner widget kedalam sebuah layout.

#### 1.1. Membuat Recyclerview
Buatlah sebuah projek baru, tambahkan dependency recyclerview seperti dibawah ke app.gradle(Module:app)  
```java
    implementation 'com.android.support:recyclerview-v7:28.0.0'
```
Kemudian tambahkan sebuah widget Recyclerview ke layout kalian  
contoh : 
```xml
    <android.support.v7.widget.RecyclerView
        android:id="@+id/listResep"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```


#### 1.2. Membuat tampilan list
1. Selanjutnya buatlah sebuah layout baru di res > layout > new layout resource file, gunakan kode dibawah untuk tampilan list kalian, kalian boleh memodifikasinya agar sesuai keinginan

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="180dp">
    <ImageView
        android:id="@+id/resepImage"
        android:background="@color/colorPrimary"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
    <View
        android:layout_alignParentBottom="true"
        android:background="#888"
        android:alpha="0.4"
        android:layout_width="match_parent"
        android:layout_height="70dp"/>
    <LinearLayout
        android:padding="8dp"
        android:orientation="vertical"
        android:layout_alignParentBottom="true"
        android:layout_width="match_parent"
        android:layout_height="70dp">

        <TextView
            android:textColor="@android:color/white"
            android:textSize="22sp"
            android:id="@+id/resepTitle"
            android:text="Title over here"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />
        <TextView
            android:textColor="@android:color/white"
            android:textSize="14sp"
            android:text="Calories : 3300"
            android:id="@+id/resepCalories"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />


    </LinearLayout>

</RelativeLayout>
```  
#### 1.3. Membuat kelas model
1. Buatlah sebuah kelas model Resep.java seperti contoh dibawah
```java
public class Resep {
    private String image;
    private String title;
    private String calories;

    public Resep(String image, String title, String calories) {
        this.image = image;
        this.title = title;
        this.calories = calories;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getCalories() {
        return calories;
    }
}
```


#### 1.4. Membuat adapter list
1. Buka app.gradle(Module:app) kalian kembali, dan tambahkan dependecy Picasso seperti dibawah  
Picasso akan kalian gunakan untuk load gambar dari internet
```java
    implementation 'com.squareup.picasso:picasso:2.71828'
```

2. Buka file Androidmanifest.xml kalian, tambahkan kode dibawah agar aplikasi kalian diberi permission untuk mengakses internet, letakkan kode di atas tag pembuka <application>
```xml
    <uses-permission android:name="android.permission.INTERNET" />
```

3. Buatlah sebuah kelas adapter, beri nama ResepAdapter.java seperti dibawah  
```java
public class ResepAdapter extends RecyclerView.Adapter<ResepAdapter.ViewHolder> {

    private List<Resep> resepList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, calories;
        public ImageView image;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.resepTitle);
            calories = (TextView) view.findViewById(R.id.resepCalories);
            image = (ImageView) view.findViewById(R.id.resepImage);
        }
    }

    public ResepAdapter(List<Resep> resepList) {
        this.resepList = resepList;
    }

    @Override
    public ResepAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_resep, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Resep resep = resepList.get(position);
        holder.title.setText(resep.getTitle());
        holder.calories.setText(resep.getCalories());
        Picasso.get()
                .load(resep.getImage())
                .fit()
                .centerCrop()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return resepList.size();
    }
}
```

#### 1.5 Mengimplementasikan adapter ke Recyclerview
1. Buatlah objek ResepAdapter di Activity kalian, dan inisiasikan ke recyclerview yang telah kalian buat sebelumnya seperti kode dibawah, sesuaikan dengan id dan nama variabel kalian
```java
        adapter = new ResepAdapter(listResep);
        RecyclerView recyclerView = findViewById(R.id.listResep);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
```

2. Buat sebuah data dummy untuk memastikan kode yang telah kalian buat benar
```java
    private void loadData(){
        Resep resep = new Resep("https://img-global.cpcdn.com/003_recipes/7ac31bcd8b05a075/751x532cq70/photo.jpg", "Pelecing Kangkung", "100 Calories");
        listResep.add(resep);
        adapter.notifyDataSetChanged();
    }
```

3. Panggil fungsi loadData() di activity kalian, contoh  
```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new ResepAdapter(listResep);
        RecyclerView recyclerView = findViewById(R.id.listResep);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadData();
    }
```

4. Jika kode benar, maka hasil akhir kurang lebih akan seperti ini
![alt text](https://i.imgur.com/FP6QRe0.png)

### PART 2 : Retrofit
Retrofit adalah salah satu library yang dapat digunakan untuk melakukan HTTP Request ke server
Disini kalian akan menggunakan Retrofit dengan GSON Converter
GSON Converter digunakan untuk mengubah json menjadi sebuah objek Java untuk mempermudah

![alt text](https://github.com/MobileInnovationLab/studygroup-2018/blob/master/Modul%204%20HTTP%20Request/json_notation.png)


#### 2.1 Tambahkan dependecy Retrofit dan GSON ke app.gradle(Module:app) kalian
```java
    implementation 'com.google.code.gson:gson:2.6.2'
    implementation 'com.squareup.retrofit2:retrofit:2.0.2'
    implementation 'com.squareup.retrofit2:converter-gson:2.0.2'
```

#### 2.2 Membuat kelas model untuk menerima respons server
1. Buatlah sebuah kelas Response.java seperti contoh dibawah
```java
    public class Response {
        @SerializedName("q")
        private String query;
        @SerializedName("hits")
        private List<Hits> hits = new ArrayList<>();

        public String getQuery() {
            return query;
        }

        public List<Hits> getHits() {
            return hits;
        }
    }
```

2. Lalu buatlah sebuah kelas Hits.java 
```java
    public class Hits {
        @SerializedName("recipe")
        private Resep resep;

        public Resep getResep() {
            return resep;
        }
    }
```

3. Buka kembali kelas resep.java dan ubahlah kode seperti dibawah
```java
    @SerializedName("image")
    private String image;
    @SerializedName("label")
    private String title;
    @SerializedName("calories")
    private String calories;
```


#### 2.3 Inisiasi pengaturan Retrofit dan GSON Converter
1. Buatlah sebuah kelas ApiClient.java seperti dibawah
```java
    public class ApiClient {

        public static final String BASE_URL = "https://api.edamam.com/";
        private static Retrofit retrofit = null;


        public static Retrofit getClient() {
            if (retrofit==null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }
    }
```

#### 2.4 Membuat interface EndPoint server
1. Buatlah sebuah interface seperti contoh dibawah 
Call<Response> = Response adalah objek yang telah kalian buat sebelumnya, disini semua hasil Call atau Request akan di return
 
```java
    public interface ApiInterface {
    @GET("search")
    public Call<Response> getRecipe(@Query("q") String query,
                                    @Query("app_id") String appId,
                                    @Query("app_key") String appKey
                                    );
    }
```
kode diatas akan menghasilkan end point seperti ini
https://api.edamam.com/search?q={query}n&app_id={appId}&app_key={appKey}


#### 2.5 Melakukan request di activity
1. Setelah semuanya di inisiasi, selanjutnya adalah menggunakan fungsi getRecipe yang telah kalian buat di kelas ApiInterface.java . Bukalah activity kalian dan panggil fungsi getRecipe di fungsi loadData() yang telah kalian buat sebelumnya seperti dibawah

```java
    private void loadData(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Response> call = apiService.getRecipe("chicken", "9bb5eb17", "a658bf7a755995dcf8644d6ac1ccb091");
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response>call, retrofit2.Response<Response> response) {
                List<Hits> hits = response.body().getHits();
                listResep.clear();
                for (int i=0;i<hits.size();i++) {
                    Resep resepServer = hits.get(i).getResep();
                    listResep.add(resepServer);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<Response>call, Throwable t) {
                Log.e("Request Failure", t.getMessage());
            }
        });
    }

```

2. Apabila kode kalian benar, maka tampilan akhir akan muncul seperti ini

![alt text](https://github.com/MobileInnovationLab/studygroup-2018/blob/master/Modul%204%20HTTP%20Request/Screenshot_2018-11-23-01-29-46-27.png)