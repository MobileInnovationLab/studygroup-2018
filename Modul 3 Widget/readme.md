# Modul 3 : Widget

### Kalian akan belajar
Dalam Focus Group kali ini kalian akan mempelajari : 
1. Menambahkan elemen Spinner untuk memilih satu item dari serangkaian item.
2. Menampilkan Dialog untuk memberikan feedback kepada pengguna dengan custom design.
3. Mengimplementasikan Cardview kedalam RecyclerView.`

### PART 1 : Widget Spinner
Dalam praktik ini kalian akan mempelajari cara menerapkan spinner widget kedalam sebuah layout.

#### 1.1. Membuat Layout dengan Spinner
Buatlah sebuah aktivitas dengan layout seperti berikut

![alt text](https://google-developer-training.gitbooks.io/android-developer-fundamentals-course-practicals/content/images/4_1_P_images/dg_phonenumberspinner_layout_annotated.png)

Pada Gambar diatas kalian akan membuat layout dengan 2 bagian yaitu : 
1. untuk memasukkan informasi nomor telepon
2. untuk menampilkan nomor telepon

Tambahkan kodingan berikut untuk membuat spinner, dan letakkan diantara EditText dan Tombol "Show" :
```xml
    . . . 
    <Spinner
       android:id="@+id/label_spinner"
       android:layout_width="wrap_content"
       android:layout_height="wrap_content">
    </Spinner>
    . . . 
```
#### 1.2. Tambahkan Kode untuk mengaktifkan spinner dan listener-nya
1. Buka **strings.xml** untuk mendefinisikan nilai yang bisa dipilih (**Home, Work, Mobile,** dan **Other**) untuk spinner sebagai larik string ``labels_array``.
```xml
<string-array name="labels_array">
        <item>Home</item>
        <item>Work</item>
        <item>Mobile</item>
        <item>Other</item>
</string-array>
```
2. Untuk mendefinisikan callback pilihan untuk spinner, ubah kelas `MainActivity` Anda untuk mengimplementasikan antarmuka `AdapterView.OnItemSelectedListener` seperti yang ditampilkan:
```java
public class MainActivity extends AppCompatActivity implements
            AdapterView.OnItemSelectedListener {
```
Selagi Anda mengetik AdapterView. di pernyataan di atas, Android Studio otomatis mengimpor widget AdapterView.
3. Klik bola lampu dan pilih **Implement methods**. Metode `onItemSelected()` dan `onNothingSelected()` yang diperlukan untuk `OnItemSelectedListener`, seharusnya sudah disorot, dan opsi "Insert @Override" harus dicentang. Klik **OK**.
4. Buat instance objek spinner di metode `onCreate()` menggunakan elemen Spinner di layout (`label_spinner`), dan tetapkan listener-nya (`spinner.setOnItemSelectedListener`) di metode `onCreate()`. Tambahkan kode ke metode `onCreate()`:
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
   ...
   // Create the spinner.
   Spinner spinner = (Spinner) findViewById(R.id.label_spinner);
   if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
   }
   ...
```
5. Lanjutkan mengedit metode ``onCreate()``, tambahkan pernyataan yang membuat ArrayAdapter dengan larik string (`labels_array`) menggunakan layout spinner sederhana yang disediakan oleh Android untuk setiap item (`layout.simple_spinner_item`):
```java
...
// Create ArrayAdapter using the string array and default spinner layout.
ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
            R.array.labels_array, android.R.layout.simple_spinner_item);
...
```
6. Tetapkan layout untuk pilihan spinner menjadi ``simple_spinner_dropdown_item``, lalu terapkan adaptor ke spinner:
```java
   ...
   // Specify the layout to use when the list of choices appears.
   adapter.setDropDownViewResource
                       (android.R.layout.simple_spinner_dropdown_item);
   // Apply the adapter to the spinner.
   if (spinner != null) {
            spinner.setAdapter(adapter);
   }
   ...
```

#### 1.3. Tambahkan kode untuk merespons pilihan pengguna
1. Deklarasikan string ``mSpinnerLabel`` di awal definisi kelas ``MainActivity``:
```java
public class MainActivity extends AppCompatActivity implements
               AdapterView.OnItemSelectedListener {
 private String mSpinnerLabel = "";
 ...
}
```
2. Tambahkan kode ke metode callback ``onItemSelected()`` kosong, seperti yang ditampilkan di bawah ini, untuk mengambil item yang dipilih pengguna menggunakan ``getItemAtPosition``, dan tetapkan ke ``mSpinnerLabel``.
```java
public void onItemSelected(AdapterView<?> adapterView, View view, int
               i, long l) {
   mSpinnerLabel = adapterView.getItemAtPosition(i).toString();
   showText(view);
}
```
3. Tambahkan kode ke metode callback ``onNothingSelected()`` yang kosong, seperti yang ditampilkan di bawah ini, untuk menampilkan pesan logcat jika tidak satu pun yang dipilih:
```java
public void onNothingSelected(AdapterView<?> adapterView) {
    Log.d(TAG, "onNothingSelected: ");
}
```
4. Jalankan dan lihat hasilnya seperti berikut :
![alt text](https://google-developer-training.gitbooks.io/android-developer-fundamentals-course-practicals/content/images/4_1_P_images/dg_spinner_composite.png)


### PART 2 : Default Dialog dan Custom Dialog
Kelas builder biasanya merupakan kelas member statis dari kelas yang dibangunnya. Anda menggunakan **AlertDialog.Builder** untuk membangun dialog peringatan standar, menggunakan `setTitle` untuk menyetel judulnya, `setMessage` untuk menyetel pesannya, dan `setPositiveButton` dan `setNegativeButton` untuk menyetel tombolnya.
#### 2.1. Menampilkan default Alert Dialog
1. Pada bagian ini kalian akan membuat aplikasi seperti berikut ini : 
![alt text](https://google-developer-training.gitbooks.io/android-developer-fundamentals-course-practicals/content/images/4_1_P_images/dg_alert_test_composite.png)

Untuk memulainya bualah terlebih dahulu activity baru dengan nama **AlertTest.java**
2. Tambahkan metode `onClickShowAlert()` ke **MainActivity.java** seperti berikut:
```java
public void onClickShowAlert(View view) {
  AlertDialog.Builder myAlertBuilder = new
                AlertDialog.Builder(MainActivity.this);
```
3. Setel judul dan pesan untuk dialog peringatan dalam `onClickShowAlert()` setel kode di langkah sebelumnya:
```java
...
// Set the dialog title.
myAlertBuilder.setTitle("Alert");
// Set the dialog message.
myAlertBuilder.setMessage("Click OK to continue, or Cancel to stop:");
...
```
4. Ekstrak judul dan pesan ke dalam sumber daya string. Baris kode sebelumnya sekarang seharusnya menjadi:
```java
...
// Set the dialog title.
myAlertBuilder.setTitle(R.string.alert_title);
// Set the dialog message.
myAlertBuilder.setMessage(R.string.alert_message);
... 
```
5. Tambahkan tombol **OK** pada peringatan dengan ``setPositiveButton()`` dan menggunakan ``onClickListener()``:
```java
...
// Add the buttons.
myAlertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
     public void onClick(DialogInterface dialog, int which) {
          // User clicked OK button.
          Toast.makeText(getApplicationContext(), "Pressed OK",
                  Toast.LENGTH_SHORT).show();
     }
});
...
```
6. Ekstrak sumber daya string untuk "**OK**" dan untuk "**Pressed OK**". Pernyataan sekarang seharusnya menjadi:
```java
...
// Add the buttons.
myAlertBuilder.setPositiveButton(R.string.ok, new
                      DialogInterface.OnClickListener() {
     public void onClick(DialogInterface dialog, int which) {
         // User clicked OK button.
         Toast.makeText(getApplicationContext(), R.string.pressed_ok,
                      Toast.LENGTH_SHORT).show();
     }
});
...
```
7. Tambahkan tombol Cancel pada peringatan dengan ``setNegativeButton()`` dan ``onClickListener()``, tampilkan pesan toast jika tombol diklik, lalu batalkan dialog:
```java
...
myAlertBuilder.setNegativeButton("Cancel", new      
                       DialogInterface.OnClickListener() {
     public void onClick(DialogInterface dialog, int which) {
          // User cancelled the dialog.
          Toast.makeText(getApplicationContext(), "Pressed Cancel",
                       Toast.LENGTH_SHORT).show();
     }
});
...
```
8. Ekstrak sumber daya string untuk "**Cancel**" dan "**Pressed Cancel**". Pernyataan sekarang seharusnya menjadi:
```java
...
myAlertBuilder.setNegativeButton(R.string.cancel, new
                       DialogInterface.OnClickListener() {
     public void onClick(DialogInterface dialog, int which) {
         // User cancelled the dialog.
         Toast.makeText(getApplicationContext(), R.string.pressed_cancel,
                       Toast.LENGTH_SHORT).show();
     }
});
...
```
9. Tambahkan ``show()`` yang membuat lalu menampilkan dialog peringatan, ke akhir `onClickShowAlert()`:
```java
  ...
  // Create and show the AlertDialog.
  myAlertBuilder.show();
}
```
10. Jalankan dan lihat hasilnya.

#### 2.2. Menampilkan custom Alert Dialog
1. Tambahkan button Custom Alert di **AlertTest.java** seperti berikut : 
![alt text](https://preview.ibb.co/fBCfi0/photo-2018-10-18-21-10-08.jpg)
2. Buat xml baru dengan nama dialog_custom.xml. lalu tambahkan kodingan berikut : 
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >

    <ImageView
        android:id="@+id/iv_dialog_custom"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginRight="@dimen/size4" />

    <TextView
        android:id="@+id/tv_dialog_custom"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="@dimen/size24"
        android:layout_toRightOf="@+id/iv_dialog_custom"/>

    <Button
        android:id="@+id/btn_dialog_custom"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/ok"
        android:layout_marginTop="@dimen/size4"
        android:layout_marginRight="@dimen/size4"
        android:layout_below="@+id/iv_dialog_custom"
        />

</RelativeLayout>
```
3. Buat method baru pada **AlertTest.java** dengan nama **onClickShowAlertCustom** yang berfungsi untuk menampilkan dialog baru.
```java
    public void onClickShowAlertCustom(View v){
     . . .
    }
```
4. Lalu Inisialisasi Dialog didalam method **onClickShowAlertCustom**.
```java
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_custom);
        dialog.setTitle("Title...");
```
5. Inisialisasi juga setiap element widget yang ada didalam `dialog_custom`. Seperti berikut :
```java
        TextView text = (TextView) dialog.findViewById(R.id.tv_dialog_custom);
        text.setText("Android custom dialog example!");
        ImageView image = (ImageView) dialog.findViewById(R.id.iv_dialog_custom);
        image.setImageResource(R.drawable.ic_keyboard_arrow_right);
        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog_custom);
```
6. Tambahkan aksi onClick pada `dialogButton`, yang berungsi agar menutup dialog pada saat di klik. Seperti Berikut :
```java
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
```
7. Setelah itu Tampilkan dialog dengan kode berikut : 
```java
        dialog.show();
```
dan jalankan, maka hasilnya akan seperti berikut ini : 
![alt text](https://preview.ibb.co/gZkNwL/photo-2018-10-18-21-10-07.jpg)


### PART 3 : Menggunakan Cardview dan Picasso
#### 3.1. Membuat CardView
Hasil akhir dari bagian ini adalah sebagai berikut : 
![alt text](https://preview.ibb.co/jDHkGL/photo-2018-10-18-21-30-08.jpg)
Lalu untuk membuatnya sebagai berikut :
1. buat activity baru 
2. Di file build.gradle tingkat aplikasi Anda, tambahkan baris berikut ke blok dependensi:
```
    implementation 'com.android.support:cardview-v7:27.1.1'
```
3. Untuk membuat CardView dapat menggunakan ``android.support.v7.widget.CardView`` sebagai layout. Seperti berikut ini :
```xml
    <android.support.v7.widget.CardView
        android:layout_width="match_parent"
        android:elevation="@dimen/size8"
        android:layout_height="@dimen/size128"
        android:layout_margin="@dimen/size8">
        <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent">
            <ImageView
                android:id="@+id/iv_card_image"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:src="@mipmap/ic_launcher_round"
                android:scaleType="centerCrop"/>
            <TextView
                android:id="@+id/tv_card_text"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:textColor="@android:color/black"
                android:text="Matrial Design"
                android:textSize="@dimen/text24"
                android:layout_marginBottom="@dimen/size16"
                android:layout_alignParentBottom="true"
                android:layout_centerHorizontal="true"/>
        </RelativeLayout>
    </android.support.v7.widget.CardView>
```
3. Jalankan dan lihat hasilnya.
#### 3.1. Menerapkan gambar dengan menggunakan picasso
1. Untuk mengambil gambar secara online dapat menggunakan picasso. Dengan menambahkan kode berikut di depedency gradle :
```
implementation 'com.squareup.picasso:picasso:2.71828'
```
**P.S : untuk gambar dapat diakses di https://maxcdn.icons8.com/app/uploads/2016/03/material-1-1000x563.jpg.**
2. Lalu tambahkan potongan kode berikut pada `onCreate` di class sebelumnya.
```java
    private ImageView mIvCardImage;
    private String mPath;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_card);

        mPath = "https://maxcdn.icons8.com/app/uploads/2016/03/material-1-1000x563.jpg";
        mIvCardImage = findViewById(R.id.iv_card_image);

        Picasso.get().load(mPath).fit().centerCrop().into(mIvCardImage);
    }

```
**P.S : 
a. `Picasso.get()` digunakan untuk mengambil gambar
b. `load(url)` digunakan untuk mengambil gambar dari website
c. `fit()` digunakan agar gambar sesuai dengan ukuran widget ImageView
d. `centerCrop()` digunakan untuk memotong gambar agar sesuai dengan ukuran widget ImageView
e. `into(imageView)` digunakan untuk memasukkan gambar ke imageView**

3. Tambahkan Permission pada `AndroidManifests` : 
```
<uses-permission android:name="android.permission.INTERNET"/>
```
4. Jalankan dan lihat hasilnya.

